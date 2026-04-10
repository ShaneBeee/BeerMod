package com.github.shanebeee.beer.api.biome;

import net.minecraft.world.level.biome.Climate;

public enum Weirdness {

    MID_A(-1.0F, -0.93333334F),
    HIGH_A(-0.93333334F, -0.7666667F),
    PEAK_A(-0.7666667F, -0.56666666F),
    HIGH_B(-0.56666666F, -0.4F),
    MID_B(-0.4F, -0.26666668F),
    LOW_A(-0.26666668F, -0.05F),
    VALLEY(-0.05F, 0.05F),
    LOW_B(0.05F, 0.26666668F),
    MID_C(0.26666668F, 0.4F),
    HIGH_C(0.4F, 0.56666666F),
    PEAK_B(0.56666666F, 0.7666667F),
    HIGH_D(0.7666667F, 0.93333334F),
    MID_D(0.93333334F, 1.0F);

    private final Climate.Parameter span;

    Weirdness(float min, float max) {
        this.span = Climate.Parameter.span(min, max);
    }

    public boolean isWeird() {
        return this.span.max() > 0;
    }

    public boolean isNotWeird() {
        return this.span.max() <= 0;
    }

    public Climate.Parameter span() {
        return this.span;
    }

    public static Weirdness[] valleys() {
        return new Weirdness[]{VALLEY};
    }

    public static Weirdness[] lows() {
        return new Weirdness[]{LOW_A, LOW_B};
    }

    public static Weirdness[] mids() {
        return new Weirdness[]{MID_A, MID_B, MID_C, MID_D};
    }

    public static Weirdness[] highs() {
        return new Weirdness[]{HIGH_A, HIGH_B, HIGH_C, HIGH_D};
    }

    public static Weirdness[] peaks() {
        return new Weirdness[]{PEAK_A, PEAK_B};
    }

}
