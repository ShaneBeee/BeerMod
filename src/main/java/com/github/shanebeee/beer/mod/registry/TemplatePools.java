package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePools {

    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_ALL = register("mineshaft/spruce/all");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_HALLS = register("mineshaft/spruce/halls");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_STAIRS = register("mineshaft/spruce/stairs");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_DECORATORS = register("mineshaft/spruce/decorators");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_MINECART = register("mineshaft/spruce/minecart");

    private static ResourceKey<StructureTemplatePool> register(String key) {
        return Beer.getKey(Registries.TEMPLATE_POOL, key);
    }

}
