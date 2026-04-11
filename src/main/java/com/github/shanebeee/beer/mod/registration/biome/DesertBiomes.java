package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.awt.Color;
import java.util.List;

public class DesertBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition cactus_fields = BiomeDefinition.builder(BeerBiomes.DESERT_CACTUS_FIELDS, reg.getContext())
            .hasPrecipitation(false)
            .temperature(1.0f)
            .downfall(0.8f)

            .waterColor(6003155)
            .foliageColorOverride(442658)
            .grassColorOverride(-8213182)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7788235)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2326625)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND),
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.FOSSIL_UPPER,
                    CavePlacements.FOSSIL_LOWER,
                    CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                List.of(MiscOverworldPlacements.DESERT_WELL),
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.SPRING_WATER,
                    MiscOverworldPlacements.SPRING_LAVA),
                List.of(PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    VegetationPlacements.PATCH_GRASS_BADLANDS,
                    VegetationPlacements.PATCH_DRY_GRASS_DESERT,
                    VegetationPlacements.PATCH_DEAD_BUSH_2,
                    PlacedFeatures.VEGETATION_CACTUS_FIELDS_CACTUS),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 12, 2, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CAMEL, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.STRAY, 50, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PILLAGER, 5, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 80, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PARCHED, 50, 4, 4)

            .addToTag(BiomeTags.HAS_VILLAGE_DESERT, BiomeTags.HAS_DESERT_PYRAMID,
                BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.SPAWNS_GOLD_RABBITS,
                BiomeTags.HAS_RUINED_PORTAL_DESERT, BiomeTags.SPAWNS_WARM_VARIANT_FROGS,
                BiomeTags.HAS_PILLAGER_OUTPOST, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(cactus_fields);

        BiomeDefinition dry_desert = BiomeDefinition.builder(BeerBiomes.DESERT_DRY_DESERT, reg.getContext())
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)

            .waterColor(4112789)
            .foliageColorOverride(9285927)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7788235)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2326625)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND),
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.FOSSIL_UPPER,
                    CavePlacements.FOSSIL_LOWER,
                    CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                List.of(MiscOverworldPlacements.DESERT_WELL),
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.SPRING_WATER,
                    MiscOverworldPlacements.SPRING_LAVA),
                List.of(VegetationPlacements.FLOWER_DEFAULT,
                    VegetationPlacements.PATCH_GRASS_BADLANDS,
                    VegetationPlacements.PATCH_DRY_GRASS_DESERT,
                    VegetationPlacements.PATCH_DEAD_BUSH_2,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_SUGAR_CANE_DESERT,
                    VegetationPlacements.PATCH_PUMPKIN,
                    VegetationPlacements.PATCH_CACTUS_DESERT),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 12, 2, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CAMEL, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 19, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 50, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 80, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PARCHED, 50, 4, 4)

            .addToTag(BiomeTags.HAS_VILLAGE_DESERT, BiomeTags.HAS_DESERT_PYRAMID,
                BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.SPAWNS_GOLD_RABBITS,
                BiomeTags.HAS_RUINED_PORTAL_DESERT, BiomeTags.SPAWNS_WARM_VARIANT_FROGS,
                BiomeTags.HAS_PILLAGER_OUTPOST, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(dry_desert);

        BiomeDefinition lush_desert = BiomeDefinition.builder(BeerBiomes.DESERT_LUSH_DESERT, reg.getContext())
            .hasPrecipitation(true)
            .temperature(1.0f)
            .downfall(0.8f)

            .waterColor(new Color(61, 174, 130).getRGB())
            .foliageColorOverride(442658)
            .grassColorOverride(-8213182)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7788235)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2326625)

            .addDefaultOverworldCarvers()

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND,
                    PlacedFeatures.TERRAIN_OASIS_PATCH,
                    PlacedFeatures.DELTA_LUSH_DESERT_DELTA,
                    PlacedFeatures.REPLACE_GRASS_UNDER_WATER_TO_SAND
                ),
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.DESERT_WELL),
                null,
                null,
                null,
                null,
                List.of(
                    PlacedFeatures.TREE_LUSH_DESERT_PALM,
                    PlacedFeatures.TREE_PALM_TREE_OASIS,
                    PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    PlacedFeatures.VEGETATION_PATCH_OASIS_FLOWERS,
                    VegetationPlacements.PATCH_GRASS_JUNGLE,
                    VegetationPlacements.PATCH_DRY_GRASS_DESERT,
                    VegetationPlacements.PATCH_DEAD_BUSH_2,
                    VegetationPlacements.PATCH_SUGAR_CANE_DESERT,
                    VegetationPlacements.PATCH_CACTUS_DESERT
                ),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 12, 2, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CAMEL, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 19, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 50, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 80, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PARCHED, 50, 4, 4)

            .addToTag(BiomeTags.HAS_VILLAGE_DESERT, BiomeTags.HAS_DESERT_PYRAMID,
                BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.SPAWNS_GOLD_RABBITS,
                BiomeTags.HAS_RUINED_PORTAL_DESERT, BiomeTags.SPAWNS_WARM_VARIANT_FROGS,
                BiomeTags.HAS_PILLAGER_OUTPOST, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(lush_desert);

        BiomeDefinition steppe = BiomeDefinition.builder(BeerBiomes.DESERT_STEPPE_DESERT, reg.getContext())
            .hasPrecipitation(false)
            .temperature(1.0f)
            .downfall(0.8f)

            .waterColor(6003155)
            .foliageColorOverride(442658)
            .grassColorOverride(-8213182)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7788235)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2326625)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND),
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.FOSSIL_UPPER,
                    CavePlacements.FOSSIL_LOWER,
                    CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                List.of(MiscOverworldPlacements.DESERT_WELL),
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.SPRING_WATER,
                    MiscOverworldPlacements.SPRING_LAVA),
                List.of(PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    VegetationPlacements.PATCH_GRASS_BADLANDS,
                    VegetationPlacements.PATCH_DRY_GRASS_DESERT,
                    VegetationPlacements.PATCH_DEAD_BUSH_2,
                    PlacedFeatures.VEGETATION_STEPPE_DESERT_CACTUS),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 12, 2, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CAMEL, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.STRAY, 50, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PILLAGER, 5, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 80, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PARCHED, 50, 4, 4)

            .addToTag(BiomeTags.HAS_VILLAGE_DESERT, BiomeTags.HAS_DESERT_PYRAMID,
                BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.SPAWNS_GOLD_RABBITS,
                BiomeTags.HAS_RUINED_PORTAL_DESERT, BiomeTags.SPAWNS_WARM_VARIANT_FROGS,
                BiomeTags.HAS_PILLAGER_OUTPOST, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(steppe);
    }
    
}
