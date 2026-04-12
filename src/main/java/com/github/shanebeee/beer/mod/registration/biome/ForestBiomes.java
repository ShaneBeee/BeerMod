package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.biome.BiomeColors;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

public class ForestBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition bamboo_jungle = BiomeDefinition.builder(BeerBiomes.FOREST_BAMBOO_JUNGLE, reg.getContext())
            .temperature(0.95f)
            .hasPrecipitation(true)
            .downfall(0.9f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(
                Optional.of(new Music(SoundEvents.MUSIC_BIOME_BAMBOO_JUNGLE,
                    12000, 24000, true)),
                Optional.empty(),
                Optional.empty()
            ))
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_HUMID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_HUMID.fogColor())

            .addDefaultOverworldCarvers()
            .addDefaultUndergroundOreFeatures()
            .addDefaultLakes()
            .addDefaultMonsterRoomFeatures()
            .addDefaultSurfaceFreezingFeatures()
            .features(null,
                null,
                List.of(CavePlacements.AMETHYST_GEODE),
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(
                    PlacedFeatures.TREE_BAMBOO_JUNGLE_TREES,
                    PlacedFeatures.VEGETATION_BAMBOO_SOME_PODZOL,
                    VegetationPlacements.BAMBOO_VEGETATION,
                    VegetationPlacements.FLOWER_WARM,
                    VegetationPlacements.PATCH_GRASS_JUNGLE,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_PUMPKIN,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER,
                    VegetationPlacements.VINES,
                    VegetationPlacements.PATCH_MELON),
                null)

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 3, 8)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 2, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 2, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CHICKEN, 2, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 2, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PARROT, 40, 1, 1)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PANDA, 80, 1, 2)
            .addDefaultMonsterSpawns(false)
            .addMobSpawn(MobCategory.MONSTER, EntityType.OCELOT, 2, 1, 1)
            .addMobSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.IS_JUNGLE, BiomeTags.HAS_JUNGLE_TEMPLE, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(bamboo_jungle);

        BiomeDefinition baobab_savanna = BiomeDefinition.builder(BeerBiomes.FOREST_BAOBAB_SAVANNA, reg.getContext())
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)
            .waterColor(new Color(89, 125, 113).getRGB())

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.HOT_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.HOT_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2171215)

            .addDefaultOverworldCarvers()
            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_STONE_CLIFF),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TREE_BAOBABS,
                    VegetationPlacements.TREES_SAVANNA,
                    PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    VegetationPlacements.PATCH_GRASS_PLAIN,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.CREATURE, EntityType.HORSE, 4, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.DONKEY, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.LLAMA, 10, 1, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.ARMADILLO, 30, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 15, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.STRAY, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PILLAGER, 10, 3, 4)

            .addToTag(BiomeTags.HAS_VILLAGE_SAVANNA, BiomeTags.IS_SAVANNA, BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_RUINED_PORTAL_STANDARD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_PILLAGER_OUTPOST,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(baobab_savanna);

        BiomeDefinition bayou = BiomeDefinition.builder(BeerBiomes.FOREST_BAYOU, reg.getContext())
            .hasPrecipitation(true)
            .temperature(1.2f)
            .downfall(0.2f)
            .waterColor(3829036)
            .foliageColorOverride(7441446)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_SEMI_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_SEMI_ARID.fogColor())

            .addDefaultOverworldCarvers()

            .features(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TREE_CYPRESSES,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE_SWAMP,
                    AquaticPlacements.SEAGRASS_SWAMP),
                null)

            .addDefaultMonsterSpawns(false)
            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 50, 1, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 50, 1, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PARROT, 20, 1, 3)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.TROPICAL_FISH, 20, 5, 8)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.WATER_ON_MAP_OUTLINES)

            .build();
        reg.register(bayou);

        BiomeDefinition dry_forest = BiomeDefinition.builder(BeerBiomes.FOREST_DRY_FOREST, reg.getContext())
            .hasPrecipitation(false)
            .temperature(0.7f)
            .downfall(0.0f)
            .waterColor(7768221)
            .foliageColorOverride(-7250899)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.0f)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.TEMPERATE_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.TEMPERATE_ARID.fogColor())

            .addDefaultUndergroundOreFeatures()
            .addDefaultMonsterRoomFeatures()
            .addDefaultSpringsFeatures()
            .addDefaultSurfaceFreezingFeatures()
            .features(null,
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE),
                List.of(CavePlacements.AMETHYST_GEODE),
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(VegetationPlacements.FOREST_FLOWERS,
                    VegetationPlacements.TREES_BIRCH_AND_OAK_LEAF_LITTER,
                    VegetationPlacements.PATCH_BUSH,
                    VegetationPlacements.FLOWER_DEFAULT,
                    VegetationPlacements.PATCH_GRASS_FOREST,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_PUMPKIN,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER),
                null
            )

            .addDefaultOverworldCarvers()

            .addDefaultPlainsSpawns()// TODO

            .build();
        reg.register(dry_forest);

        BiomeDefinition moss_garden = BiomeDefinition.builder(BeerBiomes.FOREST_MOSS_GARDEN, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)
            .foliageColorOverride(55551)
            .grassColorOverride(6980207)
            .waterColor(7768221)

            .particle(ParticleTypes.FIREFLY, 0.01f)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.0f)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE, 6000, 8, 2.0f)),
                List.of()))
            .modifyAttribute(EnvironmentAttributes.SKY_LIGHT_FACTOR, FloatModifier.MULTIPLY, 0.03f)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(1, 6, 52).getRGB())
            .setAttribute(EnvironmentAttributes.SKY_COLOR, new Color(23, 24, 28).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(23, 24, 28).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 8f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 15f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 5597568)

            .addDefaultUndergroundOreFeatures()
            .features(null, List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
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
                List.of(PlacedFeatures.TREE_MOSS_GARDEN,
                    PlacedFeatures.TREE_FALLEN_WARPED_STEM,
                    PlacedFeatures.VEGETATION_MOSS_PATCH,
                    VegetationPlacements.PATCH_GRASS_FOREST),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.IS_FOREST, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(moss_garden);

        BiomeDefinition tall_oak = BiomeDefinition.builder(BeerBiomes.FOREST_TALL_OAK, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.2f)
            .downfall(0.5f)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.COLD_MODERATE.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.COLD_MODERATE.fogColor())

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
                List.of(VegetationPlacements.FOREST_FLOWERS,
                    PlacedFeatures.TREE_TALL_OAK_TREES,
                    VegetationPlacements.PATCH_BUSH,
                    VegetationPlacements.FLOWER_DEFAULT,
                    VegetationPlacements.PATCH_GRASS_FOREST,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_PUMPKIN,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    VegetationPlacements.PATCH_FIREFLY_BUSH_NEAR_WATER),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))
            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 12, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CHICKEN, 10, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 8, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.WOLF, 5, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)

            .addToTag(BiomeTags.IS_FOREST, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(tall_oak);

        BiomeDefinition lush_forest = BiomeDefinition.builder(BeerBiomes.FOREST_LUSH_FOREST, reg.getContext())
            .hasPrecipitation(true)
            .temperature(1.4f)
            .downfall(0.6f)
            .waterColor(3832426)
            .foliageColorOverride(9285927)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(
                    SoundEvents.AMBIENT_CAVE,
                    6000,
                    8,
                    2.0)),
                List.of()))

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_SEMI_HUMID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_SEMI_HUMID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 5077600)

            .addDefaultUndergroundOreFeatures()
            .features(null,
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.FOSSIL_UPPER,
                    CavePlacements.FOSSIL_LOWER,
                    CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                null,
                null,
                null,
                null,
                List.of(MiscOverworldPlacements.SPRING_WATER,
                    MiscOverworldPlacements.SPRING_LAVA),
                List.of(PlacedFeatures.VEGETATION_MOSS_VEGETATION,
                    PlacedFeatures.TREE_TROPICAL_FOREST,
                    VegetationPlacements.PATCH_TALL_GRASS,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    VegetationPlacements.WILDFLOWERS_MEADOW,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    AquaticPlacements.SEAGRASS_SWAMP),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addDefaultMonsterSpawns(true)
            .addDefaultFarmAnimalsSpawns()
            .addDefaultCaveSpawns()
            .addMobSpawn(MobCategory.MONSTER, EntityType.OCELOT, 2, 1, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.WOLF, 10, 2, 5)

            .addToTag(BiomeTags.IS_FOREST, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(lush_forest);
    }

}
