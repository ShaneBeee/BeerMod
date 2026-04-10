package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public class BiomeDefaults {

    public static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
    public static final Climate.Parameter CAVE_DEPTH = Climate.Parameter.span(0.078125f, 1.1f);
    public static final Climate.Parameter SURFACE_DEPTH = Climate.Parameter.span(-1f, 0.078125f);

}
