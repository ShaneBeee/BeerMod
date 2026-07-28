package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.carver.WorldCarver;

public class Carvers {

    public static ResourceKey<WorldCarver> CAVE = register("minecraft:cave");
    public static ResourceKey<WorldCarver> CAVE_EXTRA = register("minecraft:cave_extra_underground");

    private static ResourceKey<WorldCarver> register(String key) {
        return Beer.getKey(Registries.CARVER, key);
    }

}
