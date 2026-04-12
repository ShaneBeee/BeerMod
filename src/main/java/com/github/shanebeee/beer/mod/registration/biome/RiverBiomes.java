package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.biome.BiomeColors;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

public class RiverBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition cold_river = BiomeDefinition.builder(BeerBiomes.RIVER_COLD_RIVER, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.15f)
            .downfall(0.5f)
            .waterColor(new Color(61, 87, 214).getRGB())

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS,
                new AmbientSounds(
                    Optional.empty(),
                    Optional.of(new AmbientMoodSettings(
                        SoundEvents.AMBIENT_CAVE,
                        6000,
                        8,
                        2.0)),
                    List.of())
            )

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
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
                List.of(VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    AquaticPlacements.SEAGRASS_RIVER,
                    AquaticPlacements.KELP_COLD),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.DROWNED, 100, 1, 1)
            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 2, 4)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.SALMON, 5, 1, 5)

            .addToTag(BiomeTags.IS_RIVER, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(cold_river);

        BiomeDefinition desert_river = BiomeDefinition.builder(BeerBiomes.RIVER_DESERT_RIVER, reg.getContext())
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)
            .waterColor(4112789)
            .foliageColorOverride(9285927)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.HOT_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.HOT_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2326625)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND),
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
                List.of(PlacedFeatures.TREE_DESERT_RIVER_PALM,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    VegetationPlacements.FLOWER_DEFAULT,
                    VegetationPlacements.PATCH_DEAD_BUSH_2,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_SUGAR_CANE_DESERT),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 19, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 80, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.RABBIT, 4, 2, 3)
            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)

            .addToTag(BiomeTags.IS_RIVER, BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS,
                BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(desert_river);

        BiomeDefinition lush_river = BiomeDefinition.builder(BeerBiomes.RIVER_LUSH_RIVER, reg.getContext())
            .hasPrecipitation(true)
            .temperature(1.5f)
            .downfall(0.8f)
            .waterColor(new Color(52, 155, 148).getRGB())
            .foliageColorOverride(9285927)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(
                    SoundEvents.AMBIENT_CAVE,
                    6000,
                    8,
                    2.0)),
                List.of()
            ))

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
                    PlacedFeatures.VEGETATION_LUSH_RIVER_PLANTS,
                    PlacedFeatures.TREE_TROPICAL_FOREST,
                    VegetationPlacements.PATCH_TALL_GRASS,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    AquaticPlacements.SEAGRASS_SWAMP),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 19, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 1, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.OCELOT, 2, 1, 3)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 4, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.CHICKEN, 10, 4, 4)
            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.PUFFERFISH, 15, 1, 3)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.COD, 25, 8, 8)

            .addToTag(BiomeTags.IS_RIVER, BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS,
                BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(lush_river);

        BiomeDefinition temperate_river = BiomeDefinition.builder(BeerBiomes.RIVER_TEMPERATE_RIVER, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.5f)
            .downfall(0.5f)
            .waterColor(6388580)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS,
                new AmbientSounds(
                    Optional.empty(),
                    Optional.of(new AmbientMoodSettings(
                        SoundEvents.AMBIENT_CAVE,
                        6000,
                        8,
                        2.0
                    )),
                    List.of()
                ))

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.TEMPERATE_MODERATE.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.TEMPERATE_MODERATE.fogColor())
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
                List.of(VegetationPlacements.BROWN_MUSHROOM_NORMAL,
                    VegetationPlacements.RED_MUSHROOM_NORMAL,
                    AquaticPlacements.SEAGRASS_RIVER),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 1, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.DROWNED, 100, 1, 1)
            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.SALMON, 5, 1, 5)

            .addToTag(BiomeTags.IS_RIVER, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(temperate_river);
    }

}
