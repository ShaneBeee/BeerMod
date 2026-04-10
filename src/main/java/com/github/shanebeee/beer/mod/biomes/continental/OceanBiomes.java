package com.github.shanebeee.beer.mod.biomes.continental;

import com.github.shanebeee.beer.api.biome.Humidity;
import com.github.shanebeee.beer.api.biome.Temperature;
import com.github.shanebeee.beer.api.biome.Weirdness;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class OceanBiomes {

    public static ResourceKey<Biome> getBiome(boolean deep, Temperature temp, Humidity humidity, Weirdness weirdness) {
        return switch (temp) {
            case FROZEN -> deep ? Biomes.DEEP_FROZEN_OCEAN : Biomes.FROZEN_OCEAN;
            case COLD -> deep ? Biomes.DEEP_COLD_OCEAN : Biomes.COLD_OCEAN;
            case TEMPERATE -> deep ? Biomes.DEEP_OCEAN : Biomes.OCEAN;
            case WARM -> deep ? Biomes.DEEP_LUKEWARM_OCEAN : Biomes.LUKEWARM_OCEAN;
            case HOT -> Biomes.WARM_OCEAN;
        };
    }

}
