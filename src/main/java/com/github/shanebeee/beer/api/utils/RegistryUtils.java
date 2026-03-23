package com.github.shanebeee.beer.api.utils;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RegistryUtils {

    private static BootstrapContext<?> CONTEXT;

    public static void init(BootstrapContext<?> context) {
        CONTEXT = context;
    }

    public static @Nullable Holder<ConfiguredFeature<?, ?>> getConfiguredFeatureReference(ResourceKey<ConfiguredFeature<?, ?>> key) {
        Optional<Holder.Reference<ConfiguredFeature<?, ?>>> configuredFeatureReference = CONTEXT.lookup(Registries.CONFIGURED_FEATURE).get(key);
        return configuredFeatureReference.orElse(null);
    }

}
