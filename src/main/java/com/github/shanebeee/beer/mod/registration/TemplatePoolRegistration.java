package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.TemplatePoolDefinition;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.List;

public class TemplatePoolRegistration {

    private static final List<TemplatePoolDefinition> TEMPLATE_POOLS = new ArrayList<>();

    public static void registerPools(BootstrapContext<StructureTemplatePool> context) {
        TEMPLATE_POOLS.addAll(mineshafts(context));
    }

    @SuppressWarnings("unused")
    public static List<TemplatePoolDefinition> getTemplatePoolDefinitions() {
        return TEMPLATE_POOLS;
    }

    private static List<TemplatePoolDefinition> mineshafts(BootstrapContext<StructureTemplatePool> context) {
        List<TemplatePoolDefinition> structures = new ArrayList<>();

        TemplatePoolDefinition mineshaft = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE, context)
            // Halls
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/hall/hall_1"), 50)
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/hall/hall_2"), 70)

            // Levels
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/stair_1"), 9)
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/cross_tower_1"), 15)
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/cross_tower_2"), 15)

            // Junctions
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/cross_1"), 40)
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/cross_2"), 30)
            .addTemplate(StructurePoolElement.legacy("beer:mineshaft/spruce/cross/cross_3"), 30)

            .build();
        structures.add(mineshaft);

        return structures;
    }

}
