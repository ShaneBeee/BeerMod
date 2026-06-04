package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.mod.registry.tags.BeerBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class TagRegistration extends BlockTagsProvider {

    public TagRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        builder(BeerBlockTags.ALT_STONE)
            .add(BlockItemIds.GRANITE, BlockItemIds.DIORITE, BlockItemIds.ANDESITE);

        // Since we're replacing some deepslate with these stones, we should allow their ores
        builder(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
            .add(BlockItemIds.GRANITE, BlockItemIds.DIORITE, BlockItemIds.ANDESITE, BlockItemIds.SMOOTH_BASALT);

        // Prevent leaves from decaying on mangrove roots/bamboo
        builder(BlockTags.PREVENTS_NEARBY_LEAF_DECAY)
            .add(BlockItemIds.MANGROVE_ROOTS, BlockItemIds.BAMBOO);

        // Allow small dripleaf on more blocks
        builder(BlockTags.SUPPORTS_SMALL_DRIPLEAF)
            .add(BlockItemIds.GRASS_BLOCK, BlockItemIds.SAND, BlockItemIds.DIRT);

        // Allow chickens to spawn on stone bricks
        builder(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(BlockItemIds.STONE_BRICKS);
    }

}
