package com.github.shanebeee.beer.mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Beer implements ModInitializer {

    public static final String MOD_ID = "beer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // Nothing here for now
    }

    /**
     * Create a {@link ResourceKey} with the "beer" namespace.
     *
     * @param registry Registry to create key for
     * @param key      "path" of the key
     * @param <T>      Generic type to use for the registry
     * @return New {@link ResourceKey} with the "beer" namespace
     */
    public static <T> ResourceKey<T> getKey(ResourceKey<? extends Registry<T>> registry, String key) {
        if (key.contains(":")) {
            return ResourceKey.create(registry, Identifier.parse(key));
        }
        return ResourceKey.create(registry, Identifier.parse(MOD_ID + ":" + key));
    }

}
