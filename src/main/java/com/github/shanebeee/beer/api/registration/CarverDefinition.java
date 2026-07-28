package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.VeryBiasedToBottomInt;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CarverDefinition extends Definable<WorldCarver> {


    public CarverDefinition(ResourceKey<WorldCarver> resourceKey, @NonNull WorldCarver value,
                            Holder.@Nullable Reference<WorldCarver> holder) {
        super(resourceKey, value, holder);
    }

    public static CaveBuilder caveBuilder(ResourceKey<WorldCarver> resourceKey,
                                          BootstrapContext<WorldCarver> context) {
        return new CaveBuilder(resourceKey, context);
    }

    public static abstract class Builder {

        final ResourceKey<WorldCarver> resourceKey;
        final BootstrapContext<WorldCarver> context;

        public Builder(ResourceKey<WorldCarver> resourceKey, BootstrapContext<WorldCarver> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public abstract CarverDefinition build();

        public CarverDefinition build(WorldCarver carver) {
            Holder.Reference<WorldCarver> holder;
            if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                ResourceKey<WorldCarver> key = this.resourceKey;
                HolderGetter<WorldCarver> lookup = this.context.lookup(Registries.CARVER);
                holder = lookup.getOrThrow(this.resourceKey);
            } else {
                holder = this.context.register(this.resourceKey, carver);
            }
            return new CarverDefinition(this.resourceKey, carver, holder);
        }
    }

    public static class CaveBuilder extends Builder {

        float probability;
        HeightProvider y;
        IntProvider count = VeryBiasedToBottomInt.of(0, 14);
        FloatProvider thickness = TrapezoidFloat.of(0.0F, 3.0F, 1.0F);
        boolean weirdThicknessBias = true;
        FloatProvider roomVerticalRadiusMultiplier;
        FloatProvider horizontalRadiusMultiplier;
        FloatProvider verticalRadiusMultiplier;
        FloatProvider startVerticalRadiusMultiplier;
        FloatProvider floorLevel;

        public CaveBuilder(ResourceKey<WorldCarver> resourceKey, BootstrapContext<WorldCarver> context) {
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

        public CaveBuilder count(IntProvider count) {
            this.count = count;
            return this;
        }

        public CaveBuilder thickness(FloatProvider thickness) {
            this.thickness = thickness;
            return this;
        }

        public CaveBuilder weirdThicknessBias(boolean weirdThicknessBias) {
            this.weirdThicknessBias = weirdThicknessBias;
            return this;
        }

        public CaveBuilder roomVerticalRadiusMultiplier(UniformFloat yScale) {
            this.roomVerticalRadiusMultiplier = yScale;
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
            CaveWorldCarver carver = new CaveWorldCarver(this.probability, this.y, this.count, this.thickness,
                this.weirdThicknessBias, this.roomVerticalRadiusMultiplier,
                this.horizontalRadiusMultiplier,
                this.verticalRadiusMultiplier,
                this.startVerticalRadiusMultiplier, this.floorLevel);
            return build(carver);
        }

    }

}
