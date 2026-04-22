package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;

public class TerrainConfigs {

    public static void register(ConfiguredFeatureRegistration reg) {
        ConfiguredFeatureDefinition sand_shore_disk = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.TERRAIN_SAND_SHORE_DISK, reg.getContext())
            .config(Feature.DISK, new DiskConfiguration(
                RuleBasedStateProvider.simple(Blocks.SAND),
                BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT),
                UniformInt.of(3, 5),
                1))
            .build();
        reg.register(sand_shore_disk);
    }

}
