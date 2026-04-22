package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.List;

public class BushFeatures {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition med_bushes = PlacedFeatureDefinition.builder(PlacedFeatures.BUSH_MEDITERRANEAN_BUSHES, reg.getContext())
            .configuredFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(
                List.of(
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_OLIVE_TREE)
                        .build().getHolder(), 0.05f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_BIRCH_SCRUB)
                        .build().getHolder(), 0.6f),
                    new WeightedPlacedFeature(PlacedFeatureDefinition.builder(reg.getContext())
                        .configuredFeature(ConfiguredFeatures.TREE_SPRUCE_SCRUB)
                        .build().getHolder(), 0.4f)),
                PlacedFeatureDefinition.builder(reg.getContext())
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
        reg.register(med_bushes);
    }

}
