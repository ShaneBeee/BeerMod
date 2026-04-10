package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class BadlandBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> weirdness.isWeird() ? Biomes.ERODED_BADLANDS : Biomes.BADLANDS;
            case MODERATE -> Biomes.BADLANDS;
            default -> Biomes.WOODED_BADLANDS;
        };
    }

}
