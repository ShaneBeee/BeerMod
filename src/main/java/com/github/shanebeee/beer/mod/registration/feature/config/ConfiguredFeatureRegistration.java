package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ConfiguredFeatureRegistration extends BaseRegistration<ConfiguredFeature<?, ?>, ConfiguredFeatureDefinition> {

    public ConfiguredFeatureRegistration(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        super(Registries.CONFIGURED_FEATURE, context);
        PlacedFeatureDefinition.setupConfiguredFeatureContext(context);
        DecorConfigs.register(this);
        DeltaConfigs.register(this);
        TerrainConfigs.register(this);
        TreeConfigs.register(this);
        VegetationConfigs.register(this);
    }

}
