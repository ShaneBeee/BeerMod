package com.github.shanebeee.beer.mod.biomes.continental;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class OceanBiomes {

    public static ResourceKey<Biome> getBiome(boolean deep, int temp, int humidity, int weirdness) {
        return switch (temp) {
            case 0 -> deep ? Biomes.DEEP_FROZEN_OCEAN : Biomes.FROZEN_OCEAN;
            case 1 -> deep ? Biomes.DEEP_COLD_OCEAN : Biomes.COLD_OCEAN;
            case 2 -> deep ? Biomes.DEEP_OCEAN : Biomes.OCEAN;
            case 3 -> deep ? Biomes.DEEP_LUKEWARM_OCEAN : Biomes.LUKEWARM_OCEAN;
            default -> Biomes.WARM_OCEAN;
        };
    }

}
