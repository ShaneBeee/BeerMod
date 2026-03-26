package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.StructureSetDefinition;
import com.github.shanebeee.beer.mod.registry.StructureSets;
import com.github.shanebeee.beer.mod.registry.Structures;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StructureSetRegistration {

    private static final List<StructureSetDefinition> STRUCTURES = new ArrayList<>();

    public static void registerStructureSets(BootstrapContext<StructureSet> context) {
        STRUCTURES.addAll(mineshafts(context));
    }

    @SuppressWarnings("unused")
    public static List<StructureSetDefinition> getStructureSetDefinitions() {
        return STRUCTURES;
    }

    private static List<StructureSetDefinition> mineshafts(BootstrapContext<StructureSet> context) {
        List<StructureSetDefinition> structures = new ArrayList<>();

        StructureSetDefinition mineshafts = StructureSetDefinition.builder(StructureSets.MINESHAFTS, context)
            .placement(new RandomSpreadStructurePlacement(
                Vec3i.ZERO,
                StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_3,
                0.004f,
                0,
                Optional.empty(),
                1,
                0,
                RandomSpreadType.LINEAR))
            .addStructure(Structures.MINESHAFT_SPRUCE)
            .build();
        structures.add(mineshafts);

        return structures;
    }

}
