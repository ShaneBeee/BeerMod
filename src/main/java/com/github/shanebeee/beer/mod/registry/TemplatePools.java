package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePools {

    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE = register("mineshaft/spruce");

    private static ResourceKey<StructureTemplatePool> register(String key) {
        return Beer.getKey(Registries.TEMPLATE_POOL, key);
    }

}
