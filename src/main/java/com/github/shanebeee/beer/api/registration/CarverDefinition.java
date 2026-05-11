package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CarverDefinition extends Definable<ConfiguredWorldCarver<?>> {


    public CarverDefinition(ResourceKey<ConfiguredWorldCarver<?>> resourceKey, @NonNull ConfiguredWorldCarver<?> value,
                            Holder.@Nullable Reference<ConfiguredWorldCarver<?>> holder) {
        super(resourceKey, value, holder);
    }

    public static CaveBuilder caveBuilder(ResourceKey<ConfiguredWorldCarver<?>> resourceKey,
                                      BootstrapContext<ConfiguredWorldCarver<?>> context) {
        return new CaveBuilder(resourceKey, context);
    }

    public static abstract class Builder {

        final ResourceKey<ConfiguredWorldCarver<?>> resourceKey;
        final BootstrapContext<ConfiguredWorldCarver<?>> context;

        public Builder(ResourceKey<ConfiguredWorldCarver<?>> resourceKey, BootstrapContext<ConfiguredWorldCarver<?>> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public abstract CarverDefinition build();

        public <WC extends CarverConfiguration> CarverDefinition build(WorldCarver<WC> carver, WC config) {
            ConfiguredWorldCarver<WC> configuredCarver = new ConfiguredWorldCarver<>(carver, config);
            Holder.Reference<ConfiguredWorldCarver<?>> holder;
            if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                ResourceKey<ConfiguredWorldCarver<?>> key = this.resourceKey;
                HolderGetter<ConfiguredWorldCarver<?>> lookup = this.context.lookup(Registries.CONFIGURED_CARVER);
                holder = lookup.getOrThrow(this.resourceKey);
            } else {
                holder = this.context.register(this.resourceKey, configuredCarver);
            }
            return new CarverDefinition(this.resourceKey, configuredCarver, holder);
        }
    }

    public static class CaveBuilder extends Builder {

        float probability;
        HeightProvider y;
        FloatProvider yScale;
        VerticalAnchor lavaLevel;
        CarverDebugSettings debugSettings = CarverDebugSettings.DEFAULT;
        List<Holder<Block>> replaceable;
        TagKey<Block> replaceableTag;
        FloatProvider horizontalRadiusMultiplier;
        FloatProvider verticalRadiusMultiplier;
        FloatProvider floorLevel;

        public CaveBuilder(ResourceKey<ConfiguredWorldCarver<?>> resourceKey, BootstrapContext<ConfiguredWorldCarver<?>> context) {
            super(resourceKey, context);
        }

        public CaveBuilder probability(float probability) {
            this.probability = probability;
            return this;
        }

        public CaveBuilder y(HeightProvider y) {
            this.y = y;
            return this;
        }

        public CaveBuilder yScale(UniformFloat yScale) {
            this.yScale = yScale;
            return this;
        }

        public CaveBuilder lavaLevel(VerticalAnchor lavaLevel) {
            this.lavaLevel = lavaLevel;
            return this;
        }

        public CaveBuilder addReplaceable(ResourceKey<Block> block) {
            Holder.Reference<Block> ref = this.context.lookup(Registries.BLOCK).getOrThrow(block);
            this.replaceable.add(ref);
            return this;
        }

        @SuppressWarnings("deprecation")
        public CaveBuilder addReplaceable(Block block) {
            ResourceKey<Block> key = block.builtInRegistryHolder().key();
            Holder.Reference<Block> ref = this.context.lookup(Registries.BLOCK).getOrThrow(key);
            this.replaceable.add(ref);
            return this;
        }

        public CaveBuilder replaceable(TagKey<Block> tag) {
            this.replaceableTag = tag;
            return this;
        }

        public CaveBuilder horizontalRadiusMultiplier(FloatProvider horizontalRadiusMultiplier) {
            this.horizontalRadiusMultiplier = horizontalRadiusMultiplier;
            return this;
        }

        public CaveBuilder verticalRadiusMultiplier(FloatProvider verticalRadiusMultiplier) {
            this.verticalRadiusMultiplier = verticalRadiusMultiplier;
            return this;
        }

        public CaveBuilder floorLevel(FloatProvider floorLevel) {
            this.floorLevel = floorLevel;
            return this;
        }

        @Override
        public CarverDefinition build() {
            HolderSet<Block> replaceables = HolderSet.empty();
            if (this.replaceableTag != null) {
                replaceables = this.context.lookup(Registries.BLOCK).getOrThrow(this.replaceableTag);
            } else if (!this.replaceable.isEmpty()) {
                replaceables = HolderSet.direct(this.replaceable);
            }
            CaveCarverConfiguration config = new CaveCarverConfiguration(this.probability, this.y, this.yScale, this.lavaLevel,
                this.debugSettings, replaceables, this.horizontalRadiusMultiplier, this.verticalRadiusMultiplier, this.floorLevel);
            WorldCarver<CaveCarverConfiguration> cave = WorldCarver.CAVE;
            return build(cave, config);
        }

    }

}
