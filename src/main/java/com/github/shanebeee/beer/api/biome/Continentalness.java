package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public enum Continentalness {
    MUSHROOM_FIELDS(-1.2F, -1.05F),
    DEEP_OCEAN(-1.05F, -0.455F),
    OCEAN(-0.455F, -0.19F),
    COASTAL(-0.19F, -0.11F),
    NEAR_INLAND(-0.11F, 0.03F),
    MID_INLAND(0.03F, 0.3F),
    FAR_INLAND(0.3F, 1.0F);

    private final Climate.Parameter span;

    Continentalness(float min, float max) {
        this.span = Climate.Parameter.span(min, max);
    }

    public Climate.Parameter span() {
        return this.span;
    }

    public boolean isMushroomFields() {
        return this == MUSHROOM_FIELDS;
    }

    public boolean isDeepOcean() {
        return this == DEEP_OCEAN;
    }

    public boolean isOcean() {
        return this == OCEAN;
    }

    public boolean isAnyOcean() {
        return this.isDeepOcean() || this.isOcean();
    }

    public boolean isLand() {
        return !this.isAnyOcean();
    }

    public boolean isCoastal() {
        return this == COASTAL;
    }

    public boolean isNearInland() {
        return this == NEAR_INLAND;
    }

    public boolean isMidInland() {
        return this == MID_INLAND;
    }

    public boolean isFarInland() {
        return this == FAR_INLAND;
    }

}
