package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.List;

public class Decor {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition basalt_pillar = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_BASALT_PILLAR, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DECOR_BASALT_PILLAR)
            .placementModifiers(CountPlacement.of(200),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(200)),
                EnvironmentScanPlacement.scanningFor(Direction.UP,
                    BlockPredicate.hasSturdyFace(Direction.DOWN),
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    12),
                BiomeFilter.biome()
            )
            .build();
        reg.register(basalt_pillar);

        PlacedFeatureDefinition hanging_chain = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_HANGING_CHAIN, reg.getContext())
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new BlockColumnConfiguration.Layer(UniformInt.of(6, 12), BlockStateProvider.simple(Blocks.COPPER_CHAIN.exposed()))
                ),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                false))
            .placementModifiers(CountPlacement.of(256),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(30), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                BiomeFilter.biome())
            .build();
        reg.register(hanging_chain);

        PlacedFeatureDefinition hanging_fence = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_HANGING_FENCE, reg.getContext())
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(
                        new WeightedListInt(WeightedList.<IntProvider>builder()
                            .add(UniformInt.of(1, 10), 2)
                            .add(UniformInt.of(1, 2), 3)
                            .add(UniformInt.of(1, 6), 10)
                            .build()),
                        WeightedStateProvider.simple(Blocks.DARK_OAK_FENCE)),
                    BlockColumnConfiguration.layer(ConstantInt.of(1),
                        new WeightedStateProvider(WeightedList.<BlockState>builder()
                            .add(Blocks.SOUL_LANTERN.defaultBlockState().setValue(BlockStateProperties.HANGING, true), 1)
                            .add(Blocks.LANTERN.defaultBlockState().setValue(BlockStateProperties.HANGING, true), 4)
                            .build()))),
                Direction.DOWN,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                BiomeFilter.biome())
            .build();
        reg.register(hanging_fence);

        PlacedFeatureDefinition hanging_stone = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_HANGING_STONE, reg.getContext())
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(
                    new WeightedListInt(WeightedList.<IntProvider>builder()
                        .add(UniformInt.of(3, 10), 2)
                        .add(UniformInt.of(1, 2), 3)
                        .add(UniformInt.of(4, 6), 10)
                        .build()),
                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), 1)
                        .add(Blocks.STONE_BRICK_WALL.defaultBlockState(), 4)
                        .build()))),
                Direction.DOWN,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true))
            .placementModifiers(CountPlacement.of(200),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                BiomeFilter.biome())
            .build();
        reg.register(hanging_stone);

        PlacedFeatureDefinition muddy_blob = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_MUDDY_BLOB, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DECOR_MUDDY_BLOB)
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                BiomeFilter.biome())
            .build();
        reg.register(muddy_blob);

        PlacedFeatureDefinition smoky_grate = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_SMOKY_GRATE, reg.getContext())
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.SOUL_CAMPFIRE)),
                    new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.COPPER_TRAPDOOR.waxedExposed()))
                ),
                Direction.UP,
                BlockPredicate.alwaysTrue(),
                false))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.hasSturdyFace(Direction.UP), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.solid(new Vec3i(0, 0, 1)),
                        BlockPredicate.solid(new Vec3i(0, 0, -1)),
                        BlockPredicate.solid(new Vec3i(1, 0, 0)),
                        BlockPredicate.solid(new Vec3i(-1, 0, 0))
                    )))
            .build();
        reg.register(smoky_grate);
    }

}
