package com.github.shanebeee.beer.api.biome;

public class BiomeColors {

    public static final BiomeColors FROZEN_ARID = BiomeColors.getBiomeColors(Temperature.FROZEN, Humidity.ARID);
    public static final BiomeColors FROZEN_SEMI_ARID = BiomeColors.getBiomeColors(Temperature.FROZEN, Humidity.SEMI_ARID);
    public static final BiomeColors FROZEN_MODERATE = BiomeColors.getBiomeColors(Temperature.FROZEN, Humidity.MODERATE);
    public static final BiomeColors FROZEN_SEMI_HUMID = BiomeColors.getBiomeColors(Temperature.FROZEN, Humidity.SEMI_HUMID);
    public static final BiomeColors FROZEN_HUMID = BiomeColors.getBiomeColors(Temperature.FROZEN, Humidity.HUMID);
    public static final BiomeColors COLD_ARID = BiomeColors.getBiomeColors(Temperature.COLD, Humidity.ARID);
    public static final BiomeColors COLD_SEMI_ARID = BiomeColors.getBiomeColors(Temperature.COLD, Humidity.SEMI_ARID);
    public static final BiomeColors COLD_MODERATE = BiomeColors.getBiomeColors(Temperature.COLD, Humidity.MODERATE);
    public static final BiomeColors COLD_SEMI_HUMID = BiomeColors.getBiomeColors(Temperature.COLD, Humidity.SEMI_HUMID);
    public static final BiomeColors COLD_HUMID = BiomeColors.getBiomeColors(Temperature.COLD, Humidity.HUMID);
    public static final BiomeColors TEMPERATE_ARID = BiomeColors.getBiomeColors(Temperature.TEMPERATE, Humidity.ARID);
    public static final BiomeColors TEMPERATE_SEMI_ARID = BiomeColors.getBiomeColors(Temperature.TEMPERATE, Humidity.SEMI_ARID);
    public static final BiomeColors TEMPERATE_MODERATE = BiomeColors.getBiomeColors(Temperature.TEMPERATE, Humidity.MODERATE);
    public static final BiomeColors TEMPERATE_SEMI_HUMID = BiomeColors.getBiomeColors(Temperature.TEMPERATE, Humidity.SEMI_HUMID);
    public static final BiomeColors TEMPERATE_HUMID = BiomeColors.getBiomeColors(Temperature.TEMPERATE, Humidity.HUMID);
    public static final BiomeColors WARM_ARID = BiomeColors.getBiomeColors(Temperature.WARM, Humidity.ARID);
    public static final BiomeColors WARM_SEMI_ARID = BiomeColors.getBiomeColors(Temperature.WARM, Humidity.SEMI_ARID);
    public static final BiomeColors WARM_MODERATE = BiomeColors.getBiomeColors(Temperature.WARM, Humidity.MODERATE);
    public static final BiomeColors WARM_SEMI_HUMID = BiomeColors.getBiomeColors(Temperature.WARM, Humidity.SEMI_HUMID);
    public static final BiomeColors WARM_HUMID = BiomeColors.getBiomeColors(Temperature.WARM, Humidity.HUMID);
    public static final BiomeColors HOT_ARID = BiomeColors.getBiomeColors(Temperature.HOT, Humidity.ARID);
    public static final BiomeColors HOT_SEMI_ARID = BiomeColors.getBiomeColors(Temperature.HOT, Humidity.SEMI_ARID);
    public static final BiomeColors HOT_MODERATE = BiomeColors.getBiomeColors(Temperature.HOT, Humidity.MODERATE);
    public static final BiomeColors HOT_SEMI_HUMID = BiomeColors.getBiomeColors(Temperature.HOT, Humidity.SEMI_HUMID);
    public static final BiomeColors HOT_HUMID = BiomeColors.getBiomeColors(Temperature.HOT, Humidity.HUMID);

    public static BiomeColors getBiomeColors(Temperature temp, Humidity humidity) {
        return new BiomeColors(temp, humidity);
    }

    private final int skyColor;
    private final int fogColor;

    private BiomeColors(Temperature temp, Humidity humidity) {
        int r = Math.clamp((int) (temp.skyColorBase.getRed() * humidity.skyColorMultiplier.x), 0, 255);
        int g = Math.clamp((int) (temp.skyColorBase.getGreen() * humidity.skyColorMultiplier.y), 0, 255);
        int b = Math.clamp((int) (temp.skyColorBase.getBlue() * humidity.skyColorMultiplier.z), 0, 255);
        this.skyColor = (r << 16) | (g << 8) | b;


        int rh = Math.clamp((int) (humidity.fogColorBase.getRed() * temp.fogColorMultiplier.x), 0, 255);
        int gh = Math.clamp((int) (humidity.fogColorBase.getGreen() * temp.fogColorMultiplier.y), 0, 255);
        int bh = Math.clamp((int) (humidity.fogColorBase.getBlue() * temp.fogColorMultiplier.z), 0, 255);
        this.fogColor = (rh << 16) | (gh << 8) | bh;
    }

    public int skyColor() {
        return this.skyColor;
    }

    public int fogColor() {
        return this.fogColor;
    }

}
