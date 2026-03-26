package com.github.shanebeee.beer.api.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacedFeatureDefinition extends Definable<PlacedFeature> {

    private static BootstrapContext<ConfiguredFeature<?, ?>> CF_CONTEXT;

    public static void setupConfiguredFeatureContext(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        CF_CONTEXT = context;
    }

    public PlacedFeatureDefinition(ResourceKey<PlacedFeature> resourceKey, @NonNull PlacedFeature value, Holder.@Nullable Reference<PlacedFeature> holder) {
        super(resourceKey, value, holder);
    }

    public static Builder builder(ResourceKey<PlacedFeature> key, BootstrapContext<PlacedFeature> context) {
        return new Builder(key, context);
    }

    public static Builder builder(BootstrapContext<PlacedFeature> context) {
        return new Builder(null, context);
    }

    public static Builder builder() {
        return new Builder(null, null);
    }

    public static class Builder {

        private final ResourceKey<PlacedFeature> resourceKey;
        private final BootstrapContext<PlacedFeature> context;
        private Holder<ConfiguredFeature<?, ?>> configuredFeature;
        private final List<PlacementModifier> placementModifiers = new ArrayList<>();

        public Builder(ResourceKey<PlacedFeature> resourceKey, BootstrapContext<PlacedFeature> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public <F extends FeatureConfiguration> Builder configuredFeature(Feature<F> feature, F config) {
            this.configuredFeature = Holder.direct(new ConfiguredFeature<>(feature, config));
            return this;
        }

        public Builder configuredFeature(ResourceKey<ConfiguredFeature<?, ?>> key) {
            HolderGetter<ConfiguredFeature<?, ?>> registry;
            if (this.context != null) {
                registry = this.context.lookup(Registries.CONFIGURED_FEATURE);
            } else {
                registry = CF_CONTEXT.lookup(Registries.CONFIGURED_FEATURE);
            }
            this.configuredFeature = registry.getOrThrow(key);
            return this;
        }

        public Builder configuredFeature(Holder<ConfiguredFeature<?, ?>> config) {
            this.configuredFeature = config;
            return this;
        }

        public Builder placementModifiers(PlacementModifier... modifiers) {
            this.placementModifiers.addAll(Arrays.asList(modifiers));
            return this;
        }

        public PlacedFeatureDefinition build() {
            PlacedFeature placedFeature = new PlacedFeature(this.configuredFeature, this.placementModifiers);
            Holder.Reference<PlacedFeature> holder = null;
            if (this.context != null && this.resourceKey != null) {
                holder = this.context.register(this.resourceKey, placedFeature);
            }
            return new PlacedFeatureDefinition(this.resourceKey, placedFeature, holder);
        }

    }

}
