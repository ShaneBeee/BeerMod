package com.github.shanebeee.beer.mod.biomes.continental;

import com.github.shanebeee.beer.api.biome.Erosion;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.biomes.special.BadlandBiomes;
import com.github.shanebeee.beer.mod.biomes.special.BeachBiomes;
import com.github.shanebeee.beer.mod.biomes.special.MiddleBiomes;
import com.github.shanebeee.beer.mod.biomes.special.RiverBiomes;
import com.github.shanebeee.beer.mod.biomes.special.ShatteredBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

public class CoastalBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (pv) {
            case VALLEY -> getValley(temp, humidity, weirdness);
            case LOW -> getLow(temp, humidity, weirdness, erosion);
            case MID -> getMid(temp, humidity, weirdness, erosion);
            case HIGH -> getHigh(temp, humidity, weirdness, erosion);
            case PEAK -> getPeaks(temp, humidity, weirdness, erosion);
        };
    }

    public static @NotNull ResourceKey<Biome> getValley(Temperature temp, Humidity humidity, Weirdness weirdness) {
        return RiverBiomes.getBiome(temp, humidity, weirdness);
    }

    public static @NotNull ResourceKey<Biome> getLow(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL, REDUCED -> Biomes.STONY_SHORE;
            case MODERATE, INCREASED -> BeachBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isWeird()) {
                    yield BeachBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    if (temp.isColdOrFrozen() || humidity.isHumid()) {
                        yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                    } else {
                        yield Biomes.WINDSWEPT_SAVANNA;
                    }
                }
            }
            case FULLY_ERODED -> BeachBiomes.getBiome(temp, humidity, weirdness);
        };
    }

    public static @NotNull ResourceKey<Biome> getMid(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL, REDUCED -> Biomes.STONY_SHORE;
            case MODERATE -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case INCREASED ->
                weirdness.isWeird() ? MiddleBiomes.getBiome(temp, humidity, weirdness) : BeachBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isWeird()) {
                    yield BeachBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    if (temp.isColdOrFrozen() || humidity.isHumid()) {
                        yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                    } else {
                        yield Biomes.WINDSWEPT_SAVANNA;
                    }
                }
            }
            case FULLY_ERODED ->
                weirdness.isWeird() ? MiddleBiomes.getBiome(temp, humidity, weirdness) : BeachBiomes.getBiome(temp, humidity, weirdness);
        };
    }

    public static @NotNull ResourceKey<Biome> getHigh(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL, REDUCED, MODERATE, INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isNotWeird() || temp.isColdOrFrozen() || humidity.isHumid()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield Biomes.WINDSWEPT_SAVANNA;
                }
            }
            case FULLY_ERODED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
        };
    }

    public static @NotNull ResourceKey<Biome> getPeaks(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE -> {
                if (temp.isTemperateOrBelow()) yield weirdness.isWeird() ? Biomes.FROZEN_PEAKS : Biomes.JAGGED_PEAKS;
                else if (temp.isWarm()) yield Biomes.STONY_PEAKS;
                else yield BadlandBiomes.getBiome(temp, humidity, weirdness);
            }
            case MINIMAL -> {
                if (temp.isFrozen()) yield humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                else if (temp.isNotHot()) yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                else yield BadlandBiomes.getBiome(temp, humidity, weirdness);
            }
            case REDUCED, MODERATE, INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isNotWeird() || temp.isColdOrFrozen() || humidity.isHumid()) {
                    yield ShatteredBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield Biomes.WINDSWEPT_SAVANNA;
                }
            }
            case FULLY_ERODED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
        };
    }

}
