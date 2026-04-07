package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBlockTags;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.data.worldgen.features.PileFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.TrapezoidInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.blockpredicates.MatchingBlockTagPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockBlobConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.BlockPileConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.CompositeFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DeltaFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.CreakingHeartDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
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
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.OptionalInt;

public class PlacedFeatureRegistration extends BaseRegistration<PlacedFeature, PlacedFeatureDefinition> {

    public PlacedFeatureRegistration(BootstrapContext<PlacedFeature> context) {
        super(Registries.PLACED_FEATURE, context);
        blobs(context);
        bushes(context);
        decor(context);
        delta(context);
        replace(context);
        terrain(context);
        tree(context);
        vegetation(context);
    }

    private void blobs(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition basalt_blobs = createBlob(context,
            PlacedFeatures.BLOB_BASALT,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.SMOOTH_BASALT,
            22, -30, 120, 150);
        register(basalt_blobs);

        PlacedFeatureDefinition blackstone_blobs = createBlob(context,
            PlacedFeatures.BLOB_BLACKSTONE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.BLACKSTONE,
            22, -30, 120, 150);
        register(blackstone_blobs);

        PlacedFeatureDefinition blackstone_bricks = createBlob(context,
            PlacedFeatures.BLOB_BLACKSTONE_BRICKS,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.POLISHED_BLACKSTONE_BRICKS,
            22, -30, 120, 150);
        register(blackstone_bricks);

        PlacedFeatureDefinition dead_brain = createBlob(context,
            PlacedFeatures.BLOB_DEAD_BRAIN,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_BRAIN_CORAL_BLOCK,
            22, -30, 120, 150);
        register(dead_brain);

        PlacedFeatureDefinition dead_bubble = createBlob(context,
            PlacedFeatures.BLOB_DEAD_BUBBLE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_BUBBLE_CORAL_BLOCK,
            22, -40, 120, 150);
        register(dead_bubble);

        PlacedFeatureDefinition dead_fire = createBlob(context,
            PlacedFeatures.BLOB_DEAD_FIRE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_FIRE_CORAL_BLOCK,
            22, -50, 120, 150);
        register(dead_fire);

        PlacedFeatureDefinition mossy_stone = createBlob(context,
            PlacedFeatures.BLOB_MOSSY_STONE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.MOSSY_STONE_BRICKS,
            22, -50, 120, 150);
        register(mossy_stone);

        PlacedFeatureDefinition gray_terracotta = createBlob(context,
            PlacedFeatures.BLOB_TERRACOTTA_LIGHT_GRAY,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DYED_TERRACOTTA.lightGray(),
            15, -50, 120, 150);
        register(gray_terracotta);

        PlacedFeatureDefinition blue_terracotta = createBlob(context,
            PlacedFeatures.BLOB_TERRACOTTA_LIGHT_BLUE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DYED_TERRACOTTA.lightBlue(),
            15, -50, 120, 150);
        register(blue_terracotta);

        PlacedFeatureDefinition stone_blobs = createBlob(context,
            PlacedFeatures.BLOB_STONE,
            Blocks.STONE,
            22, 0, 120, 10);
        register(stone_blobs);

        PlacedFeatureDefinition tuff_blobs = createBlob(context,
            PlacedFeatures.BLOB_TUFF,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.TUFF,
            24, -20, 120, 12);
        register(tuff_blobs);
    }

    @SuppressWarnings("SameParameterValue")
    private static PlacedFeatureDefinition createBlob(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                      Block replacement, int size, int minHeight, int maxHeight, int chance) {
        return createBlob(context, key, BeerBlockTags.ALT_STONE, replacement, size, minHeight, maxHeight, chance);
    }

    private static PlacedFeatureDefinition createBlob(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                      TagKey<Block> toReplace, Block replacement, int size, int minHeight,
                                                      int maxHeight, int chance) {
        return PlacedFeatureDefinition.builder(key, context)
            .configuredFeature(Feature.ORE, new OreConfiguration(
                new TagMatchTest(toReplace),
                replacement.defaultBlockState(), size))
            .placementModifiers(CountPlacement.of(chance),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)),
                BiomeFilter.biome())
            .build();
    }

    private void bushes(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition med_bushes = PlacedFeatureDefinition.builder(PlacedFeatures.BUSH_MEDITERRANEAN_BUSHES, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                        .configuredFeature(ConfiguredFeatures.TREE_OLIVE_TREE)
                        .build().getHolder(), 0.05f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                        .configuredFeature(ConfiguredFeatures.TREE_BIRCH_SCRUB)
                        .build().getHolder(), 0.6f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                        .configuredFeature(ConfiguredFeatures.TREE_SPRUCE_SCRUB)
                        .build().getHolder(), 0.4f)),
                PlacedFeatureDefinition.builder(context)
                    .configuredFeature(ConfiguredFeatures.TREE_OAK_SCRUB)
                    .build().getHolder()))
            .placementModifiers(
                NoiseBasedCountPlacement.of(30, 50, 0),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(1, 0, 0), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 0), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, 1), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, 0, -1), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(1, 1, 0), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(-1, 1, 0), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, 1), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, 1, -1), Blocks.AIR),
                        BlockPredicate.matchesBlocks(new Vec3i(0, -1, 0), Blocks.GRASS_BLOCK, Blocks.PACKED_MUD))),
                BiomeFilter.biome())
            .build();
        register(med_bushes);
    }

    private void decor(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition basalt_pillar = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_BASALT_PILLAR, context)
            .configuredFeature(ConfiguredFeatures.DECOR_BASALT_PILLAR)
            .placementModifiers(CountPlacement.of(200),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(200)),
                EnvironmentScanPlacement.scanningFor(Direction.UP,
                    BlockPredicate.hasSturdyFace(Direction.DOWN),
                    BlockPredicate.ONLY_IN_AIR_PREDICATE,
                    12),
                BiomeFilter.biome()
            )
            .build();
        register(basalt_pillar);

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
        register(hanging_fence);

        PlacedFeatureDefinition hanging_stone = PlacedFeatureDefinition.builder(PlacedFeatures.DECOR_HANGING_STONE, context)
            .configuredFeature(Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(
                    new WeightedListInt(WeightedList.<IntProvider>builder()
                        .add(UniformInt.of(3, 10), 2)
                        .add(UniformInt.of(1, 2), 3)
                        .add(UniformInt.of(4, 6), 10)
                        .build()),
                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.MOSSY_STONE_BRICK_WALL.defaultBlockState(), 1)
                        .add(Blocks.STONE_BRICK_WALL.defaultBlockState(), 4)
                        .build()))),
                Direction.DOWN,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                true))
            .placementModifiers(CountPlacement.of(200),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(-1)),
                BiomeFilter.biome())
            .build();
        register(hanging_stone);
    }

    @SuppressWarnings("deprecation")
    // CountOnEveryLayerPlacement (If Mojang removes this, check "minecraft:delta" feature)
    private void delta(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition basalt_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BASALT_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_BASALT_DELTA)
            .placementModifiers(CountOnEveryLayerPlacement.of(1),
                BiomeFilter.biome())
            .build();
        register(basalt_delta);

        PlacedFeatureDefinition basalt_pool = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BASALT_POOL, context)
            .configuredFeature(ConfiguredFeatures.DELTA_BASALT_POOL)
            .placementModifiers(CountOnEveryLayerPlacement.of(2),
                BiomeFilter.biome())
            .build();
        register(basalt_pool);

        PlacedFeatureDefinition sandstoneWall = PlacedFeatureDefinition.builder(context)
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

        PlacedFeatureDefinition vegetationFeature = PlacedFeatureDefinition.builder(context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                        .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                            BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                                .setValue(LightBlock.LEVEL, 12))))
                        .build().getHolder(), 0.05f),
                    new WeightedPlacedFeature(sandstoneWall.getHolder(), 0.8f)),
                sandstoneWall.getHolder()))
            .placementModifiers(CountPlacement.of(1))
            .build();

        PlacedFeatureDefinition beach_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_BEACH_DELTA, context)
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
        register(beach_delta);

        PlacedFeatureDefinition coastal_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_COASTAL_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_MOSS_DELTA)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        register(coastal_delta);

        PlacedFeatureDefinition forgotten_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_FORGOTTEN_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_FORGOTTEN_DELTA)
            .placementModifiers(CountPlacement.of(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(60)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), MatchingBlockTagPredicate.ONLY_IN_AIR_PREDICATE, 12),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(1)),
                BiomeFilter.biome())
            .build();
        register(forgotten_delta);

        PlacedFeatureDefinition lush_desert_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_LUSH_DESERT_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_MOSS_DELTA)
            .placementModifiers(RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                RandomOffsetPlacement.of(ConstantInt.of(0), ConstantInt.of(0)),
                BiomeFilter.biome())
            .build();
        register(lush_desert_delta);

        PlacedFeatureDefinition dry_cave_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRY_CAVE_DELTA, context)
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.LUSH_GROUND_REPLACEABLE),
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
        register(dry_cave_delta);

        PlacedFeatureDefinition dripleaf_swamp_delta = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_DRIPLEAF_SWAMP_DELTA, context)
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.DIRT),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.GRASS_BLOCK.defaultBlockState(), 10)
                    .add(Blocks.MUD.defaultBlockState(), 3)
                    .add(Blocks.CLAY.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 3)
                    .build()),
                PlacedFeatureDefinition.builder(context).configuredFeature(CaveFeatures.DRIPLEAF).build().getHolder(),
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
        register(dripleaf_swamp_delta);

        PlacedFeatureDefinition plain_cave = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_PLAIN_CAVE_DELTA, context)
            .configuredFeature(Feature.WATERLOGGED_VEGETATION_PATCH, new VegetationPatchConfiguration(
                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.STONE_BRICKS.defaultBlockState(), 5)
                    .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 5)
                    .add(Blocks.COBBLESTONE.defaultBlockState(), 2)
                    .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2)
                    .build()),
                PlacedFeatureDefinition.builder(context)
                    .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(Blocks.LIGHT.defaultBlockState()
                            .setValue(LightBlock.LEVEL, 12))))
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
        register(plain_cave);

        PlacedFeatureDefinition stone_lava = PlacedFeatureDefinition.builder(PlacedFeatures.DELTA_STONE_LAVA_DELTA, context)
            .configuredFeature(ConfiguredFeatures.DELTA_STONE_LAVA_DELTA)
            .placementModifiers(CountOnEveryLayerPlacement.of(UniformInt.of(10, 20)),
                BiomeFilter.biome())
            .build();
        register(stone_lava);

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
        register(swamp_delta);
    }

    private void replace(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition grass_to_sand = PlacedFeatureDefinition.builder(PlacedFeatures.REPLACE_GRASS_TO_SAND, context)
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
        register(grass_to_sand);

        // Diorite
        PlacedFeatureDefinition deepslate_to_diorite = createUndergroundReplacement(context, PlacedFeatures.REPLACE_DEEPSLATE_TO_DIORITE,
            Blocks.DEEPSLATE, Blocks.DIORITE,
            60, 200, 100, 2, 5);
        register(deepslate_to_diorite);

        PlacedFeatureDefinition stone_to_diorite = createUndergroundReplacement(context, PlacedFeatures.REPLACE_STONE_TO_DIORITE,
            Blocks.STONE, Blocks.DIORITE,
            1, 100, 100, 5, 7);
        register(stone_to_diorite);

        // Stone
        PlacedFeatureDefinition stone_to_snow = createUndergroundReplacement(context, PlacedFeatures.REPLACE_STONE_TO_SNOW,
            Blocks.STONE, Blocks.SNOW_BLOCK,
            1, 40, 100, 2, 7);
        register(stone_to_snow);

        PlacedFeatureDefinition stone_to_stone_bricks = createUndergroundReplacement(context, PlacedFeatures.REPLACE_STONE_TO_STONE_BRICKS,
            Blocks.STONE, Blocks.STONE_BRICKS,
            1, 100, 100, 10, 12);
        register(stone_to_stone_bricks);

        PlacedFeatureDefinition stone_to_ice = createUndergroundReplacement(context, PlacedFeatures.REPLACE_STONE_TO_ICE,
            Blocks.STONE, Blocks.PACKED_ICE,
            40, 80, 100, 2, 7);
        register(stone_to_ice);

        PlacedFeatureDefinition deepslate_to_ice = createUndergroundReplacement(context, PlacedFeatures.REPLACE_DEEPSLATE_TO_ICE,
            Blocks.DEEPSLATE, Blocks.BLUE_ICE,
            60, 200, 100, 2, 7);
        register(deepslate_to_ice);
    }

    @SuppressWarnings("SameParameterValue")
    private static PlacedFeatureDefinition createUndergroundReplacement(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                        Block target, Block replace, int minDepth, int maxDepth, int chance, int minRadius, int maxRadius) {
        return PlacedFeatureDefinition.builder(key, context)
            .configuredFeature(Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                target.defaultBlockState(),
                replace.defaultBlockState(),
                UniformInt.of(minRadius, maxRadius)))
            .placementModifiers(CountPlacement.of(new WeightedListInt(WeightedList.<IntProvider>builder()
                    .add(ConstantInt.of(chance), 100)
                    .build())),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120)),
                SurfaceRelativeThresholdFilter.of(Heightmap.Types.WORLD_SURFACE_WG, -maxDepth, -minDepth),
                BiomeFilter.biome())
            .build();
    }

    private void terrain(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition brown_concrete_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_BROWN_CONCRETE_DISK, context)
            .configuredFeature(Feature.DISK, new DiskConfiguration(
                new RuleBasedStateProvider(
                    BlockStateProvider.simple(Blocks.DEAD_BRAIN_CORAL_BLOCK),
                    List.of(new RuleBasedStateProvider.Rule(
                        BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD),
                        BlockStateProvider.simple(Blocks.CONCRETE_POWDER.brown())))),
                BlockPredicate.matchesTag(BlockTags.BASE_STONE_OVERWORLD),
                UniformInt.of(2, 6),
                2))
            .placementModifiers(CountPlacement.of(100),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-10), VerticalAnchor.absolute(256)),
                BiomeFilter.biome())
            .build();
        register(brown_concrete_disk);

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
        register(diorite_cliff);

        PlacedFeatureDefinition mossify_grass = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_MOSSIFY_GRASS, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.MOSS_BLOCK)))
            .placementModifiers(CountPlacement.of(128),
                RandomOffsetPlacement.ofTriangle(3, 3),
                BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK),
                        BlockPredicate.anyOf(
                            BlockPredicate.matchesBlocks(new Vec3i(1, 0, 0), Blocks.WATER),
                            BlockPredicate.matchesBlocks(new Vec3i(-1, 0, 0), Blocks.WATER),
                            BlockPredicate.matchesBlocks(new Vec3i(0, 0, 1), Blocks.WATER),
                            BlockPredicate.matchesBlocks(new Vec3i(0, 0, -1), Blocks.WATER)
                        ))),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
            )
            .build();
        register(mossify_grass);

        @SuppressWarnings("deprecation")
        PlacedFeatureDefinition lush_plains_lake = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_LUSH_PLAINS_LAKE, context)
            .configuredFeature(Feature.LAKE, new LakeFeature.Configuration(
                BlockStateProvider.simple(Blocks.WATER.defaultBlockState()),
                BlockStateProvider.simple(Blocks.SAND.defaultBlockState())))
            .placementModifiers(RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        register(lush_plains_lake);

        PlacedFeatureDefinition sand_shore_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_SAND_SHORE_DISK, context)
            .configuredFeature(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK)
            .placementModifiers(CountPlacement.of(50),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
                BiomeFilter.biome())
            .build();
        register(sand_shore_disk);

        PlacedFeatureDefinition cliff_feature = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_STONE_CLIFF, context)
            .configuredFeature(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                    new WeightedStateProvider(WeightedList.<BlockState>builder()
                        .add(Blocks.COBBLESTONE.defaultBlockState(), 7)
                        .add(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 3)
                        .add(Blocks.STONE_BRICKS.defaultBlockState(), 2)
                        .add(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 1)
                        .add(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 1)
                        .build())))
            .placementModifiers(
                CountPlacement.of(2048),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG),
                RandomOffsetPlacement.ofTriangle(5, 10),
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
                BiomeFilter.biome()
            )
            .build();
        register(cliff_feature);

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
                RandomOffsetPlacement.ofTriangle(3, 2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        register(water_blob);
    }

    private void tree(BootstrapContext<PlacedFeature> context) {
        PlacedFeatureDefinition bamboo_jungle = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BAMBOO_JUNGLE_TREES, context)
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
        register(bamboo_jungle);

        PlacedFeatureDefinition baobab = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BAOBABS, context)
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
        register(baobab);

        PlacedFeatureDefinition cold_swamp_tree = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_COLD_SWAMP_TREE, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getHolder(),
                        0.5f),
                    new WeightedPlacedFeature(
                        PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_PALE).build().getHolder(),
                        0.5f)),
                PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_COLD_SWAMP_OAK).build().getHolder()))
            .placementModifiers(CountPlacement.of(new WeightedListInt(
                    WeightedList.of(new Weighted<>(ConstantInt.of(2), 9),
                        new Weighted<>(ConstantInt.of(3), 1)))),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(2),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        register(cold_swamp_tree);

        PlacedFeatureDefinition beachy_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_BEACHY_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)))
            .build();
        register(beachy_palm);

        PlacedFeatureDefinition cypresses = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_CYPRESSES, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                        .configuredFeature(ConfiguredFeatures.TREE_BAMBOO_PALM)
                        .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR)))
                        .build().getHolder(), 0.15f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SHALLOW).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_MID).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_DEEP).build().getHolder(), 0.25f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT).build().getHolder(), 0.15f)),
                PlacedFeatureDefinition.builder(context).configuredFeature(ConfiguredFeatures.TREE_CYPRESS_SURFACE_ALT).build().getHolder()))
            .placementModifiers(CountPlacement.of(40),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)))
            .build();
        register(cypresses);

        PlacedFeatureDefinition desert_river_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_DESERT_RIVER_PALM, context)
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
        register(desert_river_palm);

        PlacedFeatureDefinition fallen_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_STRIPPED_PALE_OAK, context)
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
        register(fallen_stripped_pale_oak);

        PlacedFeatureDefinition fallen_warped_stem = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_FALLEN_WARPED_STEM, context)
            .configuredFeature(Feature.SIMPLE_RANDOM_SELECTOR,
                new CompositeFeatureConfiguration(HolderSet.direct(
                    PlacedFeatureDefinition.builder(context)
                        .configuredFeature(ConfiguredFeatures.TREE_FALLEN_WARPED_STEM)
                        .build().getHolder(),
                    PlacedFeatureDefinition.builder(context)
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
        register(fallen_warped_stem);

        PlacedFeatureDefinition moss_garden = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_MOSS_GARDEN, context)
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
        register(moss_garden);

        PlacedFeatureDefinition palm_beach_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_PALM_BEACH_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        register(palm_beach_palm);

        PlacedFeatureDefinition lush_desert_palm = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_LUSH_DESERT_PALM, context)
            .configuredFeature(ConfiguredFeatures.TREE_PALM_TREE)
            .placementModifiers(RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.DEAD_BUSH.defaultBlockState(), BlockPos.ZERO)),
                BiomeFilter.biome())
            .build();
        register(lush_desert_palm);

        PlacedFeatureDefinition tall_oak_with_litter = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_WITH_LITTER, context)
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
        register(tall_oak_with_litter);

        PlacedFeatureDefinition swamp_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_SWAMP_OAK, context)
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
        register(swamp_oak);

        PlacedFeatureDefinition fallen_tall_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_FALLEN_TALL_OAK, context)
            .configuredFeature(ConfiguredFeatures.TREE_FALLEN_TALL_OAK)
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        register(fallen_tall_oak);

        PlacedFeatureDefinition tall_oaks = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_OAK_TREES, context)
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
        register(tall_oaks);

        PlacedFeatureDefinition tall_stripped_pale_oak = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TALL_STRIPPED_PALE_OAK, context)
            .configuredFeature(ConfiguredFeatures.TREE_TALL_STRIPPED_PALE_OAK)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))
            .build();
        register(tall_stripped_pale_oak);

        PlacedFeatureDefinition tropical_forest = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_TROPICAL_FOREST, context)
            .configuredFeature(ConfiguredFeatures.TREE_TROPICAL_FOREST)
            .placementModifiers(
                NoiseBasedCountPlacement.of(30, 70, 0.75),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(250))),
                BiomeFilter.biome(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING))
            .build();
        register(tropical_forest);

        PlacedFeatureDefinition windswept_pine = PlacedFeatureDefinition.builder(PlacedFeatures.TREE_WINDSWEPT_OAK, context)
            .configuredFeature(ConfiguredFeatures.TREE_WINDSWEPT_OAK)
            .placementModifiers(CountPlacement.of(1),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)))
            .build();
        register(windswept_pine);
    }

    private void vegetation(BootstrapContext<PlacedFeature> context) {
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
        register(azalea_bush_or_scrub);

        PlacedFeatureDefinition bamboo = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_BAMBOO_SOME_PODZOL, context)
            .configuredFeature(VegetationFeatures.BAMBOO_SOME_PODZOL)
            .placementModifiers(
                NoiseBasedCountPlacement.of(100, 80.0f, 0.3f),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        register(bamboo);

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
        register(desert_azalea_scrub);

        PlacedFeatureDefinition lush_river_plants = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_LUSH_RIVER_PLANTS, context)
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
        register(lush_river_plants);

        PlacedFeatureDefinition patch = PlacedFeatureDefinition.builder(context)
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
                context.lookup(Registries.BLOCK).getOrThrow(BlockTags.MOSS_REPLACEABLE),
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
        register(moss_patch);

        PlacedFeatureDefinition moss_veg = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_MOSS_VEGETATION, context)
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
        register(moss_veg);

        PlacedFeatureDefinition cherry_petals = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_CHERRY_PETALS, context)
            .configuredFeature(VegetationFeatures.FLOWER_CHERRY)
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
        register(cherry_petals);

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
        register(cliff_grass);

        PlacedFeatureDefinition small_dripleaf = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PATCH_SMALL_DRIPLEAF, context)
            .configuredFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(SimpleStateProvider.simple(Blocks.SMALL_DRIPLEAF)))
            .placementModifiers(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)),
                CountPlacement.of(10),
                InSquarePlacement.spread(),
                RandomOffsetPlacement.ofTriangle(7, 3),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        register(small_dripleaf);

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
            .placementModifiers(
                CountPlacement.of(64),
                RandomOffsetPlacement.ofTriangle(5, 0),
                SurfaceWaterDepthFilter.forMaxDepth(3),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(BlockPos.ZERO, Fluids.WATER)),
                BiomeFilter.biome())
            .build();
        register(water_leaves);

        PlacedFeatureDefinition pile_azalea = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_AZALEA_LEAVES, context)
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
        register(pile_azalea);

        PlacedFeatureDefinition pile_hay = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_HAY_BALE, context)
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
        register(pile_hay);

        PlacedFeatureDefinition pile_moss = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_MOSS, context)
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
        register(pile_moss);

        PlacedFeatureDefinition pile_melon_pumpkin = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_PILE_MELON_PUMPKIN, context)
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
        register(pile_melon_pumpkin);

        PlacedFeatureDefinition rooted_dirt_blob = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.COARSE_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getHolder(), 0.5f)),
                PlacedFeatureDefinition.builder(context)
                    .configuredFeature(Feature.BLOCK_BLOB, new BlockBlobConfiguration(Blocks.ROOTED_DIRT.defaultBlockState(), BlockPredicate.matchesTag(BlockTags.GRASS_BLOCKS)))
                    .build().getHolder()))
            .placementModifiers(CountPlacement.of(UniformInt.of(0, 1)),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING),
                BiomeFilter.biome())
            .build();
        register(rooted_dirt_blob);

        PlacedFeatureDefinition cactus_fields_cactus = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_CACTUS_FIELDS_CACTUS, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER)
                    .build().getHolder(), 0.335f)),
                PlacedFeatureDefinition.builder(context)
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
        register(cactus_fields_cactus);

        PlacedFeatureDefinition steppe_cactus = PlacedFeatureDefinition.builder(PlacedFeatures.VEGETATION_STEPPE_DESERT_CACTUS, context)
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(new WeightedPlacedFeature(PlacedFeatureDefinition.builder(context)
                    .configuredFeature(ConfiguredFeatures.VEGETATION_DESERT_CACTUS_FLOWER)
                    .build().getHolder(), 0.2f)),
                PlacedFeatureDefinition.builder(context)
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
        register(steppe_cactus);
    }

}
