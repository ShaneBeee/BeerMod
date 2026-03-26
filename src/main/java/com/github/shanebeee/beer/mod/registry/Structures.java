package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class Structures {

    public static ResourceKey<Structure> MINESHAFT_SPRUCE = register("mineshaft/spruce");

    private static ResourceKey<Structure> register(String key) {
        return Beer.getKey(Registries.STRUCTURE, key);
    }

}
