package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.SurfaceRelativeThresholdFilter;

import java.util.List;

public class Replacements {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition grass_to_sand = PlacedFeatureDefinition.builder(PlacedFeatures.REPLACE_GRASS_TO_SAND, reg.getContext())
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
        reg.register(grass_to_sand);

        PlacedFeatureDefinition grass_water_to_sand = PlacedFeatureDefinition.builder(PlacedFeatures.REPLACE_GRASS_UNDER_WATER_TO_SAND, reg.getContext())
            .configuredFeature(Feature.DISK, new DiskConfiguration(
                new RuleBasedStateProvider(
                    null,
                    List.of(
                        new RuleBasedStateProvider.Rule(
                            BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK),
                            BlockStateProvider.simple(Blocks.SAND)))),
                BlockPredicate.matchesBlocks(new Vec3i(0, 1, 0),
                    Blocks.WATER, Blocks.SEAGRASS, Blocks.BIG_DRIPLEAF_STEM),
                UniformInt.of(2, 6),
                2))
            .placementModifiers(CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome())
            .build();
        reg.register(grass_water_to_sand);

        // Diorite
        PlacedFeatureDefinition deepslate_to_diorite = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_DEEPSLATE_TO_DIORITE,
            Blocks.DEEPSLATE, Blocks.DIORITE,
            60, 200, 100, 2, 5);
        reg.register(deepslate_to_diorite);

        PlacedFeatureDefinition stone_to_diorite = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_STONE_TO_DIORITE,
            Blocks.STONE, Blocks.DIORITE,
            1, 100, 100, 5, 7);
        reg.register(stone_to_diorite);

        // Stone
        PlacedFeatureDefinition stone_to_snow = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_STONE_TO_SNOW,
            Blocks.STONE, Blocks.SNOW_BLOCK,
            1, 40, 100, 2, 7);
        reg.register(stone_to_snow);

        PlacedFeatureDefinition stone_to_stone_bricks = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_STONE_TO_STONE_BRICKS,
            Blocks.STONE, Blocks.STONE_BRICKS,
            1, 100, 100, 10, 12);
        reg.register(stone_to_stone_bricks);

        PlacedFeatureDefinition stone_to_ice = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_STONE_TO_ICE,
            Blocks.STONE, Blocks.PACKED_ICE,
            40, 80, 100, 2, 7);
        reg.register(stone_to_ice);

        PlacedFeatureDefinition deepslate_to_ice = createUndergroundReplacement(reg.getContext(), PlacedFeatures.REPLACE_DEEPSLATE_TO_ICE,
            Blocks.DEEPSLATE, Blocks.BLUE_ICE,
            60, 200, 100, 2, 7);
        reg.register(deepslate_to_ice);
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

}
