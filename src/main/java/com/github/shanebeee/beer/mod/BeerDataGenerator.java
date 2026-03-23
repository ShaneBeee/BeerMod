package com.github.shanebeee.beer.mod;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.api.registration.TimelineDefinition;
import com.github.shanebeee.beer.mod.registration.BiomeRegistration;
import com.github.shanebeee.beer.mod.registration.ConfiguredFeatureRegistration;
import com.github.shanebeee.beer.mod.registration.DimensionRegistration;
import com.github.shanebeee.beer.mod.registration.PlacedFeatureRegistration;
import com.github.shanebeee.beer.mod.registration.TimelineRegistration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.timeline.Timeline;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class BeerDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(DataRegistration::new);
        pack.addProvider(BiomeTagRegistration::new);
        pack.addProvider(TimelineTagRegistration::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureRegistration::registerFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureRegistration::registerFeatures);
        registryBuilder.add(Registries.BIOME, BiomeRegistration::registerBiomes);
        registryBuilder.add(Registries.LEVEL_STEM, DimensionRegistration::registerDimensions);
        registryBuilder.add(Registries.TIMELINE, TimelineRegistration::registerTimelines);
    }

    public static class DataRegistration extends FabricDynamicRegistryProvider {

        public DataRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
            DynamicRegistries.register(Registries.LEVEL_STEM, LevelStem.CODEC);
        }

        @Override
        protected void configure(HolderLookup.@NonNull Provider registries, @NonNull Entries entries) {
            entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
            entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
            entries.addAll(registries.lookupOrThrow(Registries.BIOME));
            entries.addAll(registries.lookupOrThrow(Registries.LEVEL_STEM));
            entries.addAll(registries.lookupOrThrow(Registries.TIMELINE));
        }

        @Override
        public @NonNull String getName() {
            return "DataRegistration";
        }
    }

    public static class BiomeTagRegistration extends FabricTagsProvider<Biome> {

        public BiomeTagRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.BIOME, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
            for (BiomeDefinition biomeDefinition : BiomeRegistration.getBiomeDefinitions()) {
                for (TagKey<Biome> tagKey : biomeDefinition.getTagKeys()) {
                    getOrCreateRawBuilder(tagKey)
                        .addElement(biomeDefinition.getResourceKey().identifier())
                        .build();
                }
            }
        }
    }

    public static class TimelineTagRegistration extends FabricTagsProvider<Timeline> {

        public TimelineTagRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, Registries.TIMELINE, registriesFuture);
        }

        @Override
        protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
            for (TimelineDefinition biomeDefinition : TimelineRegistration.getTimelineDefinitions()) {
                for (TagKey<Timeline> tagKey : biomeDefinition.getTagKeys()) {
                    getOrCreateRawBuilder(tagKey)
                        .addElement(biomeDefinition.getResourceKey().identifier())
                        .build();
                }
            }
        }
    }

}
