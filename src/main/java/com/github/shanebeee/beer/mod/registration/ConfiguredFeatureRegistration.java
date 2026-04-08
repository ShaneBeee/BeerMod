package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration.Layer;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FallenTreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
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
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class ConfiguredFeatureRegistration extends BaseRegistration<ConfiguredFeature<?, ?>, ConfiguredFeatureDefinition> {

    public ConfiguredFeatureRegistration(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        super(Registries.CONFIGURED_FEATURE, context);
        PlacedFeatureDefinition.setupConfiguredFeatureContext(context);
        decor(context);
        delta(context);
        terrain(context);
        tree(context);
        vegetation(context);
    }

    private void decor(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ConfiguredFeatureDefinition basalt_pillar = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DECOR_BASALT_PILLAR, context)
            .config(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new Layer(UniformInt.of(4, 11), BlockStateProvider.simple(Blocks.BASALT))
                ),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                false))
            .build();
        register(basalt_pillar);


        ConfiguredFeatureDefinition muddy_blob = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DECOR_MUDDY_BLOB, context)
            .config(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MUD.defaultBlockState(), 5)
                    .add(Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                                    List.of(
                                        new Layer(UniformInt.of(2, 6),
                                            BlockStateProvider.simple(Blocks.PALE_HANGING_MOSS.defaultBlockState()
                                                .setValue(HangingMossBlock.TIP, false))),
                                        new Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.PALE_HANGING_MOSS))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.25f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                                    List.of(
                                        new Layer(UniformInt.of(1, 2),
                                            BlockStateProvider.simple(Blocks.CAVE_VINES_PLANT.defaultBlockState())),
                                        new Layer(ConstantInt.of(1), new WeightedStateProvider(
                                            WeightedList.<BlockState>builder()
                                                .add(Blocks.CAVE_VINES.defaultBlockState()
                                                    .setValue(CaveVinesBlock.BERRIES, true), 1)
                                                .add(Blocks.CAVE_VINES.defaultBlockState()
                                                    .setValue(CaveVinesBlock.BERRIES, false), 3)
                                                .build()
                                        ))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.025f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                                    List.of(
                                        new Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.HANGING_ROOTS))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.25f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                                List.of(
                                    new Layer(ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)),
                                    new Layer(UniformInt.of(1, 4),
                                        BlockStateProvider.simple(Blocks.MANGROVE_ROOTS))),
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                false)).build().getHolder()
                    )).build().getHolder(),
                CaveSurface.CEILING,
                UniformInt.of(2, 4),
                0.3f,
                3,
                0.25f,
                UniformInt.of(3, 7),
                0.1f
            ))
            .build();
        register(muddy_blob);
    }

    private void delta(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ConfiguredFeatureDefinition basalt_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_DELTA, context)
            .config(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.LAVA.defaultBlockState(),
                Blocks.SMOOTH_BASALT.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        register(basalt_delta);

        ConfiguredFeatureDefinition stone_lava = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_STONE_LAVA_DELTA, context)
            .config(Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(
                Blocks.LAVA.defaultBlockState(),
                Blocks.STONE_BRICKS.defaultBlockState(),
                UniformInt.of(3, 7),
                UniformInt.of(1, 3)))
            .build();
        register(stone_lava);

        ConfiguredFeatureDefinition basalt_pool = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_BASALT_POOL, context)
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.BASE_STONE_OVERWORLD,
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
        register(basalt_pool);

        ConfiguredFeatureDefinition forgotten_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_FORGOTTEN_DELTA, context)
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.MINEABLE_WITH_PICKAXE,
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
        register(forgotten_delta);

        ConfiguredFeatureDefinition moss_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_MOSS_DELTA, context)
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 4)
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 1)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(
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
                UniformInt.of(1, 3),
                0.9f))
            .build();
        register(moss_delta);

        ConfiguredFeatureDefinition muddy_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_MUDDY_DELTA, context)
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
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
                                        new Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)),
                                        new Layer(UniformInt.of(2, 6),
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
        register(muddy_delta);
    }

    private void terrain(BootstrapContext<ConfiguredFeature<?, ?>> entries) {
        ConfiguredFeatureDefinition sand_shore_disk = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK, entries)
            .config(Feature.DISK, new DiskConfiguration(
                RuleBasedStateProvider.simple(Blocks.SAND),
                BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT),
                UniformInt.of(3, 5),
                1))
            .build();
        register(sand_shore_disk);
    }

    private void tree(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        ConfiguredFeatureDefinition acacia_forest = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_ACACIA_FOREST, context)
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
        register(acacia_forest);

        ConfiguredFeatureDefinition baobab_acacia = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_ACACIA, context)
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
        register(baobab_acacia);

        ConfiguredFeatureDefinition baobab_jungle = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_JUNGLE, context)
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
        register(baobab_jungle);

        ConfiguredFeatureDefinition baobab_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBAB_OAK, context)
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
        register(baobab_oak);

        ConfiguredFeatureDefinition bamboo_palm = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAMBOO_PALM, context)
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
        register(bamboo_palm);

        ConfiguredFeatureDefinition boababs = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BAOBABS, context)
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
        register(boababs);

        ConfiguredFeatureDefinition birch_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_BIRCH_SCRUB, context)
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.BIRCH_LEAVES))
            .build();
        register(birch_scrub);

        ConfiguredFeatureDefinition cold_swamp_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_COLD_SWAMP_OAK, context)
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
        register(cold_swamp_oak);

        ConfiguredFeatureDefinition cold_swamp_pale = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_COLD_SWAMP_PALE, context)
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
        register(cold_swamp_pale);

        ConfiguredFeatureDefinition cypress_shallow = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SHALLOW, context)
            .config(Feature.TREE, createCyprus(context, 1, 3, 2, 12, 1.0f))
            .build();
        register(cypress_shallow);

        ConfiguredFeatureDefinition cypress_mid = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_MID, context)
            .config(Feature.TREE, createCyprus(context, 3, 5, 1, 15, 0f))
            .build();
        register(cypress_mid);

        ConfiguredFeatureDefinition cypress_deep = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_DEEP, context)
            .config(Feature.TREE, createCyprus(context, 7, 9, 1, 20, 0f))
            .build();
        register(cypress_deep);

        ConfiguredFeatureDefinition cypress_surface = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SURFACE, context)
            .config(Feature.TREE, createCyprus(context, 1, 2, 2, 12, 1.0f))
            .build();
        register(cypress_surface);

        ConfiguredFeatureDefinition cypress_surface_alt = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT, context)
            .config(Feature.TREE, createCyprus(context, 1, 2, 1, 12, 0f))
            .build();
        register(cypress_surface_alt);

        ConfiguredFeatureDefinition fallen_stripped_pale_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG),
                UniformInt.of(6, 9))
                .logDecorators(List.of(new AttachedToLogsDecorator(0.25f,
                    BlockStateProvider.simple(Blocks.MOSS_CARPET),
                    List.of(Direction.UP))))
                .build())
            .build();
        register(fallen_stripped_pale_oak);

        ConfiguredFeatureDefinition fallen_warped_stem = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        register(fallen_warped_stem);

        ConfiguredFeatureDefinition fallen_warped_stem_stripped = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_WARPED_STEM, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        register(fallen_warped_stem_stripped);

        ConfiguredFeatureDefinition fallen_tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_TALL_OAK, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                UniformInt.of(6, 8))
                .build())
            .build();
        register(fallen_tall_oak);

        ConfiguredFeatureDefinition marula = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_MARULA, context)
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
        register(marula);

        ConfiguredFeatureDefinition mpingo = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_MPINGO, context)
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
        register(mpingo);

        ConfiguredFeatureDefinition oak_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_OAK_SCRUB, context)
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.OAK_LEAVES))
            .build();
        register(oak_scrub);

        ConfiguredFeatureDefinition olive = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_OLIVE_TREE, context)
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
        register(olive);

        ConfiguredFeatureDefinition palm_tree = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_PALM_TREE, context)
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
        register(palm_tree);

        ConfiguredFeatureDefinition red_ivorywood = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_RED_IVORYWOOD, context)
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
        register(red_ivorywood);

        ConfiguredFeatureDefinition spruce_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_SPRUCE_SCRUB, context)
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.SPRUCE_LEAVES))
            .build();
        register(spruce_scrub);

        ConfiguredFeatureDefinition stick_plant = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_STICK_PLANT, context)
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
        register(stick_plant);

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

        ConfiguredFeatureDefinition swamp_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_SWAMP_OAK, context)
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
        register(swamp_oak);

        ConfiguredFeatureDefinition tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TALL_OAK_WITH_LITTER, context)
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
        register(tall_oak);

        ConfiguredFeatureDefinition tall_stripped_pale_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TALL_STRIPPED_PALE_OAK, context)
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
        register(tall_stripped_pale_oak);

        BlockPredicateFilter tropicalPredicate = BlockPredicateFilter.forPredicate(
            BlockPredicate.wouldSurvive(Blocks.ACACIA_SAPLING.defaultBlockState()
                    .setValue(BlockStateProperties.STAGE, 0),
                BlockPos.ZERO));

        ConfiguredFeatureDefinition tropical_forest = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TROPICAL_FOREST, context)
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
        register(tropical_forest);

        ConfiguredFeatureDefinition windswept_pine = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_WINDSWEPT_OAK, context)
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
        register(windswept_pine);
    }

    private void vegetation(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // PREDICATES
        BlockPredicateFilter fernPredicate = BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.FERN.defaultBlockState(), BlockPos.ZERO));

        // AZALEA SCRUB
        ConfiguredFeatureDefinition azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_SCRUB, context)
            .config(Feature.TREE, createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.AZALEA_LEAVES))
            .build();
        register(azalea_scrub);

        // FLOWERING AZALEA SCRUB
        ConfiguredFeatureDefinition flowering_azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_FLOWERING_AZALEA_SCRUB, context)
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.MANGROVE_ROOTS),
                new StraightTrunkPlacer(1, 1, 0),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, false), 2)
                    .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, false), 1)
                    .build()),
                new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 0, 0))
                .build())
            .build();
        register(flowering_azalea_scrub);

        // AZALEA BUSH
        ConfiguredFeatureDefinition azalea_bush = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB, context)
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.AZALEA)))
                    .placementModifiers(fernPredicate)
                    .build()
                    .getHolder(),
                    0.2f),
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FLOWERING_AZALEA)))
                    .placementModifiers(fernPredicate)
                    .build()
                    .getHolder(),
                    0.1f),
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(flowering_azalea_scrub.getHolder())
                    .placementModifiers(fernPredicate)
                    .build()
                    .getHolder(),
                    0.25f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(azalea_scrub.getHolder())
                    .placementModifiers(fernPredicate)
                    .build()
                    .getHolder()))
            .build();
        register(azalea_bush);

        ConfiguredFeatureDefinition desert_cactus = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_DESERT_CACTUS, context)
            .config(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new Layer(UniformInt.of(2, 4), BlockStateProvider.simple(Blocks.CACTUS))
                ),
                Direction.UP,
                BlockPredicate.matchesTag(BlockTags.AIR),
                false))
            .build();
        register(desert_cactus);

        ConfiguredFeatureDefinition desert_cactus_flower = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER, context)
            .config(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new Layer(UniformInt.of(2, 4), BlockStateProvider.simple(Blocks.CACTUS)),
                    new Layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.CACTUS_FLOWER))
                ),
                Direction.UP,
                BlockPredicate.matchesTag(BlockTags.AIR),
                false))
            .build();
        register(desert_cactus_flower);

        List<WeightedPlacedFeature> plants = new ArrayList<>();
        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        for (Direction direction : directions) {
            plants.add(new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                    BlockStateProvider.simple(Blocks.SMALL_DRIPLEAF.defaultBlockState()
                        .setValue(BlockStateProperties.HORIZONTAL_FACING, direction))))
                .build().getHolder(), 0.1125f));
            plants.add(new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                    List.of(new Layer(ConstantInt.of(1),
                            BlockStateProvider.simple(Blocks.BIG_DRIPLEAF_STEM.defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .setValue(BlockStateProperties.WATERLOGGED, true))),
                        new Layer(ConstantInt.of(1),
                            BlockStateProvider.simple(Blocks.BIG_DRIPLEAF.defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .setValue(BlockStateProperties.WATERLOGGED, false)))),
                    Direction.UP,
                    BlockPredicate.alwaysTrue(),
                    false))
                .build().getHolder(), 0.1125f));
        }

        ConfiguredFeatureDefinition lush_river_plants = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_LUSH_RIVER_PLANTS, context)
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                plants,
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS)))
                    .build().getHolder()))
            .build();
        register(lush_river_plants);
    }

    @SuppressWarnings("SameParameterValue")
    private static TreeConfiguration createScrub(Block trunk, Block roots, Block leaves) {
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
}
