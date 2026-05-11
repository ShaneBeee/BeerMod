package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

public class Carvers {

    public static ResourceKey<ConfiguredWorldCarver<?>> CAVE = register("minecraft:cave");
    public static ResourceKey<ConfiguredWorldCarver<?>> CAVE_EXTRA = register("minecraft:cave_extra_underground");

    private static ResourceKey<ConfiguredWorldCarver<?>> register(String key) {
        return Beer.getKey(Registries.CONFIGURED_CARVER, key);
    }

}
