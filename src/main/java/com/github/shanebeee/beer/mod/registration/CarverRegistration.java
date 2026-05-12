package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.CarverDefinition;
import com.github.shanebeee.beer.mod.registry.Carvers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class CarverRegistration extends BaseRegistration<ConfiguredWorldCarver<?>, CarverDefinition> {

    public CarverRegistration(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        super(Registries.CONFIGURED_CARVER, context);

        CarverDefinition cave = createCave(Carvers.CAVE,
            0.35f,
            VerticalAnchor.aboveBottom(8),
            VerticalAnchor.absolute(180));
        register(cave);

        CarverDefinition cave_extra = createCave(Carvers.CAVE_EXTRA,
            0.5f,
            VerticalAnchor.aboveBottom(8),
            VerticalAnchor.absolute(0));
        register(cave_extra);
    }

    private CarverDefinition createCave(ResourceKey<ConfiguredWorldCarver<?>> key, float probability, VerticalAnchor minY, VerticalAnchor maxY) {
        return CarverDefinition.caveBuilder(key, getContext())
            .probability(probability)
            .replaceable(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .floorLevel(UniformFloat.of(-1.0f, -0.4f))
            .lavaLevel(VerticalAnchor.aboveBottom(8))
            .horizontalRadiusMultiplier(UniformFloat.of(1.9f, 2.4f))
            .verticalRadiusMultiplier(UniformFloat.of(1.2f, 1.7f))
            .y(UniformHeight.of(minY, maxY))
            .yScale(UniformFloat.of(0.1f, 0.4f))
            .build();
    }

}
