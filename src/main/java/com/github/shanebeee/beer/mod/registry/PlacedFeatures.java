package com.github.shanebeee.beer.mod.registry;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {

    // BLOBS
    public static final ResourceKey<PlacedFeature> BLOB_DEAD_BRAIN = register("blob/dead_brain");
    public static final ResourceKey<PlacedFeature> BLOB_DEAD_BUBBLE = register("blob/dead_bubble");
    public static final ResourceKey<PlacedFeature> BLOB_DEAD_FIRE = register("blob/dead_fire");
    public static final ResourceKey<PlacedFeature> BLOB_TERRACOTTA_LIGHT_GRAY = register("blob/terracotta_light_gray");
    public static final ResourceKey<PlacedFeature> BLOB_TERRACOTTA_LIGHT_BLUE = register("blob/terracotta_light_blue");
    public static final ResourceKey<PlacedFeature> BLOB_STONE = register("blob/stone");
    public static final ResourceKey<PlacedFeature> BLOB_TUFF = register("blob/tuff");

    // DECOR
    public static final ResourceKey<PlacedFeature> DECOR_HANGING_FENCE = register("decor/hanging_fence");
    public static final ResourceKey<PlacedFeature> DECOR_HANGING_STONE = register("decor/hanging_stone");

    // DELTAS
    public static final ResourceKey<PlacedFeature> DELTA_BEACH_DELTA = register("delta/beach_delta");
    public static final ResourceKey<PlacedFeature> DELTA_COASTAL_DELTA = register("delta/coastal_delta");
    public static final ResourceKey<PlacedFeature> DELTA_DRIPLEAF_SWAMP_DELTA = register("delta/dripleaf_swamp_delta");
    public static final ResourceKey<PlacedFeature> DELTA_DRY_CAVE_DELTA = register("delta/dry_cave_delta");
    public static final ResourceKey<PlacedFeature> DELTA_FORGOTTEN_DELTA = register("delta/forgotten_delta");
    public static final ResourceKey<PlacedFeature> DELTA_LUSH_DESERT_DELTA = register("delta/lush_desert_delta");
    public static final ResourceKey<PlacedFeature> DELTA_PLAIN_CAVE_DELTA = register("delta/plain_cave_delta");
    public static final ResourceKey<PlacedFeature> DELTA_SWAMP_DELTA = register("delta/swamp_delta");

    // REPLACE
    public static final ResourceKey<PlacedFeature> REPLACE_DEEPSLATE_TO_DIORITE = register("replace/deepslate_to_diorite");
    public static final ResourceKey<PlacedFeature> REPLACE_DEEPSLATE_TO_ICE = register("replace/deepslate_to_ice");
    public static final ResourceKey<PlacedFeature> REPLACE_GRASS_TO_SAND = register("replace/grass_to_sand");
    public static final ResourceKey<PlacedFeature> REPLACE_STONE_TO_DIORITE = register("replace/stone_to_diorite");
    public static final ResourceKey<PlacedFeature> REPLACE_STONE_TO_ICE = register("replace/stone_to_ice");
    public static final ResourceKey<PlacedFeature> REPLACE_STONE_TO_SNOW = register("replace/stone_to_snow");

    // TERRAIN
    public static final ResourceKey<PlacedFeature> TERRAIN_BROWN_CONCRETE_DISK = register("terrain/brown_concrete_disk");
    public static final ResourceKey<PlacedFeature> TERRAIN_DIORITE_CLIFFS = register("terrain/diorite_cliffs");
    public static final ResourceKey<PlacedFeature> TERRAIN_LUSH_PLAINS_LAKE = register("terrain/lush_plains_lake");
    public static final ResourceKey<PlacedFeature> TERRAIN_MOSSIFY_GRASS = register("terrain/mossify_grass");
    public static final ResourceKey<PlacedFeature> TERRAIN_SAND_SHORE_DISK = register("terrain/sand_shore_disk");
    public static final ResourceKey<PlacedFeature> TERRAIN_STONE_CLIFF = register("terrain/stone_cliff");
    public static final ResourceKey<PlacedFeature> TERRAIN_WATER_BLOB = register("terrain/water_blob");

    // TREE
    public static final ResourceKey<PlacedFeature> TREE_BEACHY_PALM = register("tree/beachy_palm");
    public static final ResourceKey<PlacedFeature> TREE_DESERT_RIVER_PALM = register("tree/desert_river_palm");
    public static final ResourceKey<PlacedFeature> TREE_COLD_SWAMP_TREE = register("tree/cold_swamp_tree");
    public static final ResourceKey<PlacedFeature> TREE_FALLEN_STRIPPED_PALE_OAK = register("tree/fallen_stripped_pale_oak");
    public static final ResourceKey<PlacedFeature> TREE_FALLEN_WARPED_STEM = register("tree/fallen_warped_stem");
    public static final ResourceKey<PlacedFeature> TREE_LUSH_DESERT_PALM = register("tree/lush_desert_palm");
    public static final ResourceKey<PlacedFeature> TREE_MOSS_GARDEN = register("tree/moss_garden");
    public static final ResourceKey<PlacedFeature> TREE_PALM_BEACH_PALM = register("tree/palm_beach_palm");
    public static final ResourceKey<PlacedFeature> TREE_TALL_FALLEN_TALL_OAK = register("tree/fallen_tall_oak");
    public static final ResourceKey<PlacedFeature> TREE_TALL_OAK_TREES = register("tree/tall_oak_trees");
    public static final ResourceKey<PlacedFeature> TREE_TALL_OAK_WITH_LITTER = register("tree/tall_oak_with_litter");
    public static final ResourceKey<PlacedFeature> TREE_TALL_STRIPPED_PALE_OAK = register("tree/tall_stripped_pale_oak");
    public static final ResourceKey<PlacedFeature> TREE_TROPICAL_FOREST = register("tree/tropical_forest");

    // VEGETATION
    public static final ResourceKey<PlacedFeature> VEGETATION_AZALEA_BUSH_OR_SCRUB = register("vegetation/azalea_bush_or_scrub");
    public static final ResourceKey<PlacedFeature> VEGETATION_LUSH_DESERT_AZALEA_SCRUB = register("vegetation/lush_desert_azalea_scrub");
    public static final ResourceKey<PlacedFeature> VEGETATION_MOSS_PATCH = register("vegetation/moss_patch");
    public static final ResourceKey<PlacedFeature> VEGETATION_PATCH_CHERRY_PETALS = register("vegetation/patch_cherry_petals");
    public static final ResourceKey<PlacedFeature> VEGETATION_PATCH_CLIFF_GRASS = register("vegetation/patch_cliff_grass");
    public static final ResourceKey<PlacedFeature> VEGETATION_PATCH_HAY_BALE = register("vegetation/patch_hay_bale");
    public static final ResourceKey<PlacedFeature> VEGETATION_PATCH_SMALL_DRIPLEAF = register("vegetation/patch_small_dripleaf");
    public static final ResourceKey<PlacedFeature> VEGETATION_PATCH_WATER_LEAVES = register("vegetation/patch_water_leaves");
    public static final ResourceKey<PlacedFeature> VEGETATION_ROOT_DIRT_BLOB = register("vegetation/rooted_dirt_blob");

    private static ResourceKey<PlacedFeature> register(String key) {
        return Beer.getKey(Registries.PLACED_FEATURE, key);
    }

}
