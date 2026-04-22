package com.github.shanebeee.beer.mod.registration.feature.placed;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatureRegistration extends BaseRegistration<PlacedFeature, PlacedFeatureDefinition> {

    public PlacedFeatureRegistration(BootstrapContext<PlacedFeature> context) {
        super(Registries.PLACED_FEATURE, context);
        BlobFeatures.register(this);
        BushFeatures.register(this);
        DecorFeatures.register(this);
        DeltaFeatures.register(this);
        ReplacementFeatures.register(this);
        TerrainFeatures.register(this);
        TreeFeatures.register(this);
        VegetationFeatures.register(this);
    }

}
