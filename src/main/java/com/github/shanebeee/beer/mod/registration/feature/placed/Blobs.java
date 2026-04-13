package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.tags.BeerBlockTags;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

public class Blobs {

    public static void register(PlacedFeatureRegistration reg) {
        PlacedFeatureDefinition basalt_blobs = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_BASALT,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.SMOOTH_BASALT,
            22, -30, 120, 150);
        reg.register(basalt_blobs);

        PlacedFeatureDefinition blackstone_blobs = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_BLACKSTONE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.BLACKSTONE,
            22, -30, 120, 150);
        reg.register(blackstone_blobs);

        PlacedFeatureDefinition blackstone_bricks = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_BLACKSTONE_BRICKS,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.POLISHED_BLACKSTONE_BRICKS,
            22, -30, 120, 150);
        reg.register(blackstone_bricks);

        PlacedFeatureDefinition dead_brain = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_DEAD_BRAIN,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_BRAIN_CORAL_BLOCK,
            22, -30, 120, 150);
        reg.register(dead_brain);

        PlacedFeatureDefinition dead_bubble = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_DEAD_BUBBLE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_BUBBLE_CORAL_BLOCK,
            22, -40, 120, 150);
        reg.register(dead_bubble);

        PlacedFeatureDefinition dead_fire = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_DEAD_FIRE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.DEAD_FIRE_CORAL_BLOCK,
            22, -50, 120, 150);
        reg.register(dead_fire);

        PlacedFeatureDefinition mossy_stone = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_MOSSY_STONE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.MOSSY_STONE_BRICKS,
            22, -50, 120, 150);
        reg.register(mossy_stone);

        PlacedFeatureDefinition gray_terracotta = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_TERRACOTTA_LIGHT_GRAY,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.LIGHT_GRAY_TERRACOTTA,
            15, -50, 120, 150);
        reg.register(gray_terracotta);

        PlacedFeatureDefinition blue_terracotta = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_TERRACOTTA_LIGHT_BLUE,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.LIGHT_BLUE_TERRACOTTA,
            15, -50, 120, 150);
        reg.register(blue_terracotta);

        PlacedFeatureDefinition stone_blobs = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_STONE,
            Blocks.STONE,
            22, 0, 120, 10);
        reg.register(stone_blobs);

        PlacedFeatureDefinition soulsand = PlacedFeatureDefinition.builder(PlacedFeatures.BLOB_SOULSAND, reg.getContext())
            .configuredFeature(Feature.DISK, new DiskConfiguration(
                BlockStateProvider.simple(Blocks.SOUL_SAND),
                BlockPredicate.solid(),
                ConstantInt.of(1),
                0
            ))
            .placementModifiers(CountPlacement.of(256),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(120)),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN,
                    BlockPredicate.allOf(
                        BlockPredicate.solid(),
                        BlockPredicate.matchesFluids(new Vec3i(0, 1, 0), Fluids.WATER)
                    ),
                    12),
                BiomeFilter.biome())
            .build();
        reg.register(soulsand);

        PlacedFeatureDefinition sulfur_blobs = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_SULFUR,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.YELLOW_TERRACOTTA,
            50, -60, 120, 150);
        reg.register(sulfur_blobs);

        PlacedFeatureDefinition tuff_blobs = createBlob(reg.getContext(),
            PlacedFeatures.BLOB_TUFF,
            BlockTags.BASE_STONE_OVERWORLD,
            Blocks.TUFF,
            24, -20, 120, 12);
        reg.register(tuff_blobs);
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

}
