package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

public class BeachBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(int temp, int humidity, int weirdness) {
        return switch (temp) {
            case 0 -> BeerBiomes.COAST_FROZEN_BEACH;
            case 1 -> Biomes.BEACH;
            case 2 -> humidity <= 2 ? BeerBiomes.COAST_BEACHY_COAST : BeerBiomes.COAST_TEMPERATE_COAST;
            case 3 -> humidity <= 2 ? BeerBiomes.COAST_PALM_BEACH : BeerBiomes.COAST_LUSH_COAST;
            default -> humidity <= 2 ? BeerBiomes.COAST_DRY_COAST : BeerBiomes.COAST_LUSH_COAST;
        };
    }

}
