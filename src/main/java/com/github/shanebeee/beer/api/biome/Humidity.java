package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public enum Humidity {
    ARID(-1.0F, -0.35F),
    SEMI_ARID(-0.35F, -0.1F),
    MODERATE(-0.1F, 0.1F),
    SEMI_HUMID(0.1F, 0.3F),
    HUMID(0.3F, 1.0F);

    private final Climate.Parameter span;

    Humidity(float min, float max) {
        this.span = Climate.Parameter.span(min, max);
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
