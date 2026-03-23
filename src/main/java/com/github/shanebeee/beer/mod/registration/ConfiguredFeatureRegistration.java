package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.api.utils.RegistryUtils;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FallenTreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.CocoaDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.PlaceOnGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ConfiguredFeatureRegistration {

    private static final List<ConfiguredFeatureDefinition> FEATURES = new ArrayList<>();

    public static void registerFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RegistryUtils.init(context);
        FEATURES.addAll(delta(context));
        FEATURES.addAll(terrain(context));
        FEATURES.addAll(tree(context));
        FEATURES.addAll(vegetation(context));
    }

    public static @Nullable Holder<ConfiguredFeature<?, ?>> getConfiguredFeature(ResourceKey<ConfiguredFeature<?, ?>> key) {
        for (ConfiguredFeatureDefinition feature : FEATURES) {
            if (feature.getResourceKey().equals(key)) {
                return feature.getHolder();
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static List<ConfiguredFeatureDefinition> getConfiguredFeatureDefinitions() {
        return FEATURES;
    }

    private static List<ConfiguredFeatureDefinition> delta(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        List<ConfiguredFeatureDefinition> features = new ArrayList<>();

        ConfiguredFeatureDefinition moss_delta = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DELTA_MOSS_DELTA, context)
            .config(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.DIRT,
                BlockStateProvider.simple(Blocks.MOSS_BLOCK),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(
                        HolderSet.direct(
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                    BlockStateProvider.simple(Blocks.SMALL_DRIPLEAF)))
                                .build().getFeatureHolder(),
                            PlacedFeatureDefinition.builder()
                                .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                                    BlockStateProvider.simple(Blocks.SEAGRASS)))
                                .build().getFeatureHolder())))
                    .build().getFeatureHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                1,
                0.1f,
                ConstantInt.of(5),
                0.1f))
            .build();
        features.add(moss_delta);

        return features;
    }

    private static List<ConfiguredFeatureDefinition> terrain(BootstrapContext<ConfiguredFeature<?, ?>> entries) {
        List<ConfiguredFeatureDefinition> features = new ArrayList<>();

        ConfiguredFeatureDefinition sand_shore_disk = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK, entries)
            .config(Feature.DISK, new DiskConfiguration(
                RuleBasedStateProvider.simple(Blocks.SAND),
                BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT),
                UniformInt.of(3, 5),
                1))
            .build();
        features.add(sand_shore_disk);

        return features;
    }

    private static List<ConfiguredFeatureDefinition> tree(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        List<ConfiguredFeatureDefinition> features = new ArrayList<>();

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
        features.add(acacia_forest);

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
        features.add(cold_swamp_oak);

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
        features.add(cold_swamp_pale);

        ConfiguredFeatureDefinition fallen_stripped_pale_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_PALE_OAK_LOG),
                UniformInt.of(4, 7))
                .build())
            .build();
        features.add(fallen_stripped_pale_oak);

        ConfiguredFeatureDefinition fallen_warped_stem = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        features.add(fallen_warped_stem);

        ConfiguredFeatureDefinition fallen_warped_stem_stripped = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_STRIPPED_WARPED_STEM, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.STRIPPED_WARPED_STEM),
                UniformInt.of(4, 7))
                .build())
            .build();
        features.add(fallen_warped_stem_stripped);

        ConfiguredFeatureDefinition fallen_tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_FALLEN_TALL_OAK, context)
            .config(Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                UniformInt.of(6, 8))
                .build())
            .build();
        features.add(fallen_tall_oak);

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
        features.add(marula);

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
        features.add(mpingo);

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
        features.add(palm_tree);

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
        features.add(red_ivorywood);

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
        features.add(stick_plant);

        Direction[] directions = new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        WeightedList.Builder<BlockState> a1 = WeightedList.builder();
        WeightedList.Builder<BlockState> a2 = WeightedList.builder();

        BlockState litter = Blocks.LEAF_LITTER.defaultBlockState();
        for (int i = 1; i < 5; i++) {
            for (Direction direction : directions) {
                BlockState blockState = litter
                    .setValue(BlockStateProperties.SEGMENT_AMOUNT, i)
                    .setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
                if (i < 4) {
                    a1.add(blockState, 1);
                }
                a2.add(blockState, 1);
            }
        }

        ConfiguredFeatureDefinition tall_oak = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TREE_TALL_OAK_WITH_LITTER, context)
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(9, 4, 0),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.DISTANCE, 7)),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.ZERO, 3),
                new TwoLayersFeatureSize(2, 0, 2))
                .ignoreVines()
                .decorators(List.of(new PlaceOnGroundDecorator(96, 4, 2,
                        new WeightedStateProvider(a1.build())),
                    new PlaceOnGroundDecorator(150, 2, 2,
                        new WeightedStateProvider(a2.build()))))
                .build())
            .build();
        features.add(tall_oak);

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
                        .getFeatureHolder(),
                        0.05f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(marula.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getFeatureHolder(),
                        0.1f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(red_ivorywood.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getFeatureHolder(),
                        0.1f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                        .configuredFeature(mpingo.getHolder())
                        .placementModifiers(tropicalPredicate)
                        .build()
                        .getFeatureHolder(),
                        0.3f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(acacia_forest.getHolder())
                    .placementModifiers(tropicalPredicate)
                    .build().getFeatureHolder()))
            .build();
        features.add(tropical_forest);

        return features;
    }

    private static List<ConfiguredFeatureDefinition> vegetation(BootstrapContext<ConfiguredFeature<?, ?>> entries) {
        List<ConfiguredFeatureDefinition> features = new ArrayList<>();

        // PREDICATES
        BlockPredicateFilter fernPredicate = BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.FERN.defaultBlockState(), BlockPos.ZERO));

        // AZALEA SCRUB
        ConfiguredFeatureDefinition azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_SCRUB, entries)
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.MANGROVE_ROOTS),
                new StraightTrunkPlacer(1, 1, 0),
                SimpleStateProvider.simple(Blocks.ACACIA_LEAVES.defaultBlockState()
                    .setValue(BlockStateProperties.PERSISTENT, true)),
                new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 0, 0))
                .build())
            .build();
        features.add(azalea_scrub);

        // FLOWERING AZALEA SCRUB
        ConfiguredFeatureDefinition flowering_azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_FLOWERING_AZALEA_SCRUB, entries)
            .config(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                SimpleStateProvider.simple(Blocks.MANGROVE_ROOTS),
                new StraightTrunkPlacer(1, 1, 0),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2)
                    .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 1)
                    .build()),
                new FancyFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(0, 0, 0))
                .build())
            .build();
        features.add(flowering_azalea_scrub);

        // AZALEA BUSH
        ConfiguredFeatureDefinition azalea_bush = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB, entries)
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.AZALEA)))
                    .placementModifiers(fernPredicate)
                    .build()
                    .getFeatureHolder(),
                    0.2f),
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FLOWERING_AZALEA)))
                    .placementModifiers(fernPredicate)
                    .build()
                    .getFeatureHolder(),
                    0.1f),
                new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                    .configuredFeature(flowering_azalea_scrub.getHolder())
                    .placementModifiers(fernPredicate)
                    .build()
                    .getFeatureHolder(),
                    0.25f)),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(azalea_scrub.getHolder())
                    .placementModifiers(fernPredicate)
                    .build()
                    .getFeatureHolder()))
            .build();
        features.add(azalea_bush);

        // RETURN
        return features;
    }

}
