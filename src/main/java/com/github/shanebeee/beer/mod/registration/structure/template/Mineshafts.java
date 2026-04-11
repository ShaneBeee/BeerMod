package com.github.shanebeee.beer.mod.registration.structure.template;

import com.github.shanebeee.beer.api.registration.TemplatePoolDefinition;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.function.Function;

public class Mineshafts {

    public static void register(TemplatePoolRegistration reg) {
        mineshaftCommon(reg);
        mineshaftSandstone(reg);
        mineshaftSpruce(reg);
    }

    private static void mineshaftCommon(TemplatePoolRegistration reg) {
        TemplatePoolDefinition mineshaft_decorators = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_COMMON_DECORATORS, reg.getContext())
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/decorators/lantern_1"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/decorators/lantern_2"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/decorators/lantern_3"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/decorators/lantern_empty"), 10)
            .build();
        reg.register(mineshaft_decorators);

        TemplatePoolDefinition mineshaft_minecart = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_COMMON_MINECART, reg.getContext())
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/minecart/empty"), 10)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/minecart/looted_cart"), 1)
            .addTemplate(StructurePoolElement.single("beer:mineshaft/common/minecart/empty_cart"), 2)
            .build();
        reg.register(mineshaft_minecart);
    }

    private static void mineshaftSandstone(TemplatePoolRegistration reg) {
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_1 = StructurePoolElement.single("beer:mineshaft/sandstone/hall/hall_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_2 = StructurePoolElement.single("beer:mineshaft/sandstone/hall/hall_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_3 = StructurePoolElement.single("beer:mineshaft/sandstone/hall/hall_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_with_spawner = StructurePoolElement.single("beer:mineshaft/sandstone/hall/hall_with_spawner");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_1 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/stair_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_2 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/stair_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_3 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/stair_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_1 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_tower_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_2 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_tower_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_3 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_tower_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_1 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_2 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_3 = StructurePoolElement.single("beer:mineshaft/sandstone/cross/cross_3");

        TemplatePoolDefinition mineshaft_all = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SANDSTONE_ALL, reg.getContext())
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
        reg.register(mineshaft_all);

        TemplatePoolDefinition mineshaft_hall = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SANDSTONE_HALLS, reg.getContext())
            .addTemplate(hall_1, 1)
            .addTemplate(hall_2, 1)
            .addTemplate(hall_3, 1)
            .build();
        reg.register(mineshaft_hall);

        TemplatePoolDefinition mineshaft_stairs = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SANDSTONE_STAIRS, reg.getContext())
            .addTemplate(cross_tower_1, 1)
            .addTemplate(cross_tower_2, 1)
            .addTemplate(cross_tower_3, 1)
            .build();
        reg.register(mineshaft_stairs);
    }

    private static void mineshaftSpruce(TemplatePoolRegistration reg) {
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_1 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_2 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_3 = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> hall_with_spawner = StructurePoolElement.single("beer:mineshaft/spruce/hall/hall_with_spawner");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> stair_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/stair_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_tower_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_tower_3");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_1 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_1");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_2 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_2");
        Function<StructureTemplatePool.Projection, SinglePoolElement> cross_3 = StructurePoolElement.single("beer:mineshaft/spruce/cross/cross_3");

        TemplatePoolDefinition mineshaft_spruce_all = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_ALL, reg.getContext())
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
        reg.register(mineshaft_spruce_all);

        TemplatePoolDefinition mineshaft_spruce_hall = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_HALLS, reg.getContext())
            .addTemplate(hall_1, 1)
            .addTemplate(hall_2, 1)
            .addTemplate(hall_3, 1)
            .build();
        reg.register(mineshaft_spruce_hall);

        TemplatePoolDefinition mineshaft_spruce_stairs = TemplatePoolDefinition.builder(TemplatePools.MINESHAFT_SPRUCE_STAIRS, reg.getContext())
            .addTemplate(cross_tower_1, 1)
            .addTemplate(cross_tower_2, 1)
            .addTemplate(cross_tower_3, 1)
            .build();
        reg.register(mineshaft_spruce_stairs);
    }

}
