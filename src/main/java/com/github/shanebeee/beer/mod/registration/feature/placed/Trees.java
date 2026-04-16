package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CreakingHeartDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.OptionalInt;

public class Trees {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition bamboo_jungle = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BAMBOO_JUNGLE_TREES, reg.getContext())
            .configuredFeature(VegetationFeatures.TREES_JUNGLE)
            .placementModifiers(
                CountPlacement.of(new WeightedListInt(WeightedList.<IntProvider>builder()
                    .add(ConstantInt.of(7), 9)
                    .add(ConstantInt.of(60), 1)
                    .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome())
            .build();
        reg.register(bamboo_jungle);

        PlacedFeatureDefinition baobab = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BAOBABS, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_BAOBABS)
            .placementModifiers(
                CountPlacement.of(new WeightedListInt(WeightedList.<IntProvider>builder()
                    .add(ConstantInt.of(0), 10)
                    .add(ConstantInt.of(1), 9)
                    .add(ConstantInt.of(3), 1)
                    .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome())
            .build();
        reg.register(baobab);

        PlacedFeatureDefinition cold_swamp_tree = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_COLD_SWAMP_TREE, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getHolder(),
                        0.5f),
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_PALE).build().getHolder(),
                        0.5f)),
                PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getHolder()))
            .placementModifiers(CountPlacement.of(new WeightedListInt(
                    WeightedList.of(new Weighted<>(ConstantInt.of(2), 9),
                        new Weighted<>(ConstantInt.of(3), 1)))),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(2),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(cold_swamp_tree);

        PlacedFeatureDefinition cotton_candy = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_COTTON_CANDY_TREES, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_COTTON_CANDY_TREES)
            .placementModifiers(CountPlacement.of(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(cotton_candy);

        PlacedFeatureDefinition beachy_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BEACHY_PALM, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(beachy_palm);

        PlacedFeatureDefinition cypresses = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_CYPRESSES, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_BAMBOO_PALM)
                        .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)))
                        .build().getHolder(), 0.15f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SHALLOW).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_MID).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_DEEP).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT).build().getHolder(), 0.15f)),
                PlacedFeatureDefinition.builder(reg.getContext()).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT).build().getHolder()))
            .placementModifiers(CountPlacement.of(40),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(cypresses);

        PlacedFeatureDefinition dead_corals = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_DEAD_CORAL_TREES, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_DEAD_CORAL_TREES)
            .placementModifiers(CountPlacement.of(40),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Vec3i(0,-1,0), Blocks.GRASS_BLOCK)))
            .build();
        reg.register(dead_corals);

        PlacedFeatureDefinition desert_river_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_DESERT_RIVER_PALM, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(15),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(63), VerticalAnchor.absolute(63)),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Vec3i.ZERO, Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), Blocks.SAND, Blocks.PODZOL, Blocks.ROOTED_DIRT, Blocks.PACKED_MUD),
                        BlockPredicate.anyOf(
                            BlockPredicate.matchesFluids(new Vec3i(0, -1, 1), Fluids.WATER),
                            BlockPredicate.matchesFluids(new Vec3i(0, -1, -1), Fluids.WATER),
                            BlockPredicate.matchesFluids(new Vec3i(1, -1, 0), Fluids.WATER),
                            BlockPredicate.matchesFluids(new Vec3i(-1, -1, 0), Fluids.WATER)
                        ))),
                BiomeFilter.biome())
            .build();
        reg.register(desert_river_palm);

        PlacedFeatureDefinition fallen_crimsom_stem = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_CRIMSON_STEM, reg.getContext())
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_CRIMSOM_STEM)
                        .build().getHolder(),
                    PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_STRIPPED_CRIMSOM_STEM)
                        .build().getHolder())))
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT),
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.GRASS_BLOCKS)
                )),
                BiomeFilter.biome())
            .build();
        reg.register(fallen_crimsom_stem);

        PlacedFeatureDefinition fallen_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_FALLEN_STRIPPED_PALE_OAK)
            .placementModifiers(RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT),
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.GRASS_BLOCKS)
                )),
                BiomeFilter.biome())
            .build();
        reg.register(fallen_stripped_pale_oak);

        PlacedFeatureDefinition fallen_warped_stem = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_WARPED_STEM, reg.getContext())
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(HolderSet.direct(
                    PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM)
                        .build().getHolder(),
                    PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_STRIPPED_WARPED_STEM)
                        .build().getHolder())))
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.DIRT),
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.GRASS_BLOCKS)
                )),
                BiomeFilter.biome())
            .build();
        reg.register(fallen_warped_stem);

        PlacedFeatureDefinition moss_garden = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_MOSS_GARDEN, reg.getContext())
            .configuredFeature(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.WARPED_STEM.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y)),
                new DarkOakTrunkPlacer(8, 3, 1),
                SimpleStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.of(0)))
                .decorators(List.of(new TrunkVineDecorator(), new CreakingHeartDecorator(0.1f)))
                .ignoreVines()
                .build())
            .placementModifiers(CountPlacement.of(8),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(moss_garden);

        PlacedFeatureDefinition moss_garden_island = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_MOSS_GARDEN_ISLAND, reg.getContext())
            .configuredFeature(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.CRIMSON_STEM.defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Y)),
                new DarkOakTrunkPlacer(8, 3, 1),
                SimpleStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.of(0)))
                .decorators(List.of(new TrunkVineDecorator(), new CreakingHeartDecorator(0.1f)))
                .ignoreVines()
                .build())
            .placementModifiers(CountPlacement.of(8),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(moss_garden_island);

        PlacedFeatureDefinition palm_beach_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_PALM_BEACH_PALM, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        reg.register(palm_beach_palm);

        PlacedFeatureDefinition palm_oasis_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_PALM_TREE_OASIS, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE_OASIS)
            .placementModifiers(CountPlacement.of(15),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(0, 0, 1))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(0, 0, -1))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(1, 0, 0))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(-1, 0, 0))),

                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(1, 0, 1))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(1, 0, -1))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(-1, 0, 1))),
                    BlockPredicate.not(BlockPredicate.solid(new Vec3i(-1, 0, -1)))
                )),
                BiomeFilter.biome())
            .build();
        reg.register(palm_oasis_palm);

        PlacedFeatureDefinition lush_desert_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_LUSH_DESERT_PALM, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        reg.register(lush_desert_palm);

        PlacedFeatureDefinition tall_oak_with_litter = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_WITH_LITTER, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_TALL_OAK_WITH_LITTER)
            .placementModifiers(
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO),
                    BlockPredicate.matchesTag(new Vec3i(1, 0, 0), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(-1, 0, 0), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(0, 0, 1), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(0, 0, -1), BlockTags.AIR),

                    BlockPredicate.matchesTag(new Vec3i(1, 0, 1), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(1, 0, -1), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(-1, 0, 1), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(-1, 0, -1), BlockTags.AIR))))
            .build();
        reg.register(tall_oak_with_litter);

        PlacedFeatureDefinition swamp_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_SWAMP_OAK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_SWAMP_OAK)
            .placementModifiers(
                CountPlacement.of(new WeightedListInt(WeightedList.<IntProvider>builder()
                    .add(ConstantInt.of(20), 4)
                    .add(ConstantInt.of(30), 1)
                    .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(2),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(swamp_oak);

        PlacedFeatureDefinition fallen_tall_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_FALLEN_TALL_OAK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_FALLEN_TALL_OAK)
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(fallen_tall_oak);

        PlacedFeatureDefinition tall_oaks = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_TREES, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(tall_oak_with_litter.getHolder(), 0.5f),
                    new WeightedPlacedFeature(fallen_tall_oak.getHolder(), 0.0125f)),
                tall_oak_with_litter.getHolder()))
            .placementModifiers(CountPlacement.of(new WeightedListInt(
                    WeightedList.<IntProvider>builder()
                        .add(ConstantInt.of(15), 9)
                        .add(ConstantInt.of(20), 1)
                        .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
            )
            .build();
        reg.register(tall_oaks);

        PlacedFeatureDefinition tall_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_STRIPPED_PALE_OAK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_TALL_STRIPPED_PALE_OAK)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))
            .build();
        reg.register(tall_stripped_pale_oak);

        PlacedFeatureDefinition tropical_forest = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TROPICAL_FOREST, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_TROPICAL_FOREST)
            .placementModifiers(
                NoiseBasedCountPlacement.of(30, 70, 0.75),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(250))),
                BiomeFilter.biome(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING))
            .build();
        reg.register(tropical_forest);

        PlacedFeatureDefinition windswept_pine = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_WINDSWEPT_OAK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TREE_WINDSWEPT_OAK)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        reg.register(windswept_pine);
    }

}
