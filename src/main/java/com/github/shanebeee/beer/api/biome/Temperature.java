package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public enum Temperature {

    FROZEN(-1.0F, -0.45F),
    COLD(-0.45F, -0.15F),
    TEMPERATE(-0.15F, 0.2F),
    WARM(0.2F, 0.55F),
    HOT(0.55F, 1.0F);

    private final Climate.Parameter span;

    Temperature(float min, float max) {
        this.span = Climate.Parameter.span(min, max);
    }

    public Climate.Parameter span() {
        return this.span;
    }

    public boolean isColdOrFrozen() {
        return this == FROZEN || this == COLD;
    }

    public boolean isTemperateOrBelow() {
        return this == FROZEN || this == COLD || this == TEMPERATE;
    }

    public boolean isNotHot() {
        return this != HOT;
    }

    public boolean isFrozen() {
        return this == FROZEN;
    }

    public boolean isCold() {
        return this == COLD;
    }

    public boolean isTemperate() {
        return this == TEMPERATE;
    }

    public boolean isWarm() {
        return this == WARM;
    }

    public boolean isHot() {
        return this == HOT;
    }
}
