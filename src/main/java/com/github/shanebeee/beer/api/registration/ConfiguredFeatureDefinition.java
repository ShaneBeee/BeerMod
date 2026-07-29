package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;

public class ConfiguredFeatureDefinition extends Definable<Feature> {

    public ConfiguredFeatureDefinition(ResourceKey<Feature> resourceKey, Feature value, Holder.Reference<Feature> holder) {
        super(resourceKey, value, holder);
    }

    public static Builder builder(ResourceKey<Feature> key, BootstrapContext<Feature> context) {
        return new Builder(key, context);
    }

    public static Builder builder(BootstrapContext<Feature> context) {
        return new Builder(null, context);
    }

    public static class Builder {

        private final ResourceKey<Feature> resourceKey;
        private final BootstrapContext<Feature> context;
        private Feature feature;

        public Builder(ResourceKey<Feature> key, BootstrapContext<Feature> context) {
            this.resourceKey = key;
            this.context = context;
        }

        public Builder config(Feature feature) {
            this.feature = feature;
            return this;
        }

        public ConfiguredFeatureDefinition build() {
            Holder.Reference<Feature> holder = null;
            if (this.resourceKey != null) {
                if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                    holder = this.context.lookup(Registries.FEATURE).getOrThrow(this.resourceKey);
                } else {
                    holder = this.context.register(this.resourceKey, this.feature);
                }
            }
            return new ConfiguredFeatureDefinition(this.resourceKey, this.feature, holder);
        }

    }

}
