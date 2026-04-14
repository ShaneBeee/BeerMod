package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.mod.registry.tags.BeerBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
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

        // Prevent leaves from decaying on mangrove roots/bamboo
        valueLookupBuilder(BlockTags.PREVENTS_NEARBY_LEAF_DECAY)
            .add(Blocks.MANGROVE_ROOTS, Blocks.BAMBOO);

        // Allow small dripleaf on more blocks
        valueLookupBuilder(BlockTags.SUPPORTS_SMALL_DRIPLEAF)
            .add(Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.DIRT);

        // Allow chickens to spawn on stone bricks
        valueLookupBuilder(BlockTags.ANIMALS_SPAWNABLE_ON)
            .add(Blocks.STONE_BRICKS);
    }

    private BeerBuilder valueLookupBuilder(TagKey<Block> tag) {
        TagAppender<Block> builder = builder(tag);
        return new BeerBuilder(builder);
    }

    private record BeerBuilder(TagAppender<Block> builder) {

        private BeerBuilder add(Block... blocks) {
            for (Block block : blocks) {
                this.builder.add(block.builtInRegistryHolder().key());
            }
            return this;
        }
    }

}
