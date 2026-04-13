package com.github.shanebeee.beer.mod.registry.tags;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class BeerBiomeTags {

    public static TagKey<Biome> HAS_MINESHAFT_SANDSTONE = register("has_structure/mineshaft/sandstone");
    public static TagKey<Biome> HAS_MINESHAFT_SPRUCE = register("has_structure/mineshaft/spruce");

    private static TagKey<Biome> register(String key) {
        return TagKey.create(Registries.BIOME, Identifier.parse(Beer.MOD_ID + ":" + key));
    }

}
