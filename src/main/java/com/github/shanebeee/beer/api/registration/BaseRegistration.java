package com.github.shanebeee.beer.api.registration;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRegistration<R, T extends Definable<R>> {

    // STATIC
    private static final Map<ResourceKey<?>, List<Definable<?>>> DEFINABLES_BY_KEY = new HashMap<>();
    private static final List<BaseRegistration<?, ?>> REGISTRATIONS = new ArrayList<>();

    public static void addDefinable(ResourceKey<?> registryKey, Definable<?> definable) {
        DEFINABLES_BY_KEY.computeIfAbsent(registryKey, _ -> new ArrayList<>()).add(definable);
    }

    public static List<BaseRegistration<?, ?>> getRegistrations() {
        return REGISTRATIONS;
    }

    @SuppressWarnings("unchecked")
    public static <R, T extends Definable<R>> List<T> getDefinables(ResourceKey<Registry<R>> registryKey) {
        return (List<T>) DEFINABLES_BY_KEY.get(registryKey);
    }

    // INSTANCE
    private final ResourceKey<Registry<R>> registryKey;
    private final List<T> definables = new ArrayList<>();

    @SuppressWarnings("unused")
    public BaseRegistration(ResourceKey<Registry<R>> registryKey, BootstrapContext<R> context) {
        this.registryKey = registryKey;
        REGISTRATIONS.add(this);
    }

    public void register(T definable) {
        this.definables.add(definable);
        addDefinable(this.registryKey, definable);
    }

    public void addToEntries(FabricDynamicRegistryProvider.Entries entries) {
        for (T definable : this.definables) {
            ResourceKey<R> resourceKey = definable.getResourceKey();
            if (resourceKey == null) continue;

            R value = definable.getValue();
            entries.add(resourceKey, value);
        }
    }

}
