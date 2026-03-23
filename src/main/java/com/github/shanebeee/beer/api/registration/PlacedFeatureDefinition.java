package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.api.utils.RegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacedFeatureDefinition {

    private final PlacedFeature placedFeature;
    private final List<TagKey<PlacedFeature>> tagKeys;
    private final ResourceKey<PlacedFeature> resourceKey;

    private PlacedFeatureDefinition(ResourceKey<PlacedFeature> resourceKey, PlacedFeature placedFeature, List<TagKey<PlacedFeature>> tagKeys) {
        this.resourceKey = resourceKey;
        this.placedFeature = placedFeature;
        this.tagKeys = tagKeys;
    }

    public ResourceKey<PlacedFeature> getResourceKey() {
        return this.resourceKey;
    }

    public PlacedFeature getValue() {
        return this.placedFeature;
    }

    public Holder<PlacedFeature> getFeatureHolder() {
        return Holder.direct(this.placedFeature);
    }

    public List<TagKey<PlacedFeature>> getTagKeys() {
        return this.tagKeys;
    }

    public static Builder builder(ResourceKey<PlacedFeature> key, BootstrapContext<PlacedFeature> context) {
        return new Builder(key, context);
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
            this.configuredFeature = RegistryUtils.getConfiguredFeatureReference(key);
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
            if (this.context != null && this.resourceKey != null) {
                this.context.register(this.resourceKey, placedFeature);
            }
            return new PlacedFeatureDefinition(this.resourceKey, placedFeature, null);
        }

    }

}
