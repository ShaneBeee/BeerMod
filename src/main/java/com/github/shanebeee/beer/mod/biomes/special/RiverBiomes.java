package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

@SuppressWarnings("unused")
public class RiverBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        // Don't use weirdness for rivers as that would split them down the middle
        return switch (temp) {
            case FROZEN -> Biomes.FROZEN_RIVER;
            case COLD -> BeerBiomes.RIVER_COLD_RIVER;
            case TEMPERATE -> BeerBiomes.RIVER_TEMPERATE_RIVER;
            case WARM -> humidity.isHumidOrSemiHumid() ? BeerBiomes.RIVER_LUSH_RIVER : BeerBiomes.RIVER_TEMPERATE_RIVER;
            case HOT -> humidity.isHumidOrSemiHumid() ? BeerBiomes.RIVER_LUSH_RIVER : BeerBiomes.RIVER_DESERT_RIVER;
        };
    }

}
