package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Continentalness;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

public class CaveBiomes {

    public static @NonNull ResourceKey<Biome> getBiome(Continentalness continentalness, Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv) {
        if (temp.isFrozen()) {
            return BeerBiomes.CAVE_ICE_CAVE;
        } else if (temp.isCold()) {
            return BeerBiomes.CAVE_DIORITE_CAVE;
        } else if (temp.isTemperate()) {
            if (pv.isMid()) {
                return BeerBiomes.CAVE_SULFUR_CAVE;
            }
            if (humidity.isHumid()) {
                return BeerBiomes.CAVE_MUDDY_FEN;
            }
            return BeerBiomes.CAVE_PLAIN_CAVE;
        } else if (temp.isWarm()) {
            if (pv.isMid()) {
                return BeerBiomes.CAVE_SULFUR_CAVE;
            }
            return BeerBiomes.CAVE_BASALT_CAVE;
        } else {
            if (continentalness == Continentalness.FAR_INLAND) {
                return Biomes.DRIPSTONE_CAVES;
            }

            if (humidity.isArid()) {
                return BeerBiomes.CAVE_DRY_CAVE;
            } else if (humidity.isSemiArid()) {
                return BeerBiomes.CAVE_SMOKY_CAVE;
            } else if (humidity.isHumid()) {
                return Biomes.LUSH_CAVES;
            } else {
                return BeerBiomes.CAVE_FORGOTTEN_CAVE;
            }
        }
    }

}
