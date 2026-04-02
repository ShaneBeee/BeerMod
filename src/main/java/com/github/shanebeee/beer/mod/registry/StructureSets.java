package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;

public class StructureSets {

    // Currently none

    private static ResourceKey<StructureSet> register(String key) {
        return Beer.getKey(Registries.STRUCTURE_SET, key);
    }

}
