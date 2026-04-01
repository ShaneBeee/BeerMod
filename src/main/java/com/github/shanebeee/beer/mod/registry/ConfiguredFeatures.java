package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ConfiguredFeatures {

    // DECOR
    public static final ResourceKey<ConfiguredFeature<?, ?>> DECOR_BASALT_PILLAR = register("decor/basalt_pillar");

    // DELTA
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELTA_BASALT_POOL = register("delta/basalt_pool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELTA_BASALT_DELTA = register("delta/basalt_delta");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELTA_FORGOTTEN_DELTA = register("delta/forgotten_delta");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DELTA_MOSS_DELTA = register("delta/moss_delta");

    // TERRAIN
    public static final ResourceKey<ConfiguredFeature<?, ?>> TERRAIN_SAND_SHORE_DISK = register("terrain/sand_shore_disk");

    // TREE
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_ACACIA_FOREST = register("tree/acacia_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_BAOBABS = register("tree/baobabs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_BAOBAB_ACACIA = register("tree/baobab_acacia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_BAOBAB_JUNGLE = register("tree/baobab_jungle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_BAOBAB_OAK = register("tree/baobab_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_BIRCH_SCRUB = register("tree/birch_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_COLD_SWAMP_OAK = register("tree/cold_swamp_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_COLD_SWAMP_PALE = register("tree/cold_swamp_pale");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_FALLEN_STRIPPED_PALE_OAK = register("tree/fallen_stripped_pale_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_FALLEN_STRIPPED_WARPED_STEM = register("tree/fallen_stripped_warped_stem");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_FALLEN_TALL_OAK = register("tree/fallen_tall_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_FALLEN_WARPED_STEM = register("tree/fallen_warped_stem");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_MARULA = register("tree/marula");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_MPINGO = register("tree/mpingo");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_OAK_SCRUB = register("tree/oak_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_OLIVE_TREE = register("tree/olive_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_PALM_TREE = register("tree/palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_RED_IVORYWOOD = register("tree/red_ivorywood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_SPRUCE_SCRUB = register("tree/spruce_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_STICK_PLANT = register("tree/stick_plant");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_SWAMP_OAK = register("tree/swamp_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_TALL_OAK_WITH_LITTER = register("tree/tall_oak_with_litter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_TALL_STRIPPED_PALE_OAK = register("tree/tall_stripped_pale_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREE_TROPICAL_FOREST = register("tree/tropical_forest");

    // VEGETATION
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEGETATION_AZALEA_BUSH_OR_SCRUB = register("vegetation/azalea_bush_or_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEGETATION_AZALEA_SCRUB = register("vegetation/azalea_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEGETATION_FLOWERING_AZALEA_SCRUB = register("vegetation/flowering_azalea_scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> VEGETATION_LUSH_RIVER_PLANTS = register("vegetation/lush_river_plants");

    private static ResourceKey<ConfiguredFeature<?, ?>> register(String key) {
        return Beer.getKey(Registries.CONFIGURED_FEATURE, key);
    }

}
