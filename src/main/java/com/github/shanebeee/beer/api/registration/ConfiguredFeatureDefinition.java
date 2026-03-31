package com.github.shanebeee.beer.api.registration;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class ConfiguredFeatureDefinition extends Definable<ConfiguredFeature<?, ?>> {

    public ConfiguredFeatureDefinition(ResourceKey<ConfiguredFeature<?, ?>> resourceKey, ConfiguredFeature<?, ?> value, Holder.Reference<ConfiguredFeature<?, ?>> holder) {
        super(resourceKey, value, holder);
    }

    public static Builder builder(ResourceKey<ConfiguredFeature<?, ?>> key, BootstrapContext<ConfiguredFeature<?, ?>> context) {
        return new Builder(key, context);
    }

    public static Builder builder(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        return new Builder(null, context);
    }

    public static class Builder {

        private final ResourceKey<ConfiguredFeature<?, ?>> identifier;
        private final BootstrapContext<ConfiguredFeature<?, ?>> context;
        private Feature<? extends FeatureConfiguration> feature;
        private FeatureConfiguration config;

        public Builder(ResourceKey<ConfiguredFeature<?, ?>> key, BootstrapContext<ConfiguredFeature<?, ?>> context) {
            this.identifier = key;
            this.context = context;
        }

        public <F extends FeatureConfiguration> Builder config(Feature<F> feature, F config) {
            this.feature = feature;
            this.config = config;
            return this;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        public ConfiguredFeatureDefinition build() {
            ConfiguredFeature<?, ?> configuredFeature = new ConfiguredFeature(this.feature, this.config);
            Holder.Reference<ConfiguredFeature<?, ?>> holder = null;
            if (this.identifier != null) {
                holder = this.context.register(this.identifier, configuredFeature);
            }
            return new ConfiguredFeatureDefinition(this.identifier, configuredFeature, holder);
        }

    }

}
