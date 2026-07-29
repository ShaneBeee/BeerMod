package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.DeltaFeature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.SimpleRandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.OffsetPlacement;

import java.util.List;

public class DeltaConfigs {

    public static void register(ConfiguredFeatureRegistration reg) {
        HolderGetter<Block> blockReg = reg.getContext().lookup(Registries.BLOCK);

        ConfiguredFeatureDefinition basalt_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_DELTA, reg.getContext())
            .config(new DeltaFeature(
                Blocks.LAVA.defaultBlockState(),
                Blocks.SMOOTH_BASALT.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        reg.register(basalt_delta);

        ConfiguredFeatureDefinition stone_lava = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_STONE_LAVA_DELTA, reg.getContext())
            .config(new DeltaFeature(
                Blocks.LAVA.defaultBlockState(),
                Blocks.STONE_BRICKS.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        reg.register(stone_lava);

        ConfiguredFeatureDefinition basalt_pool = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_POOL, reg.getContext())
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.BASE_STONE_OVERWORLD),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.SMOOTH_BASALT.defaultBlockState(), 6)
                    .add(Blocks.STONE_BRICKS.defaultBlockState(), 2)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new RandomSelectorFeature(
                        List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                            .configuredFeature(new SimpleBlockFeature(
                                BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                    .setValue(LightBlock.LEVEL, 12))))
                            .build().getHolder(), 0.1f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(new SimpleBlockFeature(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(OffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)))
                            .build().getHolder()
                    ))
                    .build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(2),
                0.01f,
                2,
                0.7f,
                UniformInt.of(2, 5),
                0.9f))
            .build();
        reg.register(basalt_pool);

        ConfiguredFeatureDefinition forgotten_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_FORGOTTEN_DELTA, reg.getContext())
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.BASE_STONE_OVERWORLD),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState())
                    .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState())
                    .add(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState())
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new RandomSelectorFeature(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new SimpleBlockFeature(
                                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(Blocks.DEAD_BRAIN_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BUBBLE_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_FIRE_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 12), 1)
                                        .build())))
                                .build()
                                .getHolder(), 0.3f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(new SimpleBlockFeature(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(OffsetPlacement.vertical(ConstantInt.of(-1)))
                            .build().getHolder()))
                    .build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                1.0f,
                4,
                1.0f,
                UniformInt.of(4, 7),
                0.7f
            ))
            .build();
        reg.register(forgotten_delta);

        ConfiguredFeatureDefinition lush_desert_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_LUSH_DESERT_DELTA, reg.getContext())
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new SimpleRandomSelectorFeature(
                        HolderSet.direct(
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(),
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(new SimpleBlockFeature(
                                    BlockStateProvider.simple(Blocks.SEAGRASS)))
                                .build().getHolder())))
                    .build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.01f,
                1,
                0.1f,
                UniformInt.of(3, 7),
                0.9f))
            .build();
        reg.register(lush_desert_delta);

        ConfiguredFeatureDefinition moss_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_MOSS_DELTA, reg.getContext())
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new SimpleRandomSelectorFeature(
                        HolderSet.direct(
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(),
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(new SimpleBlockFeature(
                                    BlockStateProvider.simple(Blocks.SEAGRASS)))
                                .build().getHolder())))
                    .build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.01f,
                1,
                0.1f,
                UniformInt.of(3, 7),
                0.9f))
            .build();
        reg.register(moss_delta);

        ConfiguredFeatureDefinition muddy_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_MUDDY_DELTA, reg.getContext())
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MUD.defaultBlockState(), 3)
                    .add(Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new RandomSelectorFeature(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(), 0.2f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new BlockColumnFeature(
                                    List.of(
                                        new BlockColumnFeature.Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)),
                                        new BlockColumnFeature.Layer(UniformInt.of(2, 6),
                                            BlockStateProvider.simple(Blocks.MANGROVE_ROOTS))),
                                    Direction.UP,
                                    BlockPredicate.allOf(
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.NORTH, Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.SOUTH, Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.EAST, Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.WEST, Blocks.MANGROVE_ROOTS))
                                    ), false))
                                .build().getHolder(), 0.4f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new SimpleBlockFeature(
                                    BlockStateProvider.simple(Blocks.SEAGRASS)))
                                .build().getHolder(), 0.2f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new SimpleBlockFeature(
                                    BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                        .setValue(LightBlock.LEVEL, 10))))
                                .build().getHolder(), 0.2f)),

                        PlacedFeatureDefinition.builder()
                            .configuredFeature(new SimpleBlockFeature(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(OffsetPlacement.vertical(ConstantInt.of(-1)))
                            .build().getHolder())
                    )
                    .build().getHolder(),
                CaveSurface.FLOOR,
                UniformInt.of(1, 4),
                0.01f,
                1,
                0.35f,
                UniformInt.of(1, 3),
                0.9f))
            .build();
        reg.register(muddy_delta);
    }

}
