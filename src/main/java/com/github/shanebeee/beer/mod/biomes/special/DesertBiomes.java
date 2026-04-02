package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class DesertBiomes {

    @SuppressWarnings("unused")
    public static ResourceKey<Biome> getBiome(int temp, int humidity, int weirdness) {
        if (humidity <= 1) {
            return BeerBiomes.DESERT_DRY_DESERT;
        } else if (humidity == 3) {
            return BeerBiomes.DESERT_CACTUS_FIELDS;
        } else if (humidity == 4) {
            return BeerBiomes.DESERT_LUSH_DESERT;
        }
        return BeerBiomes.DESERT_STEPPE_DESERT;
    }

}
