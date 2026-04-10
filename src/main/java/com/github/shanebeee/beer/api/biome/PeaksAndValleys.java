package com.github.shanebeee.beer.api.biome;

public enum PeaksAndValleys {
    VALLEY(Weirdness.valleys()),
    LOW(Weirdness.lows()),
    MID(Weirdness.mids()),
    HIGH(Weirdness.highs()),
    PEAK(Weirdness.peaks());

    private final Weirdness[] weirdness;

    PeaksAndValleys(Weirdness... weirdness) {
        this.weirdness = weirdness;
    }

    public Weirdness[] weirdness() {
        return this.weirdness;
    }

    public boolean isValley() {
        return this == VALLEY;
    }

    public boolean isLow() {
        return this == LOW;
    }

    public boolean isMid() {
        return this == MID;
    }

    public boolean isHigh() {
        return this == HIGH;
    }

    public boolean isPeak() {
        return this == PEAK;
    }

}
