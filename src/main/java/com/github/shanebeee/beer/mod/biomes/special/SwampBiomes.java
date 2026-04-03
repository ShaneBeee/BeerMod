package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

@SuppressWarnings("unused")
public class SwampBiomes {

    public static ResourceKey<Biome> getBiome(int temp, int humidity, int weirdness) {
        return switch (temp) {
            case 0 -> BeerBiomes.SWAMP_COLD_SWAMP;
            case 1 -> Biomes.SWAMP;
            case 3 -> BeerBiomes.SWAMP_DRIPLEAF_SWAMP;
            case 4 -> Biomes.MANGROVE_SWAMP;
            default -> BeerBiomes.FOREST_BAYOU;
        };
    }

}
