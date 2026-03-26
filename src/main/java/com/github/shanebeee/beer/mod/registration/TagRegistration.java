package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.mod.registry.BeerBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class TagRegistration extends BlockTagsProvider {

    public TagRegistration(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(BeerBlockTags.ALT_STONE)
            .add(Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE);

        // Since we're replacing some deepslate with these stones, we should allow their ores
        valueLookupBuilder(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
            .add(Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.SMOOTH_BASALT);
    }

}
