package com.github.shanebeee.beer.mod;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registration.BiomeRegistration;
import com.github.shanebeee.beer.mod.registration.ConfiguredFeatureRegistration;
import com.github.shanebeee.beer.mod.registration.PlacedFeatureRegistration;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class BeerDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(DataRegistration::new);
        pack.addProvider(BiomeTagRegistration::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureRegistration::registerFeatures);
        registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureRegistration::registerFeatures);
        registryBuilder.add(Registries.BIOME, BiomeRegistration::registerBiomes);
    }

    public static class DataRegistration extends FabricDynamicRegistryProvider {

        public DataRegistration(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(HolderLookup.@NonNull Provider registries, @NonNull Entries entries) {
            entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
            entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
            entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        }

        @Override
        public @NonNull String getName() {
            return "DataRegistration";
        }
    }

    public static class BiomeTagRegistration extends FabricTagProvider<Biome> {

        public BiomeTagRegistration(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
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

}
