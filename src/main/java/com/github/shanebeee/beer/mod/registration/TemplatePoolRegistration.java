package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.TemplatePoolDefinition;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;

import java.util.function.Function;

public class TemplatePoolRegistration extends BaseRegistration<StructureTemplatePool, TemplatePoolDefinition> {

    public TemplatePoolRegistration(BootstrapContext<StructureTemplatePool> context) {
        super(Registries.TEMPLATE_POOL, context);
        mineshafts(context);
    }

    private void mineshafts(BootstrapContext<StructureTemplatePool> context) {
        Function<Projection, SinglePoolElement> hall_1 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_1");
        Function<Projection, SinglePoolElement> hall_2 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_2");
        Function<Projection, SinglePoolElement> hall_3 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_3");
        Function<Projection, SinglePoolElement> hall_with_spawner = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_with_spawner");
        Function<Projection, SinglePoolElement> stair_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_1");
        Function<Projection, SinglePoolElement> stair_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_2");
        Function<Projection, SinglePoolElement> stair_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_3");
        Function<Projection, SinglePoolElement> cross_tower_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_1");
        Function<Projection, SinglePoolElement> cross_tower_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_2");
        Function<Projection, SinglePoolElement> cross_tower_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_3");
        Function<Projection, SinglePoolElement> cross_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_1");
        Function<Projection, SinglePoolElement> cross_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_2");
        Function<Projection, SinglePoolElement> cross_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_3");

        Function<Projection, SinglePoolElement> minecart_empty = StructurePoolElement.single("beer:mineshaft/spruce/minecart/empty");
        Function<Projection, SinglePoolElement> minecart_looted_cart = StructurePoolElement.single("beer:mineshaft/spruce/minecart/looted_cart");
        Function<Projection, SinglePoolElement> minecart_empty_cart = StructurePoolElement.single("beer:mineshaft/spruce/minecart/empty_cart");

        TemplatePoolDefinition mineshaft_spruce_all = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_ALL, context)
            // Halls
            .addTemplate(hall_1, 40)
            .addTemplate(hall_2, 50)
            .addTemplate(hall_3, 40)
            .addTemplate(hall_with_spawner, 10)

            // Levels
            .addTemplate(stair_1, 5)
            .addTemplate(stair_2, 7)
            .addTemplate(stair_3, 7)
            .addTemplate(cross_tower_1, 15)
            .addTemplate(cross_tower_2, 15)
            .addTemplate(cross_tower_3, 15)

            // Junctions
            .addTemplate(cross_1, 40)
            .addTemplate(cross_2, 30)
            .addTemplate(cross_3, 30)

            .build();
        register(mineshaft_spruce_all);

        TemplatePoolDefinition mineshaft_spruce_hall = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_HALLS, context)
            .addTemplate(hall_1, 1)
            .addTemplate(hall_2, 1)
            .addTemplate(hall_3, 1)
            .build();
        register(mineshaft_spruce_hall);

        TemplatePoolDefinition mineshaft_spruce_stairs = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_STAIRS, context)
            .addTemplate(cross_tower_1, 1)
            .addTemplate(cross_tower_2, 1)
            .addTemplate(cross_tower_3, 1)
            .build();
        register(mineshaft_spruce_stairs);

        TemplatePoolDefinition mineshaft_spruce_decorators = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_DECORATORS, context)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/spruce/decorators/lantern_1"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/spruce/decorators/lantern_2"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/spruce/decorators/lantern_3"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/spruce/decorators/lantern_empty"), 10)
            .build();
        register(mineshaft_spruce_decorators);

        TemplatePoolDefinition mineshaft_spruce_minecart = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_MINECART, context)
            .addTemplate(minecart_empty, 10)
            .addTemplate(minecart_looted_cart, 1)
            .addTemplate(minecart_empty_cart, 3)
            .build();
        register(mineshaft_spruce_minecart);
    }

}
