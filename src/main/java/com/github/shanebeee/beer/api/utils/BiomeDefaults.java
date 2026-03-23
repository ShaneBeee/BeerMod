package com.github.shanebeee.beer.api.utils;

import net.minecraft.world.level.biome.Climate;

public class BiomeDefaults {

    public static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    public static final Climate.Parameter[] TEMPERATURES = new Climate.Parameter[]{
        Climate.Parameter.span(-1.0F, -0.45F),
        Climate.Parameter.span(-0.45F, -0.15F),
        Climate.Parameter.span(-0.15F, 0.2F),
        Climate.Parameter.span(0.2F, 0.55F),
        Climate.Parameter.span(0.55F, 1.0F)
    };

    public static final Climate.Parameter[] HUMIDITIES = new Climate.Parameter[]{
        Climate.Parameter.span(-1.0F, -0.35F),
        Climate.Parameter.span(-0.35F, -0.1F),
        Climate.Parameter.span(-0.1F, 0.1F),
        Climate.Parameter.span(0.1F, 0.3F),
        Climate.Parameter.span(0.3F, 1.0F)
    };

    public static final Climate.Parameter[] EROSIONS = new Climate.Parameter[]{
        Climate.Parameter.span(-1.0F, -0.78F),
        Climate.Parameter.span(-0.78F, -0.375F),
        Climate.Parameter.span(-0.375F, -0.2225F),
        Climate.Parameter.span(-0.2225F, 0.05F),
        Climate.Parameter.span(0.05F, 0.45F),
        Climate.Parameter.span(0.45F, 0.55F),
        Climate.Parameter.span(0.55F, 1.0F)
    };

    public static final Climate.Parameter[] PV_VALLEYS = new Climate.Parameter[]{
        Climate.Parameter.span(-0.05F, 0.05F)
    };

    public static final Climate.Parameter[] PV_LOW = new Climate.Parameter[]{
        Climate.Parameter.span(-0.26666668F, -0.05F),
        Climate.Parameter.span(0.05F, 0.26666668F)
    };

    public static final Climate.Parameter[] PV_MID = new Climate.Parameter[]{
        Climate.Parameter.span(-1.0F, -0.93333334F),
        Climate.Parameter.span(-0.4F, -0.26666668F),
        Climate.Parameter.span(0.26666668F, 0.4F),
        Climate.Parameter.span(0.93333334F, 1.0F)
    };

    public static final Climate.Parameter[] PV_HIGH = new Climate.Parameter[]{
        Climate.Parameter.span(-0.93333334F, -0.7666667F),
        Climate.Parameter.span(-0.56666666F, -0.4F),
        Climate.Parameter.span(0.4F, 0.56666666F),
        Climate.Parameter.span(0.7666667F, 0.93333334F)
    };

    public static final Climate.Parameter[] PV_PEAK = new Climate.Parameter[]{
        Climate.Parameter.span(-0.7666667F, -0.56666666F),
        Climate.Parameter.span(0.56666666F, 0.7666667F)
    };

    public static final Climate.Parameter[][] PVS = new Climate.Parameter[][] {
        PV_VALLEYS, PV_LOW, PV_MID, PV_HIGH, PV_PEAK
    };

    public static final Climate.Parameter MUSHROOM_FIELDS_CONTINENTALNESS = Climate.Parameter.span(-1.2F, -1.05F);
    public static final Climate.Parameter DEEP_OCEAN_CONTINENTALNESS = Climate.Parameter.span(-1.05F, -0.455F);
    public static final Climate.Parameter OCEAN_CONTINENTALNESS = Climate.Parameter.span(-0.455F, -0.19F);
    public static final Climate.Parameter COAST_CONTINENTALNESS = Climate.Parameter.span(-0.19F, -0.11F);
    public static final Climate.Parameter INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.55F);
    public static final Climate.Parameter NEAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(-0.11F, 0.03F);
    public static final Climate.Parameter MID_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.03F, 0.3F);
    public static final Climate.Parameter FAR_INLAND_CONTINENTALNESS = Climate.Parameter.span(0.3F, 1.0F);

    public static final Climate.Parameter[] CONTINENTALNESS = new Climate.Parameter[]{
        MUSHROOM_FIELDS_CONTINENTALNESS,
        DEEP_OCEAN_CONTINENTALNESS,
        OCEAN_CONTINENTALNESS,
        COAST_CONTINENTALNESS,
        INLAND_CONTINENTALNESS,
        NEAR_INLAND_CONTINENTALNESS,
        MID_INLAND_CONTINENTALNESS,
        FAR_INLAND_CONTINENTALNESS
    };

}
