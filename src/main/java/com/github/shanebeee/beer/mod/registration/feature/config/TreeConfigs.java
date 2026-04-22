package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FallenTreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLogsDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class TreeConfigs {

    public static void register(ConfiguredFeatureRegistration reg) {
        // DEFAULTS
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        WeightedList.Builder<BlockState> litter_small = WeightedList.builder();
        WeightedList.Builder<BlockState> litter_big = WeightedList.builder();

        BlockState litter = Blocks.LEAF_LITTER.defaultBlockState();
        for (int i = 1; i < 5; i++) {
            for (Direction direction : directions) {
                BlockState blockState = litter
                    .setValue(BlockStateProperties.SEGMENT_AMOUNT, i)
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
                if (i < 4) {
                    litter_small.add(blockState, 1);
                }
                litter_big.add(blockState, 1);
            }
        }

        // TREES
        ConfiguredFeatureDefinition acacia_forest = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_ACACIA_FOREST, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new ForkingTrunkPlacer(5, 2, 3),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(acacia_forest);

        ConfiguredFeatureDefinition acacia_forest_litter = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_ACACIA_FOREST_LITTER, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new ForkingTrunkPlacer(5, 2, 3),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new PlaceOnGroundDecorator(150, 3, 3,
                        new WeightedStateProvider(litter_small)),
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(acacia_forest_litter);

        ConfiguredFeatureDefinition baobab_acacia = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_ACACIA, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_LOG),
                new MegaJungleTrunkPlacer(7, 10, 10),
                BlockStateProvider.simple(Blocks.ACACIA_LEAVES),
                new AcaciaFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(0)),
                Optional.of(new MangroveRootPlacer(
                    UniformInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.ACACIA_WOOD),
                    Optional.empty(),
                    new MangroveRootPlacement(
                        HolderSet.empty(),
                        HolderSet.empty(),
                        BlockStateProvider.simple(Blocks.DIRT),
                        5,
                        7,
                        1.0f))),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 3, OptionalInt.empty()),
                BlockStateProvider.simple(Blocks.ACACIA_LOG))
                .build())
            .build();
        reg.register(baobab_acacia);

        ConfiguredFeatureDefinition baobab_jungle = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_JUNGLE, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG),
                new MegaJungleTrunkPlacer(8, 10, 10),
                BlockStateProvider.simple(Blocks.JUNGLE_LEAVES),
                new AcaciaFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(0)),
                Optional.of(new MangroveRootPlacer(
                    UniformInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.JUNGLE_WOOD),
                    Optional.empty(),
                    new MangroveRootPlacement(
                        HolderSet.empty(),
                        HolderSet.empty(),
                        BlockStateProvider.simple(Blocks.DIRT),
                        5,
                        7,
                        1.0f))),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 3, OptionalInt.empty()),
                BlockStateProvider.simple(Blocks.JUNGLE_LOG))
                .build())
            .build();
        reg.register(baobab_jungle);

        ConfiguredFeatureDefinition baobab_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_OAK, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new MegaJungleTrunkPlacer(6, 10, 10),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new AcaciaFoliagePlacer(UniformInt.of(3, 4), ConstantInt.of(0)),
                Optional.of(new MangroveRootPlacer(
                    UniformInt.of(1, 2),
                    BlockStateProvider.simple(Blocks.OAK_WOOD),
                    Optional.empty(),
                    new MangroveRootPlacement(
                        HolderSet.empty(),
                        HolderSet.empty(),
                        BlockStateProvider.simple(Blocks.DIRT),
                        5,
                        7,
                        1.0f))),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 3, OptionalInt.empty()),
                BlockStateProvider.simple(Blocks.OAK_LOG))
                .build())
            .build();
        reg.register(baobab_oak);

        ConfiguredFeatureDefinition bamboo_palm = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAMBOO_PALM, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BAMBOO.defaultBlockState()
                    .setValue(BambooStalkBlock.AGE, 1)
                    .setValue(BambooStalkBlock.STAGE, 1)
                    .setValue(BambooStalkBlock.LEAVES, BambooLeaves.NONE)),
                new StraightTrunkPlacer(2, 2, 0),
                BlockStateProvider.simple(Blocks.AZALEA_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 2),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 1),
                BlockStateProvider.simple(Blocks.GRASS_BLOCK))
                .ignoreVines()
                .build())
            .build();
        reg.register(bamboo_palm);

        ConfiguredFeatureDefinition boababs = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBABS, reg.getContext())
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(ConfiguredFeatures.TREE_BAOBAB_ACACIA)
                    .build().getHolder(), 0.333f),
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(ConfiguredFeatures.TREE_BAOBAB_JUNGLE)
                    .build().getHolder(), 0.333f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(ConfiguredFeatures.TREE_BAOBAB_OAK)
                    .build().getHolder()))
            .build();
        reg.register(boababs);

        ConfiguredFeatureDefinition birch_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BIRCH_SCRUB, reg.getContext())
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.BIRCH_LEAVES))
            .build();
        reg.register(birch_scrub);

        ConfiguredFeatureDefinition cold_swamp_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_COLD_SWAMP_OAK, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(5, 3, 2),
                BlockStateProvider.simple(Blocks.SPRUCE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(new LeaveVineDecorator(0.25f)))
                .build())
            .build();
        reg.register(cold_swamp_oak);

        ConfiguredFeatureDefinition cold_swamp_pale = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_COLD_SWAMP_PALE, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.PALE_OAK_LOG),
                new StraightTrunkPlacer(6, 4, 2),
                BlockStateProvider.simple(Blocks.PALE_OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(new LeaveVineDecorator(0.25f)))
                .build())
            .build();
        reg.register(cold_swamp_pale);

        ConfiguredFeatureDefinition cypress_shallow = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SHALLOW, reg.getContext())
            .config(Feature.TREE, createCyprus(reg.getContext(), 1, 3, 2, 12, 1.0f))
            .build();
        reg.register(cypress_shallow);

        ConfiguredFeatureDefinition cypress_mid = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_MID, reg.getContext())
            .config(Feature.TREE, createCyprus(reg.getContext(), 3, 5, 1, 15, 0f))
            .build();
        reg.register(cypress_mid);

        ConfiguredFeatureDefinition cypress_deep = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_DEEP, reg.getContext())
            .config(Feature.TREE, createCyprus(reg.getContext(), 7, 9, 1, 20, 0f))
            .build();
        reg.register(cypress_deep);

        ConfiguredFeatureDefinition cypress_surface = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SURFACE, reg.getContext())
            .config(Feature.TREE, createCyprus(reg.getContext(), 1, 2, 2, 12, 1.0f))
            .build();
        reg.register(cypress_surface);

        ConfiguredFeatureDefinition cypress_surface_alt = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT, reg.getContext())
            .config(Feature.TREE, createCyprus(reg.getContext(), 1, 2, 1, 12, 0f))
            .build();
        reg.register(cypress_surface_alt);

        ConfiguredFeatureDefinition fallen_stripped_pale_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, reg.getContext())
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG),
                UniformInt.of(6, 9))
                .logDecorators(List.of(new AttachedToLogsDecorator(0.25f,
                    BlockStateProvider.simple(Blocks.MOSS_CARPET),
                    List.of(Direction.UP))))
                .build())
            .build();
        reg.register(fallen_stripped_pale_oak);

        ConfiguredFeatureDefinition fallen_warped_stem = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM, reg.getContext())
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        reg.register(fallen_warped_stem);

        ConfiguredFeatureDefinition fallen_warped_stem_stripped = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_WARPED_STEM, reg.getContext())
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        reg.register(fallen_warped_stem_stripped);

        ConfiguredFeatureDefinition fallen_tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_TALL_OAK, reg.getContext())
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                UniformInt.of(6, 8))
                .build())
            .build();
        reg.register(fallen_tall_oak);

        ConfiguredFeatureDefinition marula = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_MARULA, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(10, 6, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .decorators(List.of(
                    new AttachedToLeavesDecorator(
                        0.001f,
                        0,
                        0,
                        BlockStateProvider.simple(Blocks.HONEY_BLOCK),
                        2,
                        List.of(Direction.DOWN)),
                    new BeehiveDecorator(0.1f),
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(marula);

        ConfiguredFeatureDefinition marula_litter = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_MARULA_LITTER, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(10, 6, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1)),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                .decorators(List.of(
                    new AttachedToLeavesDecorator(
                        0.001f,
                        0,
                        0,
                        BlockStateProvider.simple(Blocks.HONEY_BLOCK),
                        2,
                        List.of(Direction.DOWN)),
                    new PlaceOnGroundDecorator(150, 3, 3,
                        new WeightedStateProvider(litter_small)),
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(marula_litter);

        ConfiguredFeatureDefinition mpingo = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_MPINGO, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
                new ForkingTrunkPlacer(5, 3, 5),
                BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(mpingo);

        ConfiguredFeatureDefinition oak_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_OAK_SCRUB, reg.getContext())
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.OAK_LEAVES))
            .build();
        reg.register(oak_scrub);

        ConfiguredFeatureDefinition olive = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_OLIVE_TREE, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.ACACIA_WOOD.defaultBlockState()),
                new ForkingTrunkPlacer(2, 2, 1),
                BlockStateProvider.simple(Blocks.BIRCH_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.PERSISTENT, false)
                    .setValue(BlockStateProperties.DISTANCE, 7)),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.ACACIA_WOOD))
                .build())
            .build();
        reg.register(olive);

        ConfiguredFeatureDefinition palm_tree = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_PALM_TREE, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.JUNGLE_WOOD),
                new ForkingTrunkPlacer(5, 2, 3),
                SimpleStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.WATERLOGGED, false)
                    .setValue(BlockStateProperties.PERSISTENT, false)
                    .setValue(BlockStateProperties.DISTANCE, 7)),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new CocoaDecorator(0.2f),
                    new BeehiveDecorator(0.03f)))
                .build())
            .build();
        reg.register(palm_tree);

        ConfiguredFeatureDefinition palm_tree_oasis = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_PALM_TREE_OASIS, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.JUNGLE_WOOD),
                new StraightTrunkPlacer(6, 5, 5),
                SimpleStateProvider.simple(Blocks.JUNGLE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.WATERLOGGED, false)
                    .setValue(BlockStateProperties.PERSISTENT, false)
                    .setValue(BlockStateProperties.DISTANCE, 7)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 1, 2),
                BlockStateProvider.simple(Blocks.JUNGLE_WOOD))
                .build())
            .build();
        reg.register(palm_tree_oasis);

        ConfiguredFeatureDefinition red_ivorywood = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_RED_IVORYWOOD, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
                new ForkingTrunkPlacer(6, 2, 3),
                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(red_ivorywood);

        ConfiguredFeatureDefinition red_ivorywood_litter = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_RED_IVORYWOOD_LITTER, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
                new ForkingTrunkPlacer(6, 2, 3),
                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),
                new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new PlaceOnGroundDecorator(150, 3, 3,
                        new WeightedStateProvider(litter_small)),
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.GRASS_BLOCK.defaultBlockState()
                                    .setValue(BlockStateProperties.SNOWY, false), 4)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .build())))))
                .build())
            .build();
        reg.register(red_ivorywood_litter);

        ConfiguredFeatureDefinition spruce_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_SPRUCE_SCRUB, reg.getContext())
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.SPRUCE_LEAVES))
            .build();
        reg.register(spruce_scrub);

        ConfiguredFeatureDefinition stick_plant = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_STICK_PLANT, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_ROOTS),
                new StraightTrunkPlacer(3, 4, 0),
                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.DISTANCE, 7)
                    .setValue(BlockStateProperties.PERSISTENT, false)),

                new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 1))
                .build())
            .build();
        reg.register(stick_plant);

        ConfiguredFeatureDefinition swamp_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_SWAMP_OAK, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new ForkingTrunkPlacer(4, 2, 2),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.PERSISTENT, false)
                    .setValue(BlockStateProperties.DISTANCE, 7)),
                new BlobFoliagePlacer(UniformInt.of(2, 3), ConstantInt.of(2), 2),
                Optional.empty(),
                new TwoLayersFeatureSize(3, 3, 3),
                BlockStateProvider.simple(Blocks.OAK_LOG))
                .decorators(List.of(new LeaveVineDecorator(0.25f)))
                .build())
            .build();
        reg.register(swamp_oak);

        ConfiguredFeatureDefinition tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TALL_OAK_WITH_LITTER, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(8, 4, 3),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 7)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 3),
                Optional.empty(),
                new TwoLayersFeatureSize(1, 0, 1),
                BlockStateProvider.simple(Blocks.OAK_LOG))
                .decorators(List.of(new PlaceOnGroundDecorator(96, 4, 2,
                        new WeightedStateProvider(litter_small.build())),
                    new PlaceOnGroundDecorator(150, 2, 2,
                        new WeightedStateProvider(litter_big.build()))))
                .build())
            .build();
        reg.register(tall_oak);

        ConfiguredFeatureDefinition tall_stripped_pale_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TALL_STRIPPED_PALE_OAK, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG),
                new StraightTrunkPlacer(6, 3, 3),
                SimpleStateProvider.simple(Blocks.FLOWERING_AZALEA_LEAVES),
                new RandomSpreadFoliagePlacer(
                    UniformInt.of(2, 3),
                    ConstantInt.ZERO,
                    UniformInt.of(4, 7),
                    100),
                new TwoLayersFeatureSize(1, 0, 1))
                .decorators(List.of(
                    new BeehiveDecorator(0.002f),
                    new PlaceOnGroundDecorator(150, 3, 4, new WeightedStateProvider(litter_small.build())),
                    new PlaceOnGroundDecorator(90, 3, 4, new WeightedStateProvider(litter_big.build()))
                ))
                .ignoreVines()
                .build())
            .build();
        reg.register(tall_stripped_pale_oak);

        BlockPredicateFilter tropicalPredicate = BlockPredicateFilter.forPredicate(
            BlockPredicate.wouldSurvive(Blocks.ACACIA_SAPLING.defaultBlockState()
                    .setValue(BlockStateProperties.STAGE, 0),
                BlockPos.ZERO));

        ConfiguredFeatureDefinition tropical_forest = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TROPICAL_FOREST, reg.getContext())
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(stick_plant.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.05f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(marula.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.1f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(red_ivorywood.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.1f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(mpingo.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.3f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(acacia_forest.getHolder())
                    .placementModifiers(tropicalPredicate)
                    .build().getHolder()))
            .build();
        reg.register(tropical_forest);

        ConfiguredFeatureDefinition japan_maple = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_JAPANESE_MAPLE, reg.getContext())
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(marula_litter.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.3f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(red_ivorywood_litter.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getHolder(),
                        0.3f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(acacia_forest_litter.getHolder())
                    .placementModifiers(tropicalPredicate)
                    .build().getHolder()))
            .build();
        reg.register(japan_maple);

        ConfiguredFeatureDefinition windswept_pine = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_WINDSWEPT_OAK, reg.getContext())
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.OAK_WOOD),
                new ForkingTrunkPlacer(5, 2, 3),
                SimpleStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.WATERLOGGED, false)
                    .setValue(BlockStateProperties.PERSISTENT, false)
                    .setValue(BlockStateProperties.DISTANCE, 7)),
                new AcaciaFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .decorators(List.of(
                    new AlterGroundDecorator(
                        RuleBasedStateProvider.ifTrueThenProvide(BlockPredicate.matchesTag(BlockTags.BENEATH_TREE_PODZOL_REPLACEABLE),
                            new WeightedStateProvider(WeightedList.<BlockState>builder()
                                .add(Blocks.PODZOL.defaultBlockState(), 1)
                                .add(Blocks.COARSE_DIRT.defaultBlockState(), 1)
                                .add(Blocks.ROOTED_DIRT.defaultBlockState(), 1)
                                .add(Blocks.GRASS_BLOCK.defaultBlockState(), 3)
                                .build()))))
                )
                .build())
            .build();
        reg.register(windswept_pine);
    }

    @SuppressWarnings("deprecation") // Block#builtInRegistryHolder
    private static TreeConfiguration createCyprus(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                  int trunkOffsetYMin, int trunkOffsetYMax,
                                                  int maxRootWidth, int maxRootLength, float randomSkewChance) {
        return new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.ACACIA_WOOD),
            new FancyTrunkPlacer(10, 2, 8),
            BlockStateProvider.simple(Blocks.AZALEA_LEAVES.defaultBlockState()
                .setValue(BlockStateProperties.DISTANCE, 7)
                .setValue(BlockStateProperties.PERSISTENT, false)),
            new MegaJungleFoliagePlacer(UniformInt.of(1, 2), ConstantInt.of(1), 1),
            Optional.of(new MangroveRootPlacer(
                UniformInt.of(trunkOffsetYMin, trunkOffsetYMax),
                BlockStateProvider.simple(Blocks.ACACIA_WOOD),
                Optional.of(new AboveRootPlacement(
                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.MOSS_CARPET.defaultBlockState(), 5)
                        .add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                        .build()
                    ), 1.0f)),
                new MangroveRootPlacement(
                    context.lookup(Registries.BLOCK).getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                    HolderSet.direct(
                        BuiltInRegistries.BLOCK.getOrThrow(Blocks.MUD.builtInRegistryHolder().key()),
                        BuiltInRegistries.BLOCK.getOrThrow(Blocks.MUDDY_MANGROVE_ROOTS.builtInRegistryHolder().key())),
                    BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState()
                        .setValue(BlockStateProperties.AXIS, Direction.Axis.Y)),
                    maxRootWidth,
                    maxRootLength,
                    randomSkewChance))),
            new TwoLayersFeatureSize(2, 0, 2, OptionalInt.of(4)),
            BlockStateProvider.simple(Blocks.ACACIA_LOG))
            .decorators(List.of(new TrunkVineDecorator()))
            .ignoreVines()
            .build();
    }

    @SuppressWarnings("SameParameterValue")
    public static TreeConfiguration createScrub(Block trunk, Block roots, Block leaves) {
        BlockState blockState = leaves.defaultBlockState();
        if (blockState.is(BlockTags.LEAVES)) {
            blockState = blockState.setValue(BlockStateProperties.PERSISTENT, false)
                .setValue(BlockStateProperties.DISTANCE, 7);
        }
        return new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(trunk),
            new StraightTrunkPlacer(1, 1, 0),
            BlockStateProvider.simple(blockState),
            new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 2),
            Optional.empty(),
            new TwoLayersFeatureSize(0, 0, 0),
            BlockStateProvider.simple(roots))
            .build();
    }

}
