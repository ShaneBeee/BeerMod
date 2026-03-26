package com.github.shanebeee.beer.mod;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.Definable;
import com.github.shanebeee.beer.mod.registration.BiomeRegistration;
import com.github.shanebeee.beer.mod.registration.ConfiguredFeatureRegistration;
import com.github.shanebeee.beer.mod.registration.DimensionRegistration;
import com.github.shanebeee.beer.mod.registration.PlacedFeatureRegistration;
import com.github.shanebeee.beer.mod.registration.StructureRegistration;
import com.github.shanebeee.beer.mod.registration.StructureSetRegistration;
import com.github.shanebeee.beer.mod.registration.TagRegistration;
import com.github.shanebeee.beer.mod.registration.TemplatePoolRegistration;
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
import net.minecraft.resources.ResourceKey;
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
        pack.addProvider(TagRegistration::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureRegistration::new);
        builder.add(Registries.PLACED_FEATURE, PlacedFeatureRegistration::new);
        builder.add(Registries.BIOME, BiomeRegistration::new);
        builder.add(Registries.LEVEL_STEM, DimensionRegistration::new);
        builder.add(Registries.TIMELINE, TimelineRegistration::new);
        builder.add(Registries.TEMPLATE_POOL, TemplatePoolRegistration::new);
        builder.add(Registries.STRUCTURE, StructureRegistration::new);
        builder.add(Registries.STRUCTURE_SET, StructureSetRegistration::new);
    }

    public static class DataRegistration extends FabricDynamicRegistryProvider {

        public DataRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
            DynamicRegistries.register(Registries.LEVEL_STEM, LevelStem.CODEC);
        }

        @Override
        protected void configure(HolderLookup.@NonNull Provider registries, @NonNull Entries entries) {
            for (BaseRegistration<?, ?> registration : BaseRegistration.getRegistrations()) {
                registration.addToEntries(entries);
            }
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
            for (Definable<Biome> definition : BaseRegistration.getDefinables(Registries.BIOME)) {
                for (TagKey<Biome> tagKey : definition.getTagKeys()) {
                    ResourceKey<Biome> resourceKey = definition.getResourceKey();
                    // Should never happen, but let's be safe
                    if (resourceKey == null) continue;

                    getOrCreateRawBuilder(tagKey)
                        .addElement(resourceKey.identifier())
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
            for (Definable<Timeline> definition : BaseRegistration.getDefinables(Registries.TIMELINE)) {
                for (TagKey<Timeline> tagKey : definition.getTagKeys()) {
                    ResourceKey<Timeline> resourceKey = definition.getResourceKey();
                    // Should never happen, but let's be safe
                    if (resourceKey == null) continue;

                    getOrCreateRawBuilder(tagKey)
                        .addElement(resourceKey.identifier())
                        .build();
                }
            }
        }
    }

}
