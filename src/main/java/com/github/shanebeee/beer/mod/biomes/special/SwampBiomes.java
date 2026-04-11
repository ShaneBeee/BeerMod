package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

@SuppressWarnings("unused")
public class SwampBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv) {
        return switch (temp) {
            case FROZEN, COLD -> BeerBiomes.SWAMP_COLD_SWAMP;
            case TEMPERATE -> BeerBiomes.FOREST_BAYOU;
            case WARM -> BeerBiomes.SWAMP_DRIPLEAF_SWAMP;
            case HOT -> Biomes.MANGROVE_SWAMP;
        };
    }

}
