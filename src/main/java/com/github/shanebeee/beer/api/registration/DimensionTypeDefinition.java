package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.modifier.AttributeModifier;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.timeline.Timeline;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DimensionTypeDefinition extends Definable<DimensionType> {

    public DimensionTypeDefinition(ResourceKey<DimensionType> resourceKey, @NonNull DimensionType value, Holder.@Nullable Reference<DimensionType> holder) {
        super(resourceKey, value, holder);
    }

    public static DimensionTypeDefinition.Builder builder(ResourceKey<DimensionType> resourceKey, BootstrapContext<DimensionType> context) {
        return new DimensionTypeDefinition.Builder(resourceKey, context);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static class Builder {

        final ResourceKey<DimensionType> resourceKey;
        final BootstrapContext<DimensionType> context;
        boolean hasFixedTime;
        boolean hasSkyLight;
        boolean hasCeiling;
        boolean hasEnderDragonFight;
        double coordinateScale;
        int minY;
        int height;
        int logicalHeight;
        TagKey<Block> infiniburn;
        float ambientLight;
        DimensionType.MonsterSettings monsterSettings;
        DimensionType.Skybox skybox = DimensionType.Skybox.OVERWORLD;
        CardinalLighting.Type cardinalLightType = CardinalLighting.Type.DEFAULT;
        EnvironmentAttributeMap.Builder attributes = EnvironmentAttributeMap.builder();
        List<Holder<Timeline>> timelines = new ArrayList<>();
        TagKey<Timeline> timelinesTag;
        ResourceKey<WorldClock> defaultClock;

        public Builder(ResourceKey<DimensionType> resourceKey, BootstrapContext<DimensionType> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public Builder hasFixedTime(boolean hasFixedTime) {
            this.hasFixedTime = hasFixedTime;
            return this;
        }

        public Builder hasSkyLight(boolean hasSkyLight) {
            this.hasSkyLight = hasSkyLight;
            return this;
        }

        public Builder hasCeiling(boolean hasCeiling) {
            this.hasCeiling = hasCeiling;
            return this;
        }

        public Builder hasEnderDragonFight(boolean hasEnderDragonFight) {
            this.hasEnderDragonFight = hasEnderDragonFight;
            return this;
        }

        public Builder coordinateScale(double coordinateScale) {
            this.coordinateScale = coordinateScale;
            return this;
        }

        public Builder height(int minY, int height, int logicalHeight) {
            this.minY = minY;
            this.height = height;
            this.logicalHeight = logicalHeight;
            return this;
        }

        public Builder infiniburn(TagKey<Block> infiniburn) {
            this.infiniburn = infiniburn;
            return this;
        }

        public Builder ambientLight(float ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public Builder monsterSettings(DimensionType.MonsterSettings monsterSettings) {
            this.monsterSettings = monsterSettings;
            return this;
        }

        public Builder skybox(DimensionType.Skybox skybox) {
            this.skybox = skybox;
            return this;
        }

        public Builder cardinalLightType(CardinalLighting.Type type) {
            this.cardinalLightType = type;
            return this;
        }

        /**
         * Set the environmental attribute of this dimension type.
         *
         * @param attribute Attribute to set
         * @param value     Value to set to
         * @param <T>       Type of value according to attribute
         * @return This builder
         */
        public <T> Builder setAttribute(EnvironmentAttribute<@NotNull T> attribute, T value) {
            this.attributes.set(attribute, value);
            return this;
        }

        /**
         * Apply a modifier to an environmental attribute of this dimension type.
         *
         * @param attribute Attribute to modify
         * @param modifier  Modifier to apply to attribute
         * @param parameter Value of modification
         * @param <T>       Type of value according to attribute
         * @param <P>       Type of modifier according to attribute
         * @return This builder
         */
        public <T, P> Builder modifyAttribute(EnvironmentAttribute<T> attribute, AttributeModifier<T, P> modifier, P parameter) {
            this.attributes.modify(attribute, modifier, parameter);
            return this;
        }

        public Builder addTimeline(ResourceKey<Timeline> timeline) {
            Holder.Reference<Timeline> ref = this.context.lookup(Registries.TIMELINE).getOrThrow(timeline);
            this.timelines.add(ref);
            return this;
        }

        public Builder setTimeline(TagKey<Timeline> timeline) {
            this.timelinesTag = timeline;
            return this;
        }

        public Builder defaultClock(ResourceKey<WorldClock> defaultClock) {
            this.defaultClock = defaultClock;
            return this;
        }

        public DimensionTypeDefinition build() {
            HolderSet<Timeline> timelines = HolderSet.empty();
            if (this.timelinesTag != null) {
                timelines = this.context.lookup(Registries.TIMELINE).getOrThrow(this.timelinesTag);
            } else if (!this.timelines.isEmpty()) {
                timelines = HolderSet.direct(this.timelines);
            }

            Optional<Holder<WorldClock>> clock = Optional.empty();
            if (this.defaultClock != null) {
                Holder.Reference<WorldClock> orThrow = this.context.lookup(Registries.WORLD_CLOCK).getOrThrow(this.defaultClock);
                clock = Optional.of(orThrow);
            }

            DimensionType dimensionType = new DimensionType(this.hasFixedTime, this.hasSkyLight, this.hasCeiling,
                this.hasEnderDragonFight, this.coordinateScale, this.minY, this.height, this.logicalHeight,
                this.infiniburn, this.ambientLight, this.monsterSettings, this.skybox, this.cardinalLightType,
                this.attributes.build(), timelines, clock);

            Holder.Reference<DimensionType> holder;
            if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                holder = this.context.lookup(Registries.DIMENSION_TYPE).getOrThrow(this.resourceKey);
            } else {
                holder = this.context.register(this.resourceKey, dimensionType);
            }
            return new DimensionTypeDefinition(this.resourceKey, dimensionType, holder);
        }
    }

}
