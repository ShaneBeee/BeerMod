package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;

public class BiomeRegistration extends BaseRegistration<Biome, BiomeDefinition> {

    public BiomeRegistration(BootstrapContext<Biome> context) {
        super(Registries.BIOME, context);
        CaveBiomes.register(this);
        CoastalBiomes.register(this);
        DesertBiomes.register(this);
        ForestBiomes.register(this);
        IslandBiomes.register(this);
        PlainsBiomes.register(this);
        RiverBiomes.register(this);
        SwampBiomes.register(this);
    }

}
