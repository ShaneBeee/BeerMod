package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.phys.Vec3;

import java.awt.Color;

public enum Temperature {
    FROZEN(-1.0F, -0.45F,
        new Color(0, 116, 210),
        new Vec3(0.9f, 0.95f, 1.15f)),
    COLD(-0.45F, -0.15F,
        new Color(30, 123, 215),
        new Vec3(0.95f, 0.98f, 1.08f)),
    TEMPERATE(-0.15F, 0.2F,
        new Color(80, 140, 220),
        new Vec3(1.0f, 1.0f, 1.0f)),
    WARM(0.2F, 0.55F,
        new Color(105, 189, 210),
        new Vec3(1.08f, 1.03f, 0.95f)),
    HOT(0.55F, 1.0F,
        new Color(104, 222, 191, 255),
        new Vec3(1.15f, 1.05f, 0.88f));

    private final Climate.Parameter span;
    final Color skyColorBase;
    final Vec3 fogColorMultiplier;

    Temperature(float min, float max, Color skyColorBase, Vec3 fogColorMultiplier) {
        this.span = Climate.Parameter.span(min, max);
        this.skyColorBase = skyColorBase;
        this.fogColorMultiplier = fogColorMultiplier;
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
