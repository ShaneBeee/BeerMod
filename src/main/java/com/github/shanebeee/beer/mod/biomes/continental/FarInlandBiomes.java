package com.github.shanebeee.beer.mod.biomes.continental;

import com.github.shanebeee.beer.api.biome.Erosion;
import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.PeaksAndValleys;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import com.github.shanebeee.beer.mod.biomes.special.BadlandBiomes;
import com.github.shanebeee.beer.mod.biomes.special.MiddleBiomes;
import com.github.shanebeee.beer.mod.biomes.special.PlateauBiomes;
import com.github.shanebeee.beer.mod.biomes.special.RiverBiomes;
import com.github.shanebeee.beer.mod.biomes.special.ShatteredBiomes;
import com.github.shanebeee.beer.mod.biomes.special.SwampBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("SwitchStatementWithTooFewBranches")
public class FarInlandBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (pv) {
            case VALLEY -> getValley(temp, humidity, weirdness, erosion);
            case LOW -> getLow(temp, humidity, weirdness, erosion);
            case MID -> getMid(temp, humidity, weirdness, erosion);
            case HIGH -> getHigh(temp, humidity, weirdness, erosion);
            case PEAK -> getPeaks(temp, humidity, weirdness, erosion);
        };
    }

    public static @NotNull ResourceKey<Biome> getValley(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL ->
                temp.isHot() ? BadlandBiomes.getBiome(temp, humidity, weirdness) : MiddleBiomes.getBiome(temp, humidity, weirdness);
            case REDUCED, MODERATE, INCREASED, HIGH -> RiverBiomes.getBiome(temp, humidity, weirdness);
            case FULLY_ERODED -> switch (temp) {
                case FROZEN -> RiverBiomes.getBiome(temp, humidity, weirdness);
                default -> SwampBiomes.getBiome(temp, humidity, weirdness, PeaksAndValleys.VALLEY);
            };
        };
    }

    public static @NotNull ResourceKey<Biome> getLow(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL -> switch (temp) {
                case FROZEN -> switch (humidity) {
                    case ARID, SEMI_ARID -> Biomes.SNOWY_SLOPES;
                    default -> Biomes.GROVE;
                };
                case COLD, TEMPERATE, WARM -> MiddleBiomes.getBiome(temp, humidity, weirdness);
                case HOT -> BadlandBiomes.getBiome(temp, humidity, weirdness);
            };
            case REDUCED, MODERATE ->
                temp.isHot() ? BadlandBiomes.getBiome(temp, humidity, weirdness) : MiddleBiomes.getBiome(temp, humidity, weirdness);
            case INCREASED, HIGH -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case FULLY_ERODED -> switch (temp) {
                case FROZEN -> MiddleBiomes.getBiome(temp, humidity, weirdness);
                default -> SwampBiomes.getBiome(temp, humidity, weirdness, PeaksAndValleys.LOW);
            };
        };
    }

    public static @NotNull ResourceKey<Biome> getMid(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE ->
                temp.isTemperateOrBelow() ? humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE : PlateauBiomes.getBiome(temp, humidity, weirdness);
            case MINIMAL -> {
                if (temp.isFrozen()) {
                    yield humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                } else if (temp.isNotHot()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield BadlandBiomes.getBiome(temp, humidity, weirdness);
                }
            }
            case REDUCED -> PlateauBiomes.getBiome(temp, humidity, weirdness);
            case MODERATE ->
                temp.isNotHot() ? MiddleBiomes.getBiome(temp, humidity, weirdness) : BadlandBiomes.getBiome(temp, humidity, weirdness);
            case INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> ShatteredBiomes.getBiome(temp, humidity, weirdness);
            case FULLY_ERODED -> switch (temp) {
                case FROZEN -> MiddleBiomes.getBiome(temp, humidity, weirdness);
                default -> SwampBiomes.getBiome(temp, humidity, weirdness, PeaksAndValleys.MID);
            };
        };
    }

    public static @NotNull ResourceKey<Biome> getHigh(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE -> switch (temp) {
                case FROZEN, COLD, TEMPERATE -> weirdness.isWeird() ? Biomes.FROZEN_PEAKS : Biomes.JAGGED_PEAKS;
                case WARM -> Biomes.STONY_PEAKS;
                case HOT -> BadlandBiomes.getBiome(temp, humidity, weirdness);
            };
            case MINIMAL -> switch (temp) {
                case FROZEN, COLD, TEMPERATE -> humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                default -> PlateauBiomes.getBiome(temp, humidity, weirdness);
            };
            case REDUCED, MODERATE -> PlateauBiomes.getBiome(temp, humidity, weirdness);
            case INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> ShatteredBiomes.getBiome(temp, humidity, weirdness);
            case FULLY_ERODED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
        };
    }

    public static @NotNull ResourceKey<Biome> getPeaks(Temperature temp, Humidity humidity, Weirdness weirdness, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL -> switch (temp) {
                case FROZEN, COLD, TEMPERATE -> weirdness.isWeird() ? Biomes.FROZEN_PEAKS : Biomes.JAGGED_PEAKS;
                case WARM -> Biomes.STONY_PEAKS;
                case HOT -> BadlandBiomes.getBiome(temp, humidity, weirdness);
            };
            case REDUCED, MODERATE -> PlateauBiomes.getBiome(temp, humidity, weirdness);
            case INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> ShatteredBiomes.getBiome(temp, humidity, weirdness);
            case FULLY_ERODED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
        };
    }

}
