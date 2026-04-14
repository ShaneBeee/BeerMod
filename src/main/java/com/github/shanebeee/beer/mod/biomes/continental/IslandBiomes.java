package com.github.shanebeee.beer.mod.biomes.continental;

import com.github.shanebeee.beer.api.biome.Continentalness;
import com.github.shanebeee.beer.api.biome.Erosion;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("unused")
public class IslandBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return Biomes.MUSHROOM_FIELDS;
    }

    public static @NonNull ResourceKey<Biome> getIslandCave(Continentalness continentalness, Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv) {
        return Biomes.MUSHROOM_FIELDS;
    }

}
