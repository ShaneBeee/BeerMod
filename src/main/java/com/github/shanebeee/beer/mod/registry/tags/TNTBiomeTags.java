package com.github.shanebeee.beer.mod.registry.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TNTBiomeTags {

    // PILLAGER OUTPOSTS
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_IBERIAN = register("has_structure/exclusives/pillager_outpost_iberian");
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_ORIENTAL = register("has_structure/exclusives/pillager_outpost_oriental");
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_RUSTIC = register("has_structure/exclusives/pillager_outpost_rustic");
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_NILOTIC = register("has_structure/exclusives/pillager_outpost_nilotic");
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_SWEDISH = register("has_structure/exclusives/pillager_outpost_swedish");
    public static final TagKey<Biome> TNT_HAS_PILLAGER_OUTPOST_TUDOR = register("has_structure/exclusives/pillager_outpost_tudor");

    // VILLAGES
    public static final TagKey<Biome> TNT_HAS_VILLAGE_IBERIAN = register("has_structure/exclusives/village_iberian");
    public static final TagKey<Biome> TNT_HAS_VILLAGE_ORIENTAL = register("has_structure/exclusives/village_oriental");
    public static final TagKey<Biome> TNT_HAS_VILLAGE_RUSTIC = register("has_structure/exclusives/village_rustic");
    public static final TagKey<Biome> TNT_HAS_VILLAGE_SWAMP_BOAT = register("has_structure/village_swamp_boat");
    public static final TagKey<Biome> TNT_HAS_VILLAGE_SWEDISH = register("has_structure/exclusives/village_swedish");
    public static final TagKey<Biome> TNT_HAS_VILLAGE_TUDOR = register("has_structure/exclusives/village_tudor");

    private static TagKey<Biome> register(String key) {
        return TagKey.create(Registries.BIOME, Identifier.parse("towns_and_towers:" + key));
    }

}
