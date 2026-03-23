package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public class Dimensions {

    public static ResourceKey<LevelStem> BEER_WORLD = register("world");

    private static ResourceKey<LevelStem> register(String key) {
        return Beer.getKey(Registries.LEVEL_STEM, key);
    }

}
