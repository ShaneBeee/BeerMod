package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionTypes {

    public static ResourceKey<DimensionType> OVERWORLD = register("minecraft:overworld");

    private static ResourceKey<DimensionType> register(String key) {
        return Beer.getKey(Registries.DIMENSION_TYPE, key);
    }

}
