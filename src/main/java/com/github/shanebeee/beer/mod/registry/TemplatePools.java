package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePools {

    // COMMON
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_COMMON_DECORATORS = register("mineshaft/common/decorators");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_COMMON_MINECART = register("mineshaft/common/minecart");

    // SANDSTONE
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SANDSTONE_ALL = register("mineshaft/sandstone/all");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SANDSTONE_HALLS = register("mineshaft/sandstone/halls");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SANDSTONE_STAIRS = register("mineshaft/sandstone/stairs");

    // SPRUCE
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_ALL = register("mineshaft/spruce/all");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_HALLS = register("mineshaft/spruce/halls");
    public static final ResourceKey<StructureTemplatePool> MINESHAFT_SPRUCE_STAIRS = register("mineshaft/spruce/stairs");

    private static ResourceKey<StructureTemplatePool> register(String key) {
        return Beer.getKey(Registries.TEMPLATE_POOL, key);
    }

}
