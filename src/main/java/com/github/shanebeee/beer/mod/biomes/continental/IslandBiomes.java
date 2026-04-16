package com.github.shanebeee.beer.mod.biomes.continental;

import com.github.shanebeee.beer.api.biome.Continentalness;
import com.github.shanebeee.beer.api.biome.Erosion;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("unused")
public class IslandBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        if (temp.isFrozen()) {
            return BeerBiomes.ISLAND_FORGOTTEN_ISLAND;
        } else if (temp.isCold()) {
            return BeerBiomes.ISLAND_MOSS_GARDEN_ISLAND;
        } else if (temp.isTemperate()) {
            if (pv.isLow() || pv.isHigh()) {
                return BeerBiomes.ISLAND_COTTON_CANDY_ISLAND_ALT;
            }
            return BeerBiomes.ISLAND_COTTON_CANDY_ISLAND;
        }
        return Biomes.MUSHROOM_FIELDS;
    }

    public static @NonNull ResourceKey<Biome> getIslandCave(Continentalness continentalness, Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv) {
        if (temp.isFrozen()) {
            return BeerBiomes.CAVE_FORGOTTEN_CAVE;
        } else if (temp.isCold()) {
            return BeerBiomes.CAVE_BASALT_CAVE;
        } else if (temp.isTemperate()) {
            return BeerBiomes.ISLAND_COTTON_CANDY_ISLAND;
        }
        return Biomes.MUSHROOM_FIELDS;
    }

}
