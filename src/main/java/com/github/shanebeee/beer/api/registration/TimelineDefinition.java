package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.KeyframeTrack;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.modifier.AttributeModifier;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.timeline.Timeline;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class TimelineDefinition extends Definable<Timeline> {

    public TimelineDefinition(ResourceKey<Timeline> resourceKey, @NonNull Timeline value, Holder.@Nullable Reference<Timeline> holder, List<TagKey<Timeline>> tagKeys) {
        super(resourceKey, value, holder, tagKeys);
    }

    public static Builder builder(ResourceKey<Timeline> resourceKey, BootstrapContext<Timeline> context) {
        Holder.Reference<WorldClock> clock = context.lookup(Registries.WORLD_CLOCK).getOrThrow(WorldClocks.OVERWORLD);
        return new Builder(resourceKey, context, clock);
    }

    public static Builder builder(ResourceKey<Timeline> resourceKey, BootstrapContext<Timeline> context, Holder<WorldClock> clock) {
        return new Builder(resourceKey, context, clock);
    }

    public static class Builder {

        private final ResourceKey<Timeline> resourceKey;
        private final BootstrapContext<Timeline> context;
        private final Timeline.Builder builder;
        private final List<TagKey<Timeline>> tagKeys = new ArrayList<>();

        private Builder(ResourceKey<Timeline> resourceKey, BootstrapContext<Timeline> context, Holder<WorldClock> clock) {
            this.resourceKey = resourceKey;
            this.context = context;
            this.builder = Timeline.builder(clock);
        }

        public Builder periodTicks(int ticks) {
            this.builder.setPeriodTicks(ticks);
            return this;
        }

        public <Value, Argument> Builder addModifierTrack(EnvironmentAttribute<Value> attribute,
                                                          AttributeModifier<Value, Argument> modifier,
                                                          Consumer<KeyframeTrack.Builder<Argument>> builder) {
            this.builder.addModifierTrack(attribute, modifier, builder);
            return this;
        }

        public <Value> Builder addTrack(EnvironmentAttribute<Value> attribute, Consumer<KeyframeTrack.Builder<Value>> builder) {
            this.builder.addTrack(attribute, builder);
            return this;
        }

        @SafeVarargs
        public final Builder addToTag(TagKey<Timeline>... tagKeys) {
            Collections.addAll(this.tagKeys, tagKeys);
            return this;
        }

        public TimelineDefinition build() {
            Timeline timeline = this.builder.build();
            Holder.Reference<Timeline> holder = null;
            if (this.context != null && this.resourceKey != null) {
                if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                    holder = this.context.lookup(Registries.TIMELINE).getOrThrow(this.resourceKey);
                } else {
                    holder = this.context.register(this.resourceKey, timeline);
                }
            }
            return new TimelineDefinition(this.resourceKey, timeline, holder, this.tagKeys);
        }

    }

}
