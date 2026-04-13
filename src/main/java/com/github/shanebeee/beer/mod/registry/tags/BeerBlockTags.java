package com.github.shanebeee.beer.mod.registry.tags;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BeerBlockTags {

    public static final TagKey<Block> ALT_STONE = register("alt_stone");

    private static TagKey<Block> register(String key) {
        return TagKey.create(Registries.BLOCK, Identifier.parse(Beer.MOD_ID + ":" + key));
    }

}
