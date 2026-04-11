package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

@SuppressWarnings("unused")
public class DesertBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> BeerBiomes.DESERT_DRY_DESERT;
            case MODERATE -> BeerBiomes.DESERT_STEPPE_DESERT;
            case SEMI_HUMID -> BeerBiomes.DESERT_CACTUS_FIELDS;
            case HUMID -> BeerBiomes.DESERT_LUSH_DESERT;
        };
    }

}
