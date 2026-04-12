package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.phys.Vec3;

import java.awt.Color;

public enum Humidity {
    ARID(-1.0F, -0.35F,
        new Vec3(1.1, 1.0, 0.92),
        new Color(180, 170, 140)),
    SEMI_ARID(-0.35F, -0.1F,
        new Vec3(1.05, 1.0, 0.97),
        new Color(190, 185, 160)),
    MODERATE(-0.1F, 0.1F,
        new Vec3(1.0, 1.0, 1.0),
        new Color(190, 195, 195)),
    SEMI_HUMID(0.1F, 0.3F,
        new Vec3(0.95, 1.0, 1.05),
        new Color(175, 185, 195)),
    HUMID(0.3F, 1.0F,
        new Vec3(1.15, 0.95, 0.88),
        new Color(160, 175, 195));

    private final Climate.Parameter span;
    final Vec3 skyColorMultiplier;
    final Color fogColorBase;

    Humidity(float min, float max, Vec3 skyColorMultiplier, Color fogColorBase) {
        this.span = Climate.Parameter.span(min, max);
        this.skyColorMultiplier = skyColorMultiplier;
        this.fogColorBase = fogColorBase;
    }

    public Climate.Parameter span() {
        return this.span;
    }

    public boolean isAridOrSemiArid() {
        return this == ARID || this == SEMI_ARID;
    }

    public boolean isHumidOrSemiHumid() {
        return this == HUMID || this == SEMI_HUMID;
    }

    public boolean isModerateOrLess() {
        return this == MODERATE || this == SEMI_ARID || this == ARID;
    }

    public boolean isArid() {
        return this == ARID;
    }

    public boolean isSemiArid() {
        return this == SEMI_ARID;
    }

    public boolean isModerate() {
        return this == MODERATE;
    }

    public boolean isHumid() {
        return this == HUMID;
    }

    public boolean isSemiHumid() {
        return this == SEMI_HUMID;
    }
    
}
