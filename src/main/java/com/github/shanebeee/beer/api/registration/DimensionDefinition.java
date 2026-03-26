package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.api.utils.Utils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DimensionDefinition extends Definable<LevelStem> {

    public DimensionDefinition(ResourceKey<LevelStem> resourceKey, LevelStem value, Holder.Reference<LevelStem> holder) {
        super(resourceKey, value, holder);
    }

    public static Builder overworldBuilder(ResourceKey<LevelStem> resourceKey, BootstrapContext<LevelStem> context) {
        return builder(resourceKey, context, BuiltinDimensionTypes.OVERWORLD, NoiseGeneratorSettings.OVERWORLD);
    }

    public static Builder builder(ResourceKey<LevelStem> resourceKey, BootstrapContext<LevelStem> context, ResourceKey<DimensionType> dimensionTypeKey, ResourceKey<NoiseGeneratorSettings> noiseGeneratorSettingsKey) {
        return new Builder(resourceKey, context, dimensionTypeKey, noiseGeneratorSettingsKey);
    }

    public static class Builder {

        final ResourceKey<LevelStem> resourceKey;
        final BootstrapContext<LevelStem> context;
        ResourceKey<DimensionType> dimensionType;
        ResourceKey<NoiseGeneratorSettings> noiseGeneratorSettingsKey;
        List<Pair<Climate.ParameterPoint, Holder<Biome>>> paramList = new ArrayList<>();
        java.util.Map<ResourceKey<Biome>, Holder.Reference<Biome>> biomeReferenceCache = new java.util.HashMap<>();

        public Builder(ResourceKey<LevelStem> resourceKey, BootstrapContext<LevelStem> context, ResourceKey<DimensionType> dimensionTypeKey, ResourceKey<NoiseGeneratorSettings> noiseGeneratorSettingsKey) {
            this.context = context;
            this.dimensionType = dimensionTypeKey;
            this.resourceKey = resourceKey;
            this.noiseGeneratorSettingsKey = noiseGeneratorSettingsKey;
        }

        @SuppressWarnings("UnusedReturnValue")
        public Builder addPoint(@NotNull ResourceKey<Biome> biomeKey, Climate.Parameter continentalness, Climate.Parameter temperature, Climate.Parameter humidity, Climate.Parameter erosion, Climate.Parameter depth, Climate.Parameter weirdness, long offset) {
            Climate.ParameterPoint parameterPoint = new Climate.ParameterPoint(temperature, humidity, continentalness, erosion, depth, weirdness, offset);
            Holder.Reference<Biome> biomeReference = this.biomeReferenceCache.computeIfAbsent(biomeKey, new Function<ResourceKey<Biome>, Holder.Reference<Biome>>() {
                @Override
                public Holder.Reference<Biome> apply(ResourceKey<Biome> biomeResourceKey) {
                    HolderGetter<Biome> lookup = context.lookup(Registries.BIOME);
                    return lookup.getOrThrow(biomeResourceKey);
                }
            });
            this.paramList.add(new Pair<>(parameterPoint, biomeReference));

            return this;
        }

        public Builder consolidate() {
            int originalSize = this.paramList.size();
            Utils.log("Starting consolidation with " + originalSize + " parameter points");

            // Debug: Count unique biomes and duplicates
            java.util.Map<Holder<Biome>, Integer> biomeCount = new java.util.HashMap<>();
            for (Pair<Climate.ParameterPoint, Holder<Biome>> pair : this.paramList) {
                biomeCount.merge(pair.getSecond(), 1, Integer::sum);
            }

            int uniqueBiomes = biomeCount.size();
            int biomesWithDuplicates = (int) biomeCount.values().stream().filter(count -> count > 1).count();
            Utils.log("Unique biomes: " + uniqueBiomes);
            Utils.log("Biomes with duplicates: " + biomesWithDuplicates);

            Utils.log("All biomes and their occurrence count:");
            biomeCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(20)
                .forEach(e -> Utils.log("  " + e.getValue() + " times"));

            // Group by biome first to avoid O(n²) comparisons across all biomes
            java.util.Map<Holder<Biome>, java.util.List<Pair<Climate.ParameterPoint, Holder<Biome>>>> groupedByBiome = new java.util.HashMap<>();
            for (Pair<Climate.ParameterPoint, Holder<Biome>> pair : this.paramList) {
                groupedByBiome.computeIfAbsent(pair.getSecond(), k -> new ArrayList<>()).add(pair);
            }

            Utils.log("Processing " + groupedByBiome.size() + " unique biomes...");

            List<Pair<Climate.ParameterPoint, Holder<Biome>>> consolidated = new ArrayList<>();
            int totalMerges = 0;

            for (java.util.Map.Entry<Holder<Biome>, java.util.List<Pair<Climate.ParameterPoint, Holder<Biome>>>> entry : groupedByBiome.entrySet()) {
                java.util.List<Pair<Climate.ParameterPoint, Holder<Biome>>> biomePoints = new ArrayList<>(entry.getValue());
                int originalCount = biomePoints.size();

                boolean merged;
                do {
                    merged = false;
                    for (int i = 0; i < biomePoints.size(); i++) {
                        for (int j = i + 1; j < biomePoints.size(); j++) {
                            Climate.ParameterPoint mergedPoint = tryMerge(biomePoints.get(i).getFirst(), biomePoints.get(j).getFirst());
                            if (mergedPoint != null) {
                                biomePoints.set(i, new Pair<>(mergedPoint, entry.getKey()));
                                biomePoints.remove(j);
                                merged = true;
                                totalMerges++;
                                break;
                            }
                        }
                        if (merged) break;
                    }
                } while (merged);

                consolidated.addAll(biomePoints);
                if (originalCount > biomePoints.size()) {
                    Utils.log("  Merged " + originalCount + " -> " + biomePoints.size() + " points");
                }
            }

            this.paramList = consolidated;

            int finalSize = this.paramList.size();
            Utils.log("Consolidation complete!");
            Utils.log("Total merges performed: " + totalMerges);
            Utils.log("Reduced from " + originalSize + " to " + finalSize + " parameter points");
            Utils.log("Reduction: " + (originalSize - finalSize) + " points (" +
                String.format("%.2f", (100.0 * (originalSize - finalSize) / originalSize)) + "%)");

            return this;
        }

        private Climate.ParameterPoint tryMerge(Climate.ParameterPoint p1, Climate.ParameterPoint p2) {
            // Check if offset matches
            if (p1.offset() != p2.offset()) return null;

            int differences = 0;
            Climate.Parameter mergedTemp = null, mergedHumid = null, mergedCont = null;
            Climate.Parameter mergedErosion = null, mergedDepth = null, mergedWeird = null;

            // Temperature
            if (!parametersEqual(p1.temperature(), p2.temperature())) {
                mergedTemp = tryMergeParameter(p1.temperature(), p2.temperature());
                if (mergedTemp == null) return null;
                differences++;
            } else {
                mergedTemp = p1.temperature();
            }

            // Humidity
            if (!parametersEqual(p1.humidity(), p2.humidity())) {
                mergedHumid = tryMergeParameter(p1.humidity(), p2.humidity());
                if (mergedHumid == null) return null;
                differences++;
            } else {
                mergedHumid = p1.humidity();
            }

            // Continentalness
            if (!parametersEqual(p1.continentalness(), p2.continentalness())) {
                mergedCont = tryMergeParameter(p1.continentalness(), p2.continentalness());
                if (mergedCont == null) return null;
                differences++;
            } else {
                mergedCont = p1.continentalness();
            }

            // Erosion
            if (!parametersEqual(p1.erosion(), p2.erosion())) {
                mergedErosion = tryMergeParameter(p1.erosion(), p2.erosion());
                if (mergedErosion == null) return null;
                differences++;
            } else {
                mergedErosion = p1.erosion();
            }

            // Depth
            if (!parametersEqual(p1.depth(), p2.depth())) {
                mergedDepth = tryMergeParameter(p1.depth(), p2.depth());
                if (mergedDepth == null) return null;
                differences++;
            } else {
                mergedDepth = p1.depth();
            }

            // Weirdness
            if (!parametersEqual(p1.weirdness(), p2.weirdness())) {
                mergedWeird = tryMergeParameter(p1.weirdness(), p2.weirdness());
                if (mergedWeird == null) return null;
                differences++;
            } else {
                mergedWeird = p1.weirdness();
            }

            // Only merge if exactly one parameter differs
            if (differences != 1) return null;

            return new Climate.ParameterPoint(mergedTemp, mergedHumid, mergedCont, mergedErosion, mergedDepth, mergedWeird, p1.offset());
        }

        @SuppressWarnings("BooleanMethodIsAlwaysInverted")
        private boolean parametersEqual(Climate.Parameter p1, Climate.Parameter p2) {
            return p1.min() == p2.min() && p1.max() == p2.max();
        }

        private Climate.Parameter tryMergeParameter(Climate.Parameter p1, Climate.Parameter p2) {
            // Check if ranges are adjacent using quantized long values
            long p1Min = p1.min();
            long p1Max = p1.max();
            long p2Min = p2.min();
            long p2Max = p2.max();

            // Check if ranges are adjacent (edges touch)
            if (p1Max == p2Min) {
                // Convert quantized values back to floats using Climate's unquantize method
                // The quantization uses: quantize(x) = round(x * 10000)
                // So unquantize: x / 10000.0
                float newMin = Climate.unquantizeCoord(p1Min);
                float newMax = Climate.unquantizeCoord(p2Max);
                return Climate.Parameter.span(newMin, newMax);
            } else if (p2Max == p1Min) {
                float newMin = Climate.unquantizeCoord(p2Min);
                float newMax = Climate.unquantizeCoord(p1Max);
                return Climate.Parameter.span(newMin, newMax);
            }

            return null;
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        public DimensionDefinition build() {
            Holder.Reference<DimensionType> dimensionTypeReference = this.context.lookup(Registries.DIMENSION_TYPE).get(this.dimensionType).get();

            BiomeSource biomeSource = MultiNoiseBiomeSource.createFromList(new Climate.ParameterList<>(this.paramList));
            Holder.Reference<NoiseGeneratorSettings> genSettings = this.context.lookup(Registries.NOISE_SETTINGS).get(this.noiseGeneratorSettingsKey).get();

            LevelStem levelStem = new LevelStem(dimensionTypeReference, new NoiseBasedChunkGenerator(biomeSource, genSettings));
            Holder.Reference<LevelStem> holder = this.context.register(this.resourceKey, levelStem);
            return new DimensionDefinition(this.resourceKey, levelStem, holder);
        }
    }

}
