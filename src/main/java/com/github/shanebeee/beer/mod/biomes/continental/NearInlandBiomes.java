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
public class NearInlandBiomes {

    public static @NotNull ResourceKey<Biome> getBiome(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (pv) {
            case VALLEY -> getValley(temp, humidity, weirdness, pv, erosion);
            case LOW -> getLow(temp, humidity, weirdness, pv, erosion);
            case MID -> getMid(temp, humidity, weirdness, pv, erosion);
            case HIGH -> getHigh(temp, humidity, weirdness, pv, erosion);
            case PEAK -> getPeaks(temp, humidity, weirdness, pv, erosion);
        };
    }

    public static @NotNull ResourceKey<Biome> getValley(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        if (erosion.isFullyEroded()) return SwampBiomes.getBiome(temp, humidity, weirdness);
        return RiverBiomes.getBiome(temp, humidity, weirdness);
    }

    public static @NotNull ResourceKey<Biome> getLow(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (erosion) {
            case NONE, MINIMAL -> temp.isHot() ? BadlandBiomes.getBiome(temp, humidity, weirdness) :
                MiddleBiomes.getBiome(temp, humidity, weirdness);
            case REDUCED, MODERATE, INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isNotWeird() || temp.isColdOrFrozen() || humidity.isHumid()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield Biomes.WINDSWEPT_SAVANNA;
                }
            }
            case FULLY_ERODED -> switch (temp) {
                case FROZEN -> MiddleBiomes.getBiome(temp, humidity, weirdness);
                default -> SwampBiomes.getBiome(temp, humidity, weirdness);
            };
        };

    }

    public static @NotNull ResourceKey<Biome> getMid(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (erosion) {
            case NONE ->
                temp.isTemperateOrBelow() ? (humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE) : PlateauBiomes.getBiome(temp, humidity, weirdness);
            case MINIMAL -> {
                if (temp.isFrozen()) {
                    yield humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                } else if (temp.isNotHot()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield BadlandBiomes.getBiome(temp, humidity, weirdness);
                }
            }
            case REDUCED, MODERATE, INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
            case HIGH -> {
                if (weirdness.isNotWeird() || temp.isColdOrFrozen() || humidity.isHumid()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield Biomes.WINDSWEPT_SAVANNA;
                }
            }
            case FULLY_ERODED -> switch (temp) {
                case FROZEN -> MiddleBiomes.getBiome(temp, humidity, weirdness);
                default -> SwampBiomes.getBiome(temp, humidity, weirdness);
            };
        };
    }

    public static @NotNull ResourceKey<Biome> getHigh(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
        return switch (erosion) {
            case NONE -> {
                if (temp.isTemperateOrBelow()) {
                    yield humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                } else {
                    yield PlateauBiomes.getBiome(temp, humidity, weirdness);
                }
            }
            case MINIMAL -> {
                if (temp.isFrozen()) {
                    yield humidity.isAridOrSemiArid() ? Biomes.SNOWY_SLOPES : Biomes.GROVE;
                } else if (temp.isNotHot()) {
                    yield MiddleBiomes.getBiome(temp, humidity, weirdness);
                } else {
                    yield BadlandBiomes.getBiome(temp, humidity, weirdness);
                }
            }
            case REDUCED, MODERATE, INCREASED -> MiddleBiomes.getBiome(temp, humidity, weirdness);
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

    public static @NotNull ResourceKey<Biome> getPeaks(Temperature temp, Humidity humidity, Weirdness weirdness, PeaksAndValleys pv, Erosion erosion) {
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
