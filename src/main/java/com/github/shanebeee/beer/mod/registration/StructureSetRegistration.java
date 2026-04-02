package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.StructureSetDefinition;
import com.github.shanebeee.beer.mod.registry.Structures;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.Optional;

public class StructureSetRegistration extends BaseRegistration<StructureSet, StructureSetDefinition> {

    public StructureSetRegistration(BootstrapContext<StructureSet> context) {
        super(Registries.STRUCTURE_SET, context);
        mineshafts(context);
    }

    private void mineshafts(BootstrapContext<StructureSet> context) {
        StructureSetDefinition mineshafts = StructureSetDefinition.builder(BuiltinStructureSets.MINESHAFTS, context)
            .placement(new RandomSpreadStructurePlacement(
                Vec3i.ZERO,
                StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_3,
                0.004f,
                0,
                Optional.empty(),
                1,
                0,
                RandomSpreadType.LINEAR))
            .addStructure(Structures.MINESHAFT_SANDSTONE)
            .addStructure(Structures.MINESHAFT_SPRUCE)
            .addStructure(BuiltinStructures.MINESHAFT)
            .addStructure(BuiltinStructures.MINESHAFT_MESA)
            .build();
        register(mineshafts);
    }

}
