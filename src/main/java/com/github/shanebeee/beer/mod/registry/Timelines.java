package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.timeline.Timeline;

public class Timelines {

    public static final ResourceKey<Timeline> MOONLIGHT = register("moonlight");

    @SuppressWarnings("SameParameterValue")
    private static ResourceKey<Timeline> register(String key) {
        return Beer.getKey(Registries.TIMELINE, key);
    }

}
