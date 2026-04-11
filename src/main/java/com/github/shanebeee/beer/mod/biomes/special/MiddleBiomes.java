package com.github.shanebeee.beer.mod.biomes.special;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class MiddleBiomes {

    public static ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (temp) {
            case FROZEN -> getFrozen(humidity, weirdness);
            case COLD -> getCold(humidity, weirdness);
            case TEMPERATE -> getTemperate(humidity, weirdness);
            case WARM -> getWarm(humidity, weirdness);
            case HOT -> getHot(humidity, weirdness);
        };
    }

    private static ResourceKey<Biome> getFrozen(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID -> weirdness.isWeird() ? Biomes.ICE_SPIKES : Biomes.SNOWY_PLAINS;
            case SEMI_ARID -> Biomes.SNOWY_PLAINS;
            case MODERATE -> weirdness.isWeird() ? Biomes.SNOWY_TAIGA : Biomes.SNOWY_PLAINS;
            default -> Biomes.SNOWY_TAIGA;
        };
    }

    private static ResourceKey<Biome> getCold(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID, SEMI_ARID -> BeerBiomes.PLAINS_COLD_PLAINS;
            case MODERATE -> BeerBiomes.FOREST_TALL_OAK;
            case SEMI_HUMID -> Biomes.TAIGA;
            case HUMID -> weirdness.isWeird() ? Biomes.OLD_GROWTH_PINE_TAIGA : Biomes.OLD_GROWTH_SPRUCE_TAIGA;
        };
    }

    private static ResourceKey<Biome> getTemperate(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID -> weirdness.isWeird() ? Biomes.SUNFLOWER_PLAINS : BeerBiomes.FOREST_DRY_FOREST;
            case SEMI_ARID -> BeerBiomes.PLAINS_TEMPERATE_PLAINS;
            case MODERATE -> weirdness.isWeird() ? Biomes.FLOWER_FOREST : Biomes.FOREST;
            case SEMI_HUMID -> weirdness.isWeird() ? Biomes.OLD_GROWTH_BIRCH_FOREST : Biomes.BIRCH_FOREST;
            case HUMID -> weirdness.isWeird() ? BeerBiomes.PLAINS_LUSH_PLAINS : Biomes.DARK_FOREST;
        };
    }

    private static ResourceKey<Biome> getWarm(Humidity humidity, Weirdness weirdness) {
        return switch (humidity) {
            case ARID -> weirdness.isWeird() ? BeerBiomes.FOREST_BAOBAB_SAVANNA : Biomes.SAVANNA;
            case SEMI_ARID -> BeerBiomes.PLAINS_SEMI_ARID_PLAINS;
            case MODERATE -> weirdness.isWeird() ? BeerBiomes.PLAINS_TEMPERATE_PLAINS : BeerBiomes.FOREST_LUSH_FOREST;
            case SEMI_HUMID -> weirdness.isWeird() ? Biomes.SPARSE_JUNGLE : BeerBiomes.FOREST_LUSH_FOREST;
            case HUMID -> weirdness.isWeird() ? BeerBiomes.FOREST_BAMBOO_JUNGLE : Biomes.JUNGLE;
        };
    }

    private static ResourceKey<Biome> getHot(Humidity humidity, Weirdness weirdness) {
        return DesertBiomes.getBiome(Temperature.HOT, humidity, weirdness);
    }

}
