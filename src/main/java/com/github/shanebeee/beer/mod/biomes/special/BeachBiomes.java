package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

public class BeachBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (temp) {
            case FROZEN -> weirdness.isWeird() ? BeerBiomes.COAST_FROZEN_BEACH : Biomes.SNOWY_BEACH;
            case COLD -> Biomes.BEACH;
            case TEMPERATE -> humidity.isModerateOrLess() ? BeerBiomes.COAST_BEACHY_COAST : BeerBiomes.COAST_TEMPERATE_COAST;
            case WARM -> humidity.isModerateOrLess() ? BeerBiomes.COAST_PALM_BEACH : BeerBiomes.COAST_LUSH_COAST;
            case HOT -> humidity.isModerateOrLess() ? BeerBiomes.COAST_DRY_COAST : BeerBiomes.COAST_LUSH_COAST;
        };
    }

}
