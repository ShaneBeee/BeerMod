package com.github.shanebeee.beer.api.registration;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConfiguredFeatureDefinition {

    private final ResourceKey<ConfiguredFeature<?, ?>> resourceKey;
    private final Holder<ConfiguredFeature<?, ?>> holder;
    private final List<TagKey<ConfiguredFeature<?, ?>>> tagKeys;

    private ConfiguredFeatureDefinition(ResourceKey<ConfiguredFeature<?, ?>> resourceKey, @Nullable Holder<ConfiguredFeature<?, ?>> holder, @NotNull List<TagKey<ConfiguredFeature<?, ?>>> tagKeys) {
        this.resourceKey = resourceKey;
        this.holder = holder;
        this.tagKeys = tagKeys;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getResourceKey() {
        return this.resourceKey;
    }

    public Holder<ConfiguredFeature<?, ?>> getHolder() {
        return this.holder;
    }

    public List<TagKey<ConfiguredFeature<?, ?>>> getTagKeys() {
        return this.tagKeys;
    }

    public static Builder builder(ResourceKey<ConfiguredFeature<?, ?>> key, BootstrapContext<ConfiguredFeature<?,?>> entries) {
        return new Builder(key, entries);
    }

    public static Builder builder(BootstrapContext<ConfiguredFeature<?,?>> context) {
        return new Builder(null, context);
    }

    public static class Builder {

        private final ResourceKey<ConfiguredFeature<?, ?>> identifier;
        private final BootstrapContext<ConfiguredFeature<?,?>> context;
        private Feature<? extends FeatureConfiguration> feature;
        private FeatureConfiguration config;

        public Builder(ResourceKey<ConfiguredFeature<?, ?>> key, BootstrapContext<ConfiguredFeature<?,?>> context) {
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
            Holder<ConfiguredFeature<?, ?>> holder = null;
            if (this.identifier != null) {
                holder = this.context.register(this.identifier, configuredFeature);
            }
            return new ConfiguredFeatureDefinition(this.identifier, holder, List.of());
        }

    }

}
