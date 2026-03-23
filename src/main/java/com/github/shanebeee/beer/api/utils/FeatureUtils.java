package com.github.shanebeee.beer.api.utils;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ReplaceSphereConfiguration;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FeatureUtils {

    public static RandomPatchConfiguration createBlobReplace(BootstrapContext<PlacedFeature> context, Block toReplace) {
        return new RandomPatchConfiguration(
            256, 2, 2,
            PlacedFeatureDefinition.builder()
                .configuredFeature(Feature.REPLACE_BLOBS, new ReplaceSphereConfiguration(
                    toReplace.defaultBlockState(),
                    Blocks.BLUE_ICE.defaultBlockState(),
                    UniformInt.of(1, 2)))
                .placementModifiers(BlockPredicateFilter.forPredicate(
                    BlockPredicate.allOf(
                        BlockPredicate.anyOf(
                            BlockPredicate.matchesBlocks(new BlockPos(1, 0, 0), Blocks.AIR),
                            BlockPredicate.matchesBlocks(new BlockPos(-1, 0, 0), Blocks.AIR),
                            BlockPredicate.matchesBlocks(new BlockPos(0, 0, 1), Blocks.AIR),
                            BlockPredicate.matchesBlocks(new BlockPos(0, 0, -1), Blocks.AIR)),
                        BlockPredicate.matchesTag(BlockTags.BATS_SPAWNABLE_ON),
                        BlockPredicate.not(BlockPredicate.matchesBlocks(new BlockPos(0, 1, 0), Blocks.AIR)))))
                .build().getFeatureHolder());
    }

}
