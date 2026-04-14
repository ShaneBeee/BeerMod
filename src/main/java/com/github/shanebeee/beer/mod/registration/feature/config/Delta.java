package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CompositeFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.List;

public class Delta {

    public static void register(ConfiguredFeatureRegistration reg) {
        ConfiguredFeatureDefinition basalt_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_DELTA, reg.getContext())
            .config(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.LAVA.defaultBlockState(),
                Blocks.SMOOTH_BASALT.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        reg.register(basalt_delta);

        ConfiguredFeatureDefinition stone_lava = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_STONE_LAVA_DELTA, reg.getContext())
            .config(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.LAVA.defaultBlockState(),
                Blocks.STONE_BRICKS.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        reg.register(stone_lava);

        ConfiguredFeatureDefinition basalt_pool = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_POOL, reg.getContext())
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                reg.getContext().lookup(Registries.BLOCK).getOrThrow(BlockTags.BASE_STONE_OVERWORLD),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.SMOOTH_BASALT.defaultBlockState(), 6)
                    .add(Blocks.STONE_BRICKS.defaultBlockState(), 2)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                    .setValue(LightBlock.LEVEL, 12))))
                            .build().getHolder(), 0.1f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)))
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
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                reg.getContext().lookup(Registries.BLOCK).getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState())
                    .add(Blocks.DEAD_BUBBLE_CORAL_BLOCK.defaultBlockState())
                    .add(Blocks.DEAD_FIRE_CORAL_BLOCK.defaultBlockState())
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                                        .add(Blocks.DEAD_BRAIN_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_BUBBLE_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.DEAD_FIRE_CORAL.defaultBlockState(), 1)
                                        .add(Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 12), 1)
                                        .build())))
                                .build()
                                .getHolder(), 0.3f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(RandomOffsetPlacement.vertical(ConstantInt.of(-1)))
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
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                reg.getContext().lookup(Registries.BLOCK).getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR, new CompositeFeatureConfiguration(
                        HolderSet.direct(
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(),
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
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
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                reg.getContext().lookup(Registries.BLOCK).getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR, new CompositeFeatureConfiguration(
                        HolderSet.direct(
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(),
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
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
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                reg.getContext().lookup(Registries.BLOCK).getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MUD.defaultBlockState(), 3)
                    .add(Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(CaveFeatures.DRIPLEAF)
                                .build().getHolder(), 0.2f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                                    List.of(
                                        new BlockColumnConfiguration.Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)),
                                        new BlockColumnConfiguration.Layer(UniformInt.of(2, 6),
                                            BlockStateProvider.simple(Blocks.MANGROVE_ROOTS))),
                                    Direction.UP,
                                    BlockPredicate.allOf(
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, 0, 1), Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(0, 0, -1), Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(1, 0, 0), Blocks.MANGROVE_ROOTS)),
                                        BlockPredicate.not(BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 0), Blocks.MANGROVE_ROOTS))
                                    ), false))
                                .build().getHolder(), 0.4f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                    BlockStateProvider.simple(Blocks.SEAGRASS)))
                                .build().getHolder(), 0.2f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                    BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                        .setValue(LightBlock.LEVEL, 10))))
                                .build().getHolder(), 0.2f)),

                        PlacedFeatureDefinition.builder()
                            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                BlockStateProvider.simple(Blocks.SOUL_SAND)))
                            .placementModifiers(RandomOffsetPlacement.vertical(ConstantInt.of(-1)))
                            .build().getHolder()))
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

        ConfiguredFeatureDefinition sulfur_pool = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_SULFUR_POOL, reg.getContext())
            .config(Feature.LAKE, new LakeFeature.Configuration(
                BlockStateProvider.simple(Blocks.WATER),
                BlockStateProvider.simple(Blocks.DYED_TERRACOTTA.orange())))
            .build();
        reg.register(sulfur_pool);
    }

}
