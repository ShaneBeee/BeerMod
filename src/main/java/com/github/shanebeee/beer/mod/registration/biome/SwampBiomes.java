package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.BiomeSpecialEffects;

import java.util.List;

public class SwampBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition cold_swamp = BiomeDefinition.builder(BeerBiomes.SWAMP_COLD_SWAMP, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.4f)
            .downfall(0.4f)
            .waterColor(-10388622)
            .foliageColorOverride(6975545)
            .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, -8151297)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, -4339216)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2302743)

            .addDefaultUndergroundOreFeatures()
            .features(null,
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                null,
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.SPRING_WATER,
                    MiscOverworldPlacements.SPRING_LAVA),
                List.of(VegetationPlacements.PATCH_LARGE_FERN,
                    PlacedFeatures.TREE_COLD_SWAMP_TREE,
                    VegetationPlacements.FLOWER_SWAMP,
                    VegetationPlacements.FLOWER_DEFAULT,
                    VegetationPlacements.PATCH_GRASS_NORMAL,
                    VegetationPlacements.PATCH_GRASS_TAIGA_2,
                    VegetationPlacements.PATCH_DEAD_BUSH,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.BROWN_MUSHROOM_TAIGA,
                    VegetationPlacements.RED_MUSHROOM_TAIGA,
                    AquaticPlacements.SEAGRASS_SWAMP,
                    VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER,
                    VegetationPlacements.PATCH_BERRY_RARE),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 2, 5)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.WATER_CREATURE, EntityType.DROWNED, 2, 1, 2)

            .addToTag(BiomeTags.HAS_SWAMP_HUT, BiomeTags.IS_OVERWORLD, BiomeTags.SPAWNS_COLD_VARIANT_FROGS,
                BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.WATER_ON_MAP_OUTLINES,
                BiomeTags.HAS_RUINED_PORTAL_SWAMP)

            .build();
        reg.register(cold_swamp);

        BiomeDefinition dripleaf_swamp = BiomeDefinition.builder(BeerBiomes.SWAMP_DRIPLEAF_SWAMP, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(6388580)
            .foliageColorOverride(6975545)
            .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2302743)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.DELTA_DRIPLEAF_SWAMP_DELTA,
                    PlacedFeatures.DELTA_COASTAL_DELTA,
                    PlacedFeatures.TERRAIN_WATER_BLOB),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(
                    PlacedFeatures.TREE_SWAMP_OAK,
                    VegetationPlacements.FLOWER_SWAMP,
                    VegetationPlacements.PATCH_GRASS_NORMAL,
                    VegetationPlacements.PATCH_DEAD_BUSH,
                    VegetationPlacements.PATCH_WATERLILY,
                    AquaticPlacements.SEAGRASS_SWAMP),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 2, 5)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.WATER_CREATURE, EntityType.DROWNED, 2, 1, 2)

            .addToTag(BiomeTags.HAS_SWAMP_HUT, BiomeTags.IS_OVERWORLD, BiomeTags.SPAWNS_WARM_VARIANT_FROGS,
                BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.WATER_ON_MAP_OUTLINES,
                BiomeTags.HAS_RUINED_PORTAL_SWAMP, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)

            .build();
        reg.register(dripleaf_swamp);
    }

}
