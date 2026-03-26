package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.StructureDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomeTags;
import com.github.shanebeee.beer.mod.registry.Structures;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.ArrayList;
import java.util.List;

public class StructureRegistration {

    private static final List<StructureDefinition> STRUCTURES = new ArrayList<>();

    public static void registerStructures(BootstrapContext<Structure> context) {
        STRUCTURES.addAll(mineshafts(context));
    }

    @SuppressWarnings("unused")
    public static List<StructureDefinition> getStructureDefinitions() {
        return STRUCTURES;
    }

    private static List<StructureDefinition> mineshafts(BootstrapContext<Structure> context) {
        List<StructureDefinition> structures = new ArrayList<>();

        StructureDefinition mineshaft_spruce = StructureDefinition.jigsawBuilder(Structures.MINESHAFT_SPRUCE, context)
            .biomeTag(BeerBiomeTags.HAS_MINESHAFT_SPRUCE)
            .liquidSettings(LiquidSettings.IGNORE_WATERLOGGING)
            .maxDistanceFromCenter(116, 50)
            .maxDepth(20)
            .startHeight(UniformHeight.of(
                VerticalAnchor.absolute(-40),
                VerticalAnchor.absolute(30)))
            .start(TemplatePools.MINESHAFT_SPRUCE)
            .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
            .terrainAdjustment(TerrainAdjustment.ENCAPSULATE)
            .build();
        structures.add(mineshaft_spruce);

        return structures;
    }

}
