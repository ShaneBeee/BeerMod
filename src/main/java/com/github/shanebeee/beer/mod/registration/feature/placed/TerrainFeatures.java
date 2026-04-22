package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class TerrainFeatures {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition brown_concrete_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_BROWN_CONCRETE_DISK, reg.getContext())
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
        reg.register(brown_concrete_disk);

        PlacedFeatureDefinition diorite_cliff = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_DIORITE_CLIFFS, reg.getContext())
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
        reg.register(diorite_cliff);

        PlacedFeatureDefinition mossify_grass = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_MOSSIFY_GRASS, reg.getContext())
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
        reg.register(mossify_grass);

        @SuppressWarnings("deprecation")
        PlacedFeatureDefinition lush_plains_lake = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_LUSH_PLAINS_LAKE, reg.getContext())
            .configuredFeature(Feature.LAKE, new LakeFeature.Configuration(
                BlockStateProvider.simple(Blocks.WATER.defaultBlockState()),
                BlockStateProvider.simple(Blocks.SAND.defaultBlockState())))
            .placementModifiers(RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        reg.register(lush_plains_lake);

        PlacedFeatureDefinition sand_shore_disk = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_SAND_SHORE_DISK, reg.getContext())
            .configuredFeature(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK)
            .placementModifiers(CountPlacement.of(50),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(ConstantHeight.of(VerticalAnchor.absolute(62))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)),
                BiomeFilter.biome())
            .build();
        reg.register(sand_shore_disk);

        PlacedFeatureDefinition cliff_feature = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_STONE_CLIFF, reg.getContext())
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
        reg.register(cliff_feature);

        PlacedFeatureDefinition oasis_patch = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_OASIS_PATCH, reg.getContext())
            .configuredFeature(Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                Blocks.SAND.defaultBlockState(),
                Blocks.GRASS_BLOCK.defaultBlockState(),
                UniformInt.of(2, 4)
            ))
            .placementModifiers(
                CountPlacement.of(16),
                InSquarePlacement.spread(),
                NoiseBasedCountPlacement.of(1, 150, -0.72f),
                CountPlacement.of(32),
                RandomOffsetPlacement.of(UniformInt.of(-5, 5), ConstantInt.of(0)),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.absolute(63), VerticalAnchor.absolute(77))),
                BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.SAND, Blocks.GRASS_BLOCK)),
                BiomeFilter.biome())
            .build();
        reg.register(oasis_patch);

        PlacedFeatureDefinition water_blob = PlacedFeatureDefinition.builder(PlacedFeatures.TERRAIN_WATER_BLOB, reg.getContext())
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
        reg.register(water_blob);
    }

}
