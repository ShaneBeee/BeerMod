package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.StructureDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomeTags;
import com.github.shanebeee.beer.mod.registry.Structures;
import com.github.shanebeee.beer.mod.registry.TemplatePools;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

public class StructureRegistration extends BaseRegistration<Structure, StructureDefinition> {

    public StructureRegistration(BootstrapContext<Structure> context) {
        super(Registries.STRUCTURE, context);
        mineshafts(context);
    }

    private void mineshafts(BootstrapContext<Structure> context) {
        StructureDefinition mineshaft_spruce = StructureDefinition.jigsawBuilder(Structures.MINESHAFT_SPRUCE, context)
            .biomeTag(BeerBiomeTags.HAS_MINESHAFT_SPRUCE)
            .liquidSettings(LiquidSettings.IGNORE_WATERLOGGING)
            .maxDistanceFromCenter(48, 30)
            .maxDepth(7)
            .startHeight(UniformHeight.of(
                VerticalAnchor.absolute(-40),
                VerticalAnchor.absolute(30)))
            .start(TemplatePools.MINESHAFT_SPRUCE)
            .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
            .terrainAdjustment(TerrainAdjustment.ENCAPSULATE)
            .build();
        register(mineshaft_spruce);
    }

}
