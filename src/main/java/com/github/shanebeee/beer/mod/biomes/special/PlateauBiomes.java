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
public class PlateauBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (temp) {
            case FROZEN -> getFrozen(humidity, weirdness);
            case COLD -> getCold(humidity, weirdness);
            case TEMPERATE -> getTemperate(humidity, weirdness);
            case WARM -> getWarm(humidity, weirdness);
            case HOT -> getHot(temp, humidity, weirdness);
        };
    }

    private static @NotNull ResourceKey<Biome> getFrozen(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID -> weirdness.isWeird() ? Biomes.ICE_SPIKES : Biomes.SNOWY_PLAINS;
            case SEMI_ARID, MODERATE -> Biomes.SNOWY_PLAINS;
            default -> Biomes.SNOWY_TAIGA;
        };
    }

    private static @NotNull ResourceKey<Biome> getCold(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID -> weirdness.isWeird() ? Biomes.CHERRY_GROVE : Biomes.MEADOW;
            case SEMI_ARID -> Biomes.MEADOW;
            case MODERATE -> weirdness.isWeird() ? Biomes.MEADOW : Biomes.FOREST;
            case SEMI_HUMID -> weirdness.isWeird() ? Biomes.MEADOW : Biomes.TAIGA;
            case HUMID -> weirdness.isWeird() ? Biomes.OLD_GROWTH_PINE_TAIGA : Biomes.OLD_GROWTH_SPRUCE_TAIGA;
        };
    }

    private static @NotNull ResourceKey<Biome> getTemperate(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> weirdness.isWeird() ? Biomes.CHERRY_GROVE : Biomes.MEADOW;
            case MODERATE -> weirdness.isWeird() ? BeerBiomes.FOREST_MOSS_GARDEN : Biomes.FOREST;
            case SEMI_HUMID -> weirdness.isWeird() ? Biomes.BIRCH_FOREST : Biomes.MEADOW;
            case HUMID -> weirdness.isWeird() ? Biomes.PALE_GARDEN : Biomes.DARK_FOREST;
        };
    }

    private static @NotNull ResourceKey<Biome> getWarm(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> Biomes.SAVANNA_PLATEAU;
            case MODERATE, SEMI_HUMID -> Biomes.FOREST;
            case HUMID -> Biomes.JUNGLE;
        };
    }

    private static @NotNull ResourceKey<Biome> getHot(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return BadlandBiomes.getBiome(temp, humidity, weirdness);
    }

}
