package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ShatteredBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (temp) {
            case FROZEN, COLD -> getCold(temp, humidity, weirdness);
            case TEMPERATE -> getTemperate(temp, humidity, weirdness);
            case WARM -> getWarm(temp, humidity, weirdness);
            case HOT -> getHot(temp, humidity, weirdness);
        };
    }

    private static @NotNull ResourceKey<Biome> getCold(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> Biomes.WINDSWEPT_GRAVELLY_HILLS;
            case MODERATE -> Biomes.WINDSWEPT_HILLS;
            case SEMI_HUMID, HUMID -> Biomes.WINDSWEPT_FOREST;
        };
    }

    private static @NotNull ResourceKey<Biome> getTemperate(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> Biomes.WINDSWEPT_HILLS;
            case MODERATE -> BeerBiomes.PLAINS_TEMPERATE_PLAINS;
            case SEMI_HUMID, HUMID -> Biomes.WINDSWEPT_FOREST;
        };
    }

    private static @NotNull ResourceKey<Biome> getWarm(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> weirdness.isWeird() ? BeerBiomes.FOREST_BAOBAB_SAVANNA : Biomes.SAVANNA;
            case MODERATE -> Biomes.FOREST;
            case SEMI_HUMID -> weirdness.isWeird() ? Biomes.SPARSE_JUNGLE : Biomes.JUNGLE;
            case HUMID -> weirdness.isWeird() ? BeerBiomes.FOREST_BAMBOO_JUNGLE : Biomes.JUNGLE;
        };
    }

    private static @NotNull ResourceKey<Biome> getHot(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return DesertBiomes.getBiome(Temperature.HOT, humidity, weirdness);
    }

}
