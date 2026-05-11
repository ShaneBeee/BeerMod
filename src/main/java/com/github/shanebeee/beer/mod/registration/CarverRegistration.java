package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.CarverDefinition;
import com.github.shanebeee.beer.mod.registry.Carvers;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class CarverRegistration extends BaseRegistration<ConfiguredWorldCarver<?>, CarverDefinition> {

    public CarverRegistration(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        super(Registries.CONFIGURED_CARVER, context);

        CarverDefinition cave = CarverDefinition.caveBuilder(Carvers.CAVE, context)
            .probability(0.15f)
            .floorLevel(UniformFloat.of(-1.0f, -0.4f))
            .horizontalRadiusMultiplier(UniformFloat.of(0.7f, 1.4f))
            .lavaLevel(VerticalAnchor.aboveBottom(8))
            .replaceable(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .verticalRadiusMultiplier(UniformFloat.of(0.8f, 1.3f))
            .y(UniformHeight.of(
                VerticalAnchor.aboveBottom(8),
                VerticalAnchor.absolute(180)
            ))
            .yScale(UniformFloat.of(0.1f, 0.9f))
            .build();
        register(cave);

        CarverDefinition cave_extra = CarverDefinition.caveBuilder(Carvers.CAVE_EXTRA, context)
            .probability(0.07f)
            .floorLevel(UniformFloat.of(-1.0f, -0.4f))
            .horizontalRadiusMultiplier(UniformFloat.of(0.7f, 1.4f))
            .lavaLevel(VerticalAnchor.aboveBottom(8))
            .replaceable(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .verticalRadiusMultiplier(UniformFloat.of(0.8f, 1.3f))
            .y(UniformHeight.of(
                VerticalAnchor.aboveBottom(8),
                VerticalAnchor.absolute(47)
            ))
            .yScale(UniformFloat.of(0.1f, 0.9f))
            .build();
        register(cave_extra);
    }
}
