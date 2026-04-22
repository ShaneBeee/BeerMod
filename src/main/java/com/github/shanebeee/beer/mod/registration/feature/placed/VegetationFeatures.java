package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockBlobConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
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

import java.util.List;

public class VegetationFeatures {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition azalea_bush_or_scrub = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB, reg.getContext())
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
        reg.register(azalea_bush_or_scrub);

        PlacedFeatureDefinition bamboo = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_BAMBOO_SOME_PODZOL, reg.getContext())
            .configuredFeature(net.minecraft.data.worldgen.features.VegetationFeatures.BAMBOO_SOME_PODZOL)
            .placementModifiers(
                NoiseBasedCountPlacement.of(100, 80.0f, 0.3f),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        reg.register(bamboo);

        PlacedFeatureDefinition desert_azalea_scrub = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB, reg.getContext())
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
        reg.register(desert_azalea_scrub);

        PlacedFeatureDefinition lush_river_plants = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_LUSH_RIVER_PLANTS, reg.getContext())
            .configuredFeature(ConfiguredFeatures.VEGETATION_LUSH_RIVER_PLANTS)
            .placementModifiers(
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(61), VerticalAnchor.absolute(62))),
                CountPlacement.of(128),
                InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.matchesFluids(Fluids.WATER),
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.SUPPORTS_SMALL_DRIPLEAF)
                )),
                BiomeFilter.biome())
            .build();
        reg.register(lush_river_plants);

        PlacedFeatureDefinition patch = PlacedFeatureDefinition.builder(reg.getContext())
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MOSS_CARPET.defaultBlockState(), 25)
                    .add(Blocks.SHORT_GRASS.defaultBlockState(), 25)
                    .add(Blocks.TALL_GRASS.defaultBlockState().setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 10)
                    .build())
            ))
            .build();

        PlacedFeatureDefinition moss_patch = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_MOSS_PATCH, reg.getContext())
            .configuredFeature(Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(Blocks.MOSS_BLOCK),
                patch.getHolder(),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0.0f,
                5,
                0.3f,
                UniformInt.of(1, 2),
                0.75f))
            .placementModifiers(CountPlacement.of(3),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        reg.register(moss_patch);

        PlacedFeatureDefinition moss_veg = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_MOSS_VEGETATION, reg.getContext())
            .configuredFeature(CaveFeatures.MOSS_VEGETATION)
            .placementModifiers(CountPlacement.of(128),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                RandomOffsetPlacement.of(
                    TrapezoidInt.of(-6, 6, 0),
                    TrapezoidInt.of(-2, 2, 0)),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                    BlockPredicate.matchesTag(BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(0, -1, 0), BlockTags.AZALEA_GROWS_ON),
                    BlockPredicate.matchesTag(new Vec3i(1, 0, 0), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(-1, 0, 0), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(0, 0, 1), BlockTags.AIR),
                    BlockPredicate.matchesTag(new Vec3i(0, 0, -1), BlockTags.AIR)
                )))
            .build();
        reg.register(moss_veg);

        PlacedFeatureDefinition cherry_petals = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_CHERRY_PETALS, reg.getContext())
            .configuredFeature(net.minecraft.data.worldgen.features.VegetationFeatures.FLOWER_CHERRY)
            .placementModifiers(NoiseThresholdCountPlacement.of(-0.8f, 15, 4),
                InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(32),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome(),
                CountPlacement.of(96),
                RandomOffsetPlacement.of(
                    TrapezoidInt.of(-6, 6, 0),
                    TrapezoidInt.of(-2, 2, 0)),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesTag(BlockTags.AIR)))
            .build();
        reg.register(cherry_petals);

        PlacedFeatureDefinition cliff_grass = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS, reg.getContext())
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
        reg.register(cliff_grass);

        BootstrapContext<PlacedFeature> context = reg.getContext();
        PlacedFeatureDefinition oasis_flowers = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_OASIS_FLOWERS, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    getWeightedFeature(context, net.minecraft.data.worldgen.features.VegetationFeatures.SUNFLOWER, 0.1f),
                    getWeightedFeature(context, net.minecraft.data.worldgen.features.VegetationFeatures.WILDFLOWER, 0.2f),
                    getWeightedFeature(context, net.minecraft.data.worldgen.features.VegetationFeatures.FIREFLY_BUSH, 0.2f),
                    getWeightedFeature(context, net.minecraft.data.worldgen.features.VegetationFeatures.FLOWER_CHERRY, 0.1f)
                ),
                getFeature(context, net.minecraft.data.worldgen.features.VegetationFeatures.FLOWER_FLOWER_FOREST)
            ))
            .placementModifiers(CountPlacement.of(52),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(7, 3),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), Blocks.GRASS_BLOCK)),
                BiomeFilter.biome())
            .build();
        reg.register(oasis_flowers);

        PlacedFeatureDefinition small_dripleaf = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_SMALL_DRIPLEAF, reg.getContext())
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(SimpleStateProvider.simple(Blocks.SMALL_DRIPLEAF)))
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)),
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(7, 3),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        reg.register(small_dripleaf);

        PlacedFeatureDefinition water_leaves = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_WATER_LEAVES, reg.getContext())
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
            .placementModifiers(
                CountPlacement.of(64),
                RandomOffsetPlacement.ofTriangle(5, 0),
                SurfaceWaterDepthFilter.forMaxDepth(3),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(BlockPos.ZERO, Fluids.WATER)),
                BiomeFilter.biome())
            .build();
        reg.register(water_leaves);

        PlacedFeatureDefinition pile_azalea = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_AZALEA_LEAVES, reg.getContext())
            .configuredFeature(Feature.BLOCK_PILE,
                new BlockPileConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 1)
                    .add(Blocks.AZALEA_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 4))))
            .placementModifiers(
                CountPlacement.of(10),
                RandomOffsetPlacement.ofTriangle(7, 3),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                    )),
                BiomeFilter.biome())
            .build();
        reg.register(pile_azalea);

        PlacedFeatureDefinition pile_hay = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_HAY_BALE, reg.getContext())
            .configuredFeature(PileFeatures.PILE_HAY)
            .placementModifiers(
                CountPlacement.of(5),
                RandomOffsetPlacement.ofTriangle(7, 3),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                    )),
                BiomeFilter.biome())
            .build();
        reg.register(pile_hay);

        PlacedFeatureDefinition pile_moss = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_MOSS, reg.getContext())
            .configuredFeature(Feature.BLOCK_PILE,
                new BlockPileConfiguration(BlockStateProvider.simple(Blocks.MOSS_BLOCK)))
            .placementModifiers(
                CountPlacement.of(9),
                RandomOffsetPlacement.ofTriangle(7, 3),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                    )),
                BiomeFilter.biome())
            .build();
        reg.register(pile_moss);

        PlacedFeatureDefinition pile_melon_pumpkin = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_MELON_PUMPKIN, reg.getContext())
            .configuredFeature(Feature.BLOCK_PILE,
                new BlockPileConfiguration(new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.PUMPKIN.defaultBlockState(), 25)
                    .add(Blocks.MELON.defaultBlockState(), 25))))
            .placementModifiers(
                CountPlacement.of(10),
                RandomOffsetPlacement.ofTriangle(7, 3),
                RarityFilter.onAverageOnceEvery(50),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.GRASS_BLOCK)
                    )),
                BiomeFilter.biome())
            .build();
        reg.register(pile_melon_pumpkin);

        PlacedFeatureDefinition rooted_dirt_blob = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getHolder(), 0.5f)),
                PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.ROOTED_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getHolder()))
            .placementModifiers(CountPlacement.of(UniformInt.of(0, 1)),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome())
            .build();
        reg.register(rooted_dirt_blob);

        PlacedFeatureDefinition cactus_fields_cactus = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_CACTUS_FIELDS_CACTUS, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER)
                    .build().getHolder(), 0.335f)),
                PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS)
                    .build().getHolder()))
            .placementModifiers(
                RarityFilter.onAverageOnceEvery(3),
                CountPlacement.of(UniformInt.of(3, 7)),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        reg.register(cactus_fields_cactus);

        PlacedFeatureDefinition steppe_cactus = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_STEPPE_DESERT_CACTUS, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER)
                    .build().getHolder(), 0.2f)),
                PlacedFeatureDefinition.builder(reg.getContext())
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS)
                    .build().getHolder()))
            .placementModifiers(
                RarityFilter.onAverageOnceEvery(5),
                CountPlacement.of(UniformInt.of(2, 5)),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        reg.register(steppe_cactus);
    }

    private static Holder<PlacedFeature> getFeature(BootstrapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> key) {
        return PlacedFeatureDefinition.builder(context).configuredFeature(key).build().getHolder();
    }

    private static WeightedPlacedFeature getWeightedFeature(BootstrapContext<PlacedFeature> context, ResourceKey<ConfiguredFeature<?, ?>> key, float weight) {
        return new WeightedPlacedFeature(getFeature(context, key), weight);
    }

}
