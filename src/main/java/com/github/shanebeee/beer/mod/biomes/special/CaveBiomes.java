package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

public class CaveBiomes {

    public static @NonNull ResourceKey<Biome> getBiome(int temp, int humidity) {
        if (temp <= 1) {
            return BeerBiomes.CAVE_ICE_CAVE;
        } else if (temp == 2) {
            return BeerBiomes.CAVE_DIORITE_CAVE;
        } else {
            if (humidity <= 1) {
                return BeerBiomes.CAVE_DRY_CAVE;
            } else if (humidity == 4) {
                return Biomes.LUSH_CAVES;
            } else {
                return BeerBiomes.CAVE_FORGOTTEN_CAVE;
            }
        }
    }

}
