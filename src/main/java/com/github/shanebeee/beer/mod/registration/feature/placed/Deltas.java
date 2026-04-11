package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.MatchingBlockTagPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.List;

public class Deltas {

    @SuppressWarnings("deprecation")
    // CountOnEveryLayerPlacement (If Mojang removes this, check "minecraft:delta" feature)
    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition basalt_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BASALT_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_BASALT_DELTA)
            .placementModifiers(CountOnEveryLayerPlacement.of(1),
                BiomeFilter.biome())
            .build();
        reg.register(basalt_delta);

        PlacedFeatureDefinition basalt_pool = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BASALT_POOL, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_BASALT_POOL)
            .placementModifiers(CountOnEveryLayerPlacement.of(2),
                BiomeFilter.biome())
            .build();
        reg.register(basalt_pool);

        PlacedFeatureDefinition sandstoneWall = PlacedFeatureDefinition.builder(reg.getContext())
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(
                    new WeightedListInt(WeightedList.<IntProvider>builder()
                        .add(UniformInt.of(0, 19), 2)
                        .add(UniformInt.of(0, 2), 3)
                        .add(UniformInt.of(0, 6), 10)
                        .build()),
                    WeightedStateProvider.simple(Blocks.SANDSTONE_WALL))),
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true))
            .placementModifiers(CountPlacement.of(1))
            .build();

        PlacedFeatureDefinition vegetationFeature = PlacedFeatureDefinition.builder(reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                            BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                .setValue(LightBlock.LEVEL, 12))))
                        .build().getHolder(), 0.05f),
                    new WeightedPlacedFeature(sandstoneWall.getHolder(), 0.8f)),
                sandstoneWall.getHolder()))
            .placementModifiers(CountPlacement.of(1))
            .build();

        PlacedFeatureDefinition beach_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BEACH_DELTA, reg.getContext())
            .configuredFeature(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.WATER.defaultBlockState(),
                Blocks.DIORITE.defaultBlockState(),
                UniformInt.of(4, 8),
                UniformInt.of(0, 2)))
            .placementModifiers(CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        reg.register(beach_delta);

        PlacedFeatureDefinition coastal_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_COASTAL_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_MOSS_DELTA)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        reg.register(coastal_delta);

        PlacedFeatureDefinition forgotten_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_FORGOTTEN_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_FORGOTTEN_DELTA)
            .placementModifiers(CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(60)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                BiomeFilter.biome())
            .build();
        reg.register(forgotten_delta);

        PlacedFeatureDefinition lush_desert_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_LUSH_DESERT_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_LUSH_DESERT_DELTA)
            .placementModifiers(CountPlacement.of(10),
                InSquarePlacement.spread(),
                NoiseBasedCountPlacement.of(1, 150, -0.75f),
                CountPlacement.of(75),
                RandomOffsetPlacement.of(ConstantInt.of(2), ConstantInt.of(0)),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        reg.register(lush_desert_delta);

        PlacedFeatureDefinition muddy_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_MUDDY_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_MUDDY_DELTA)
            .placementModifiers(CountPlacement.of(55),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(60)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                BiomeFilter.biome())
            .build();
        reg.register(muddy_delta);

        PlacedFeatureDefinition dry_cave_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRY_CAVE_DELTA, reg.getContext())
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.SANDSTONE.defaultBlockState(), 10)
                    .add(Blocks.COARSE_DIRT.defaultBlockState(), 3)
                    .add(Blocks.DEAD_BRAIN_CORAL_BLOCK.defaultBlockState(), 1)
                    .add(Blocks.GRAVEL.defaultBlockState(), 3)
                    .build()),
                vegetationFeature.getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                0.8f,
                4,
                0.2f,
                UniformInt.of(4, 7),
                0.7f))
            .placementModifiers(CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-20), VerticalAnchor.absolute(60)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                BiomeFilter.biome())
            .build();
        reg.register(dry_cave_delta);

        PlacedFeatureDefinition dripleaf_swamp_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRIPLEAF_SWAMP_DELTA, reg.getContext())
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.DIRT,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 10)
                    .add(Blocks.MUD.defaultBlockState(), 3)
                    .add(Blocks.CLAY.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 3)
                    .build()),
                PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(CaveFeatures.DRIPLEAF).build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                0.8f,
                5,
                0.2f,
                UniformInt.of(4, 7),
                0.7f))
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        reg.register(dripleaf_swamp_delta);

        PlacedFeatureDefinition plain_cave = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_PLAIN_CAVE_DELTA, reg.getContext())
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.DRIPSTONE_REPLACEABLE,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.STONE_BRICKS.defaultBlockState(), 5)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 5)
                    .add(Blocks.COBBLESTONE.defaultBlockState(), 2)
                    .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2)
                    .build()),
                PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                            .setValue(LightBlock.LEVEL, 5))))
                    .build().getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(2),
                0.0f,
                5,
                0.1f,
                UniformInt.of(4, 7),
                0.01f))
            .placementModifiers(CountPlacement.of(10),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(15), VerticalAnchor.absolute(120)),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        reg.register(plain_cave);

        PlacedFeatureDefinition stone_lava = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_STONE_LAVA_DELTA, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_STONE_LAVA_DELTA)
            .placementModifiers(CountOnEveryLayerPlacement.of(UniformInt.of(10, 20)),
                BiomeFilter.biome())
            .build();
        reg.register(stone_lava);

        PlacedFeatureDefinition sulfur_pool = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_SULFUR_POOL, reg.getContext())
            .configuredFeature(ConfiguredFeatures.DELTA_SULFUR_POOL)
            .placementModifiers(CountPlacement.of(256),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                BlockPredicateFilter.forPredicate(BlockPredicate.solid()),
                EnvironmentScanPlacement.scanningFor(Direction.UP,
                    BlockPredicate.matchesTag(BlockTags.AIR),
                    32),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.YELLOW_TERRACOTTA)),
                BiomeFilter.biome()
            )
            .build();
        reg.register(sulfur_pool);

        PlacedFeatureDefinition swamp_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_SWAMP_DELTA, reg.getContext())
            .configuredFeature(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.WATER.defaultBlockState(),
                Blocks.MUD.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 2)))
            .placementModifiers(CountPlacement.of(64),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        reg.register(swamp_delta);
    }

}
