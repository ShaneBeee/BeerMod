package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class RiverBiomes {

    public static ResourceKey<Biome> getBiome(int temp, int humidity, int weirdness) {
        return switch (temp) {
            case 0 -> Biomes.FROZEN_RIVER;
            case 3 -> BeerBiomes.RIVER_LUSH_RIVER;
            case 4 -> humidity > 3 ? BeerBiomes.RIVER_LUSH_RIVER : BeerBiomes.RIVER_DESERT_RIVER;
            default -> BeerBiomes.RIVER_TEMPERATE_RIVER;
        };
    }

}
