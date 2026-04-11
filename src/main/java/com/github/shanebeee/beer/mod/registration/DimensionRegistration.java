package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.biome.BiomeDefaults;
import com.github.shanebeee.beer.api.biome.Continentalness;
import com.github.shanebeee.beer.api.biome.Erosion;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.DimensionDefinition;
import com.github.shanebeee.beer.mod.biomes.continental.CoastalBiomes;
import com.github.shanebeee.beer.mod.biomes.continental.FarInlandBiomes;
import com.github.shanebeee.beer.mod.biomes.continental.MidInlandBiomes;
import com.github.shanebeee.beer.mod.biomes.continental.NearInlandBiomes;
import com.github.shanebeee.beer.mod.biomes.continental.OceanBiomes;
import com.github.shanebeee.beer.mod.biomes.special.CaveBiomes;
import com.github.shanebeee.beer.mod.registry.Dimensions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate.Parameter;
import net.minecraft.world.level.dimension.LevelStem;
import org.jetbrains.annotations.NotNull;

public class DimensionRegistration extends BaseRegistration<LevelStem, DimensionDefinition> {

    public DimensionRegistration(BootstrapContext<LevelStem> context) {
        super(Registries.LEVEL_STEM, context);
        DimensionDefinition.Builder builder = DimensionDefinition.overworldBuilder(Dimensions.BEER_WORLD, context);

        // CAVE BIOMES
        builder.addPoint(Biomes.DEEP_DARK,
            BiomeDefaults.FULL_RANGE,
            BiomeDefaults.FULL_RANGE,
            BiomeDefaults.FULL_RANGE,
            Parameter.span(-1.0f, -0.375f),
            Parameter.span(1.1f, 2.0f),
            BiomeDefaults.FULL_RANGE, 0);

        for (Continentalness continentalness : Continentalness.values()) {
            for (Temperature temperature : Temperature.values()) {
                for (Humidity humidity : Humidity.values()) {
                    for (PeaksAndValleys pv : PeaksAndValleys.values()) {
                        for (Weirdness weirdness : pv.weirdness()) {

                            ResourceKey<Biome> biomeKey = CaveBiomes.getBiome(continentalness, temperature,
                                humidity, weirdness, pv);

                            builder.addPoint(biomeKey,
                                continentalness.span(),
                                temperature.span(),
                                humidity.span(),
                                BiomeDefaults.FULL_RANGE,
                                BiomeDefaults.CAVE_DEPTH,
                                weirdness.span());
                        }
                    }
                }
            }
        }

        // SURFACE BIOMES
        for (Continentalness continentalness : Continentalness.values()) {
            for (Temperature temp : Temperature.values()) {
                for (Humidity humidity : Humidity.values()) {
                    for (Erosion erosion : Erosion.values()) {
                        for (PeaksAndValleys pv : PeaksAndValleys.values()) {
                            for (Weirdness weirdness : pv.weirdness()) {

                                ResourceKey<Biome> biomeKey = getBiome(continentalness, temp, humidity,
                                    weirdness, pv, erosion);

                                builder.addPoint(biomeKey,
                                    continentalness.span(),
                                    temp.span(),
                                    humidity.span(),
                                    erosion.span(),
                                    BiomeDefaults.SURFACE_DEPTH,
                                    weirdness.span());
                            }
                        }
                    }
                }
            }
        }

        // REGISTER
        register(builder.consolidate().build());
    }

    private @NotNull ResourceKey<Biome> getBiome(Continentalness continent, Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (continent) {
            case MUSHROOM_FIELDS -> Biomes.MUSHROOM_FIELDS;
            case DEEP_OCEAN -> OceanBiomes.getBiome(true, temp, humidity, weirdness);
            case OCEAN -> OceanBiomes.getBiome(false, temp, humidity, weirdness);
            case COASTAL -> CoastalBiomes.getBiome(temp, humidity, weirdness, pv, erosion);
            case NEAR_INLAND -> NearInlandBiomes.getBiome(temp, humidity, weirdness, pv, erosion);
            case MID_INLAND -> MidInlandBiomes.getBiome(temp, humidity, weirdness, pv, erosion);
            case FAR_INLAND -> FarInlandBiomes.getBiome(temp, humidity, weirdness, pv, erosion);
        };
    }

}
