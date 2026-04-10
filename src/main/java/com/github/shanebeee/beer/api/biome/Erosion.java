package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public enum Erosion {
    NONE(-1.0f, -0.78f),
    MINIMAL(-0.78f, -0.375f),
    REDUCED(-0.375f, -0.2225f),
    MODERATE(-0.2225f, 0.05f),
    INCREASED(0.05f, 0.45f),
    HIGH(0.45f, 0.55f),
    FULLY_ERODED(0.55f, 1.0f);

    public static final Climate.Parameter[] EROSIONS = new Climate.Parameter[]{
        Climate.Parameter.span(-1.0F, -0.78F),
        Climate.Parameter.span(-0.78F, -0.375F),
        Climate.Parameter.span(-0.375F, -0.2225F),
        Climate.Parameter.span(-0.2225F, 0.05F),
        Climate.Parameter.span(0.05F, 0.45F),
        Climate.Parameter.span(0.45F, 0.55F),
        Climate.Parameter.span(0.55F, 1.0F)
    };

    private final Climate.Parameter span;

    Erosion(float min, float max) {
        this.span = Climate.Parameter.span(min, max);
    }

    public Climate.Parameter span() {
        return this.span;
    }

    public boolean isNoneOrMinimal() {
        return this == NONE || this == MINIMAL;
    }

    public boolean isFullyEroded() {
        return this == FULLY_ERODED;
    }

}
