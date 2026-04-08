package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

public class CaveBiomes {

    public static @NonNull ResourceKey<Biome> getBiome(int continentalness, int temp, int humidity, int weirdness, int pv) {
        if (temp == 0) {
            return BeerBiomes.CAVE_ICE_CAVE;
        } else if (temp == 1) {
            return BeerBiomes.CAVE_DIORITE_CAVE;
        } else if (temp == 2) {
            if (humidity == 0) {
                return BeerBiomes.CAVE_SULFUR_CAVE;
            }
            if (humidity == 4) {
                return BeerBiomes.CAVE_MUDDY_FEN;
            }
            return BeerBiomes.CAVE_PLAIN_CAVE;
        } else if (temp == 3) {
            if (humidity == 0) {
                return BeerBiomes.CAVE_SULFUR_CAVE;
            }
            return BeerBiomes.CAVE_BASALT_CAVE;
        } else {
            if (continentalness == 6) {
                return Biomes.DRIPSTONE_CAVES;
            }

            if (humidity == 0) {
                return BeerBiomes.CAVE_DRY_CAVE;
            } else if (humidity == 1) {
                return BeerBiomes.CAVE_SMOKY_CAVE;
            } else if (humidity == 4) {
                return Biomes.LUSH_CAVES;
            } else {
                return BeerBiomes.CAVE_FORGOTTEN_CAVE;
            }
        }
    }

}
