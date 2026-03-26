package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.TemplatePoolDefinition;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePoolRegistration extends BaseRegistration<StructureTemplatePool, TemplatePoolDefinition> {

    public TemplatePoolRegistration(BootstrapContext<StructureTemplatePool> context) {
        super(Registries.TEMPLATE_POOL, context);
        mineshafts(context);
    }

    private void mineshafts(BootstrapContext<StructureTemplatePool> context) {
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
        register(mineshaft);
    }

}
