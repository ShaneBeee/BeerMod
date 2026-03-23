package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.api.utils.FeatureUtils;
import com.github.shanebeee.beer.api.utils.RegistryUtils;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.MatchingBlockTagPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockBlobConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class PlacedFeatureRegistration {

    private static final List<PlacedFeatureDefinition> FEATURES = new ArrayList<>();

    public static void registerFeatures(BootstrapContext<PlacedFeature> context) {
        RegistryUtils.init(context);
        FEATURES.addAll(decor(context));
        FEATURES.addAll(delta(context));
        FEATURES.addAll(terrain(context));
        FEATURES.addAll(tree(context));
        FEATURES.addAll(context(context));
    }

    public static List<PlacedFeatureDefinition> getPlaceFeatureDefinitions() {
        return FEATURES;
    }

    private static List<PlacedFeatureDefinition> decor(BootstrapContext<PlacedFeature> context) {
        List<PlacedFeatureDefinition> features = new ArrayList<>();
        PlacedFeatureDefinition hanging_fence = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_HANGING_FENCE, context)
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


        features.add(hanging_fence);

        return features;
    }

    private static List<PlacedFeatureDefinition> delta(BootstrapContext<PlacedFeature> context) {
        List<PlacedFeatureDefinition> features = new ArrayList<>();

        PlacedFeatureDefinition seaPickle = PlacedFeatureDefinition.builder()
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                BlockStateProvider.simple(Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.PICKLES, 3))))
            .build();

        PlacedFeatureDefinition sandstoneWall = PlacedFeatureDefinition.builder()
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

        PlacedFeatureDefinition vegetationFeature = PlacedFeatureDefinition.builder()
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(seaPickle.getFeatureHolder(), 0.05f),
                    new WeightedPlacedFeature(sandstoneWall.getFeatureHolder(), 0.8f)),
                sandstoneWall.getFeatureHolder()))
            .placementModifiers(CountPlacement.of(1))
            .build();

        PlacedFeatureDefinition beach_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BEACH_DELTA, context)
            .configuredFeature(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.WATER.defaultBlockState(),
                Blocks.DIORITE.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(0, 2)))
            .placementModifiers(CountPlacement.of(128),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();

        features.add(beach_delta);

        PlacedFeatureDefinition coastal_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_COASTAL_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_MOSS_DELTA)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();


        features.add(coastal_delta);

        PlacedFeatureDefinition lush_desert_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_LUSH_DESERT_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_MOSS_DELTA)
            .placementModifiers(CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();


        features.add(lush_desert_delta);

        PlacedFeatureDefinition dry_cave_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRY_CAVE_DELTA, context)
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.simple(Blocks.SANDSTONE),
                vegetationFeature.getFeatureHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                0.8f,
                5,
                0.1f,
                UniformInt.of(4, 7),
                0.7f))

            .placementModifiers(CountPlacement.of(62),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                BiomeFilter.biome())
            .build();


        features.add(dry_cave_delta);

        PlacedFeatureDefinition dripleaf_swamp_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRIPLEAF_SWAMP_DELTA, context)
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.DIRT,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 10)
                    .add(Blocks.MUD.defaultBlockState(), 3)
                    .add(Blocks.CLAY.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 3)
                    .build()),
                Holder.direct(new PlacedFeature(RegistryUtils.getConfiguredFeatureReference(CaveFeatures.DRIPLEAF), List.of())),
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


        features.add(dripleaf_swamp_delta);

        PlacedFeatureDefinition swamp_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_SWAMP_DELTA, context)
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


        features.add(swamp_delta);

        return features;
    }

    private static List<PlacedFeatureDefinition> terrain(BootstrapContext<PlacedFeature> context) {
        List<PlacedFeatureDefinition> features = new ArrayList<>();

        PlacedFeatureDefinition brown_concrete_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_BROWN_CONCRETE_DISK, context)
            .configuredFeature(Feature.DISK, new DiskConfiguration(
                new RuleBasedStateProvider(
                    BlockStateProvider.simple(Blocks.DEAD_BRAIN_CORAL_BLOCK),
                    List.of(new RuleBasedStateProvider.Rule(
                        BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD),
                        BlockStateProvider.simple(Blocks.BROWN_CONCRETE_POWDER)))),
                BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD),
                UniformInt.of(2, 6),
                2))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-10), VerticalAnchor.absolute(256)),
                BiomeFilter.biome())
            .build();
        features.add(brown_concrete_disk);

        PlacedFeatureDefinition diorite_cliff = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_DIORITE_CLIFFS, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                BlockStateProvider.simple(Blocks.DIORITE)))
            .placementModifiers(
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.anyOf(
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.STONE),
                            BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.AIR, Blocks.WATER))),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.STONE),
                            BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR, Blocks.DIORITE))),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.STONE),
                            BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.AIR))),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT),
                            BlockPredicate.anyOf(
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK))),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK))),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK))),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK))))))),
                CountPlacement.of(256),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(5, 10),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome()
            )
            .build();
        features.add(diorite_cliff);

        PlacedFeatureDefinition grass_to_sand = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_GRASS_TO_SAND, context)
            .configuredFeature(Feature.DISK, new DiskConfiguration(
                new RuleBasedStateProvider(
                    BlockStateProvider.simple(Blocks.SAND),
                    List.of(new RuleBasedStateProvider.Rule(
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.DIRT),
                        BlockStateProvider.simple(Blocks.SAND)))),
                BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK),
                UniformInt.of(2, 6),
                2))
            .placementModifiers(CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();


        features.add(grass_to_sand);

        PlacedFeatureDefinition sand_shore_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_SAND_SHORE_DISK, context)
            .configuredFeature(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK)
            .placementModifiers(CountPlacement.of(50),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
                BiomeFilter.biome())
            .build();


        features.add(sand_shore_disk);

        PlacedFeatureDefinition cliff_feature = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_STONE_CLIFF, context)
            .configuredFeature(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.COBBLESTONE.defaultBlockState(), 7)
                        .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3)
                        .add(Blocks.STONE_BRICKS.defaultBlockState(), 2)
                        .add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 1)
                        .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                        .build()
                    )
                )
            )
            .placementModifiers(
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.anyOf(
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.STONE),
                            BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.AIR, Blocks.WATER)
                            )),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.STONE, Blocks.DIRT),
                            BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR, Blocks.OAK_WOOD)
                            )),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.STONE, Blocks.DIRT),
                            BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.AIR)
                            )),
                        BlockPredicate.allOf(
                            BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT),
                            BlockPredicate.anyOf(
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.GRASS_BLOCK)
                                ),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.GRASS_BLOCK)
                                ),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.GRASS_BLOCK)
                                ),
                                BlockPredicate.allOf(
                                    BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR, Blocks.WATER),
                                    BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.GRASS_BLOCK)
                                )
                            )
                        )
                    )
                ),
                CountPlacement.of(256),
                RandomOffsetPlacement.ofTriangle(5, 10),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome()
            )
            .build();
        features.add(cliff_feature);

        PlacedFeatureDefinition stone_to_ice = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_STONE_TO_ICE, context)
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                FeatureUtils.createBlobReplace(Blocks.STONE,
                Blocks.BLUE_ICE, 5, 2, 2))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(70)),
                BiomeFilter.biome())
            .build();
        features.add(stone_to_ice);

        PlacedFeatureDefinition deepslate_to_ice = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_DEEPSLATE_TO_ICE, context)
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                FeatureUtils.createBlobReplace(Blocks.DEEPSLATE,
                    Blocks.BLUE_ICE, 5, 2, 2))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(0)),
                BiomeFilter.biome())
            .build();
        features.add(deepslate_to_ice);

        PlacedFeatureDefinition water_blob = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_WATER_BLOB, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.WATER)))
            .placementModifiers(BlockPredicateFilter.forPredicate(
                BlockPredicate.allOf(
                    BlockPredicate.matchesTag(BlockTags.DIRT),
                    BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR, Blocks.WATER),
                    BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.DIRT, Blocks.WATER, Blocks.GRASS_BLOCK),
                    BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.DIRT, Blocks.WATER, Blocks.GRASS_BLOCK),
                    BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.DIRT, Blocks.WATER, Blocks.GRASS_BLOCK),
                    BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.DIRT, Blocks.WATER, Blocks.GRASS_BLOCK)
                )),
                CountPlacement.of(100),
                RandomOffsetPlacement.ofTriangle(3,2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        features.add(water_blob);

        return features;
    }

    private static List<PlacedFeatureDefinition> tree(BootstrapContext<PlacedFeature> context) {
        List<PlacedFeatureDefinition> features = new ArrayList<>();

        PlacedFeatureDefinition cold_swamp_tree = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_COLD_SWAMP_TREE, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder().configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getFeatureHolder(),
                        0.5f),
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder().configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_PALE).build().getFeatureHolder(),
                        0.5f)),
                PlacedFeatureDefinition.builder().configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getFeatureHolder()))
            .placementModifiers(CountPlacement.of(new WeightedListInt(
                    WeightedList.of(new Weighted<>(ConstantInt.of(2), 9),
                        new Weighted<>(ConstantInt.of(3), 1)))),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(2),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        features.add(cold_swamp_tree);

        PlacedFeatureDefinition beachy_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BEACHY_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)))
            .build();
        features.add(beachy_palm);

        PlacedFeatureDefinition fallen_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, context)
            .configuredFeature(ConfiguredFeatures.TREE_FALLEN_STRIPPED_PALE_OAK)
            .placementModifiers(RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        features.add(fallen_stripped_pale_oak);

        PlacedFeatureDefinition fallen_warped_stem = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_WARPED_STEM, context)
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacedFeatureDefinition.builder()
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM)
                        .build().getFeatureHolder(),
                    PlacedFeatureDefinition.builder()
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_STRIPPED_WARPED_STEM)
                        .build().getFeatureHolder())))
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();


        features.add(fallen_warped_stem);

        PlacedFeatureDefinition moss_garden = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_MOSS_GARDEN, context)
            .configuredFeature(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.WARPED_STEM.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y)),
                new DarkOakTrunkPlacer(8, 3, 1),
                SimpleStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.of(0)))
                .decorators(List.of(new TrunkVineDecorator()))
                .ignoreVines()
                .build())
            .placementModifiers(CountPlacement.of(8),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();


        features.add(moss_garden);

        PlacedFeatureDefinition palm_beach_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_PALM_BEACH_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();


        features.add(palm_beach_palm);

        PlacedFeatureDefinition lush_desert_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_LUSH_DESERT_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();


        features.add(lush_desert_palm);

        PlacedFeatureDefinition tall_oak_with_litter = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_WITH_LITTER, context)
            .configuredFeature(ConfiguredFeatures.TREE_TALL_OAK_WITH_LITTER)
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();

        features.add(tall_oak_with_litter);

        PlacedFeatureDefinition fallen_tall_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_FALLEN_TALL_OAK, context)
            .configuredFeature(ConfiguredFeatures.TREE_FALLEN_TALL_OAK)
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();

        features.add(fallen_tall_oak);

        PlacedFeatureDefinition tall_oaks = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_TREES, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(tall_oak_with_litter.getFeatureHolder(), 0.5f),
                    new WeightedPlacedFeature(fallen_tall_oak.getFeatureHolder(), 0.0125f)),
                tall_oak_with_litter.getFeatureHolder()))
            .placementModifiers(CountPlacement.of(new WeightedListInt(
                    WeightedList.<IntProvider>builder()
                        .add(ConstantInt.of(10), 9)
                        .add(ConstantInt.of(11), 1)
                        .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
            )
            .build();

        features.add(tall_oaks);

        PlacedFeatureDefinition tall_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_STRIPPED_PALE_OAK, context)
            .configuredFeature(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG),
                new StraightTrunkPlacer(10, 3, 0),
                SimpleStateProvider.simple(Blocks.FLOWERING_AZALEA_LEAVES),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(new BeehiveDecorator(0.002f)))
                .ignoreVines()
                .build())
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))
            .build();

        features.add(tall_stripped_pale_oak);

        return features;
    }

    private static List<PlacedFeatureDefinition> context(BootstrapContext<PlacedFeature> context) {
        List<PlacedFeatureDefinition> features = new ArrayList<>();

        PlacedFeatureDefinition azalea_bush_or_scrub = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB, context)
            .configuredFeature(ConfiguredFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB)
            .placementModifiers(NoiseBasedCountPlacement.of(25, 30, 0),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.matchesBlocks(Blocks.AIR),
                    BlockPredicate.wouldSurvive(Blocks.AZALEA.defaultBlockState(), BlockPos.ZERO)
                )),
                BiomeFilter.biome())
            .build();

        features.add(azalea_bush_or_scrub);

        PlacedFeatureDefinition desert_azalea_scrub = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB, context)
            .configuredFeature(ConfiguredFeatures.VEGETATION_AZALEA_SCRUB)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.matchesBlocks(Blocks.AIR),
                    BlockPredicate.matchesTag(new BlockPos(0, -1, 0), BlockTags.AZALEA_GROWS_ON)
                )),
                BiomeFilter.biome())
            .build();

        features.add(desert_azalea_scrub);

        PlacedFeatureDefinition patch = PlacedFeatureDefinition.builder()
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_CARPET.defaultBlockState(), 25)
                    .add(Blocks.SHORT_GRASS.defaultBlockState(), 25)
                    .add(Blocks.TALL_GRASS.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 10)
                    .build())
            ))
            .build();

        PlacedFeatureDefinition moss_patch = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_MOSS_PATCH, context)
            .configuredFeature(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(Blocks.MOSS_BLOCK),
                patch.getFeatureHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.0f,
                5,
                0.3f,
                UniformInt.of(1, 2),
                0.75f))
            .placementModifiers(CountPlacement.of(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES),
                BiomeFilter.biome())
            .build();

        features.add(moss_patch);

        PlacedFeatureDefinition cherry_petals = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_CHERRY_PETALS, context)
            .configuredFeature(VegetationFeatures.FLOWER_CHERRY)
            .placementModifiers(NoiseThresholdCountPlacement.of(-0.8f, 15, 4),
                RarityFilter.onAverageOnceEvery(32),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome())
            .build();

        features.add(cherry_petals);

        PlacedFeatureDefinition cliff_grass = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS, context)
            .configuredFeature(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.GRASS_BLOCK)))
            .placementModifiers(CountPlacement.of(256),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(3, 3),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.aboveBottom(256))),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR),
                        BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.STONE, Blocks.GRANITE, Blocks.GRASS_BLOCK, Blocks.DEEPSLATE, Blocks.CALCITE),
                        BlockPredicate.not(
                            BlockPredicate.anyOf(
                                BlockPredicate.matchesBlocks(new BlockPos(1, -1, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(-1, -1, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, 1), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, -1, -1), Blocks.AIR)
                            )
                        ),
                        BlockPredicate.anyOf(
                            BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(1, 1, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.STONE, Blocks.GRANITE, Blocks.GRASS_BLOCK, Blocks.DEEPSLATE, Blocks.CALCITE)
                            ),
                            BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(-1, 1, 0), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.STONE, Blocks.GRANITE, Blocks.GRASS_BLOCK, Blocks.DEEPSLATE, Blocks.CALCITE)

                            ),
                            BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, 1, 1), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.STONE, Blocks.GRANITE, Blocks.GRASS_BLOCK, Blocks.DEEPSLATE, Blocks.CALCITE)
                            ),
                            BlockPredicate.allOf(
                                BlockPredicate.matchesBlocks(new BlockPos(0, 1, -1), Blocks.AIR),
                                BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.STONE, Blocks.GRANITE, Blocks.GRASS_BLOCK, Blocks.DEEPSLATE, Blocks.CALCITE)
                            )

                        )

                    )
                ))
            .build();
        features.add(cliff_grass);

        PlacedFeatureDefinition hay_bale_patch = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_HAY_BALE, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.HAY_BLOCK)))
            .placementModifiers(BlockPredicateFilter.forPredicate(
                BlockPredicate.allOf(
                    BlockPredicate.matchesBlocks(Blocks.AIR),
                    BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                )),
                CountPlacement.of(96),
                RandomOffsetPlacement.ofTriangle(7, 3),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome())
            .build();
        features.add(hay_bale_patch);

        PlacedFeatureDefinition small_dripleaf = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_SMALL_DRIPLEAF, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(SimpleStateProvider.simple(Blocks.SMALL_DRIPLEAF)))
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)),
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(7, 3),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        features.add(small_dripleaf);

        PlacedFeatureDefinition water_leaves = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_WATER_LEAVES, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.AZALEA_LEAVES.defaultBlockState()
                            .setValue(BlockStateProperties.WATERLOGGED, true)
                            .setValue(BlockStateProperties.PERSISTENT, true),
                        20)
                    .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState()
                            .setValue(BlockStateProperties.WATERLOGGED, true)
                            .setValue(BlockStateProperties.PERSISTENT, true),
                        6)
                    .add(Blocks.MANGROVE_ROOTS.defaultBlockState()
                            .setValue(BlockStateProperties.WATERLOGGED, true),
                        1)
                    .build())))
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(BlockPos.ZERO, Fluids.WATER)),
                CountPlacement.of(32),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(3, 0),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(BlockPos.ZERO, Fluids.WATER)),
                BiomeFilter.biome())
            .build();
        features.add(water_leaves);

        PlacedFeatureDefinition rooted_dirt_blob = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getFeatureHolder(), 0.5f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.ROOTED_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getFeatureHolder()))
            .placementModifiers(CountPlacement.of(UniformInt.of(0, 1)),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome())
            .build();

        features.add(rooted_dirt_blob);

        return features;
    }

}
