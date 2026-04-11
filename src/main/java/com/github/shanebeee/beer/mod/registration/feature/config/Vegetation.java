package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;

import java.util.ArrayList;
import java.util.List;

public class Vegetation {

    public static void register(ConfiguredFeatureRegistration reg) {
        // PREDICATES
        BlockPredicateFilter fernPredicate = BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.FERN.defaultBlockState(), BlockPos.ZERO));

        // AZALEA SCRUB
        ConfiguredFeatureDefinition azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_SCRUB, reg.getContext())
            .config(Feature.TREE, Tree.createScrub(Blocks.MANGROVE_ROOTS, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.AZALEA_LEAVES))
            .build();
        reg.register(azalea_scrub);

        // FLOWERING AZALEA SCRUB
        ConfiguredFeatureDefinition flowering_azalea_scrub = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_FLOWERING_AZALEA_SCRUB, reg.getContext())
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
        reg.register(flowering_azalea_scrub);

        // AZALEA BUSH
        ConfiguredFeatureDefinition azalea_bush = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB, reg.getContext())
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
        reg.register(azalea_bush);

        ConfiguredFeatureDefinition desert_cactus = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_DESERT_CACTUS, reg.getContext())
            .config(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new BlockColumnConfiguration.Layer(UniformInt.of(2, 4), BlockStateProvider.simple(Blocks.CACTUS))
                ),
                Direction.UP,
                BlockPredicate.matchesTag(BlockTags.AIR),
                false))
            .build();
        reg.register(desert_cactus);

        ConfiguredFeatureDefinition desert_cactus_flower = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER, reg.getContext())
            .config(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(
                    new BlockColumnConfiguration.Layer(UniformInt.of(2, 4), BlockStateProvider.simple(Blocks.CACTUS)),
                    new BlockColumnConfiguration.Layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.CACTUS_FLOWER))
                ),
                Direction.UP,
                BlockPredicate.matchesTag(BlockTags.AIR),
                false))
            .build();
        reg.register(desert_cactus_flower);

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
                    List.of(new BlockColumnConfiguration.Layer(ConstantInt.of(1),
                            BlockStateProvider.simple(Blocks.BIG_DRIPLEAF_STEM.defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .setValue(BlockStateProperties.WATERLOGGED, true))),
                        new BlockColumnConfiguration.Layer(ConstantInt.of(1),
                            BlockStateProvider.simple(Blocks.BIG_DRIPLEAF.defaultBlockState()
                                .setValue(BlockStateProperties.HORIZONTAL_FACING, direction)
                                .setValue(BlockStateProperties.WATERLOGGED, false)))),
                    Direction.UP,
                    BlockPredicate.alwaysTrue(),
                    false))
                .build().getHolder(), 0.1125f));
        }

        ConfiguredFeatureDefinition lush_river_plants = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.VEGETATION_LUSH_RIVER_PLANTS, reg.getContext())
            .config(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                plants,
                PlacedFeatureDefinition.builder()
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS)))
                    .build().getHolder()))
            .build();
        reg.register(lush_river_plants);
    }

}
