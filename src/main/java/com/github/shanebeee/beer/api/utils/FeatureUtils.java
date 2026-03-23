package com.github.shanebeee.beer.api.utils;

import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

public class FeatureUtils {

//    public static RandomPatchConfiguration createBlobReplace(BootstrapContext<PlacedFeature> context, Block toReplace) {
//        return new RandomPatchConfiguration(
//            256, 2, 2,
//            PlacedFeatureDefinition.builder()
//                .configuredFeature(Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
//                    toReplace.defaultBlockState(),
//                    Blocks.BLUE_ICE.defaultBlockState(),
//                    UniformInt.of(1, 2)))
//                .placementModifiers(BlockPredicateFilter.forPredicate(
//                    BlockPredicate.allOf(
//                        BlockPredicate.anyOf(
//                            BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.AIR),
//                            BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.AIR),
//                            BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.AIR),
//                            BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.AIR)),
//                        BlockPredicate.matchesTag(BlockTags.BATS_SPAWNABLE_ON),
//                        BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR)))))
//                .build().getFeatureHolder());
//    }

    public static SimpleRandomFeatureConfiguration createBlobReplace(Block toReplace, Block replaceWith, int count, int xzRange, int yRange) {
        return new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                toReplace.defaultBlockState(),
                replaceWith.defaultBlockState(),
                UniformInt.of(1, 2)),
            CountPlacement.of(count),
            RandomOffsetPlacement.ofTriangle(xzRange, yRange))));
    }

}
