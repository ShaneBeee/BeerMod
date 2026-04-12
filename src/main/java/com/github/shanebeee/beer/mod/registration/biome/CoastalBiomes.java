package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.biome.BiomeColors;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
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

public class CoastalBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition beachy_beach = BiomeDefinition.builder(BeerBiomes.COAST_BEACHY_COAST, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.4f)

            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_MODERATE.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_MODERATE.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS,
                    PlacedFeatures.REPLACE_GRASS_TO_SAND),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    PlacedFeatures.TREE_BEACHY_PALM,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 15, 2, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(beachy_beach);

        BiomeDefinition coast = BiomeDefinition.builder(BeerBiomes.COAST_TEMPERATE_COAST, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.5f)
            .waterColor(6003155)
            //.foliageColorOverride(442658)
            //.grassColorOverride(11060330)

            .particle(ParticleTypes.CHERRY_LEAVES, 0.0005f)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.TEMPERATE_MODERATE.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.TEMPERATE_MODERATE.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS,
                    PlacedFeatures.DELTA_COASTAL_DELTA),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(
                    PlacedFeatures.VEGETATION_PATCH_WATER_LEAVES,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    PlacedFeatures.TREE_WINDSWEPT_OAK,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    PlacedFeatures.TERRAIN_MOSSIFY_GRASS,
                    //PlacedFeatures.BUSH_MEDITERRANEAN_BUSHES,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    AquaticPlacements.SEAGRASS_NORMAL),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.ILLUSIONER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 3, 1, 1)
            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 1, 2)
            .addMobSpawn(MobCategory.CREATURE, EntityType.ARMADILLO, 10, 1, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(coast);

        BiomeDefinition dry_coast = BiomeDefinition.builder(BeerBiomes.COAST_DRY_COAST, reg.getContext())
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.05f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.HOT_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.HOT_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    PlacedFeatures.TREE_PALM_BEACH_PALM,
                    PlacedFeatures.TERRAIN_MOSSIFY_GRASS,
                    VegetationPlacements.PATCH_GRASS_SAVANNA),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.ILLUSIONER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 3, 1, 1)
            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 1, 2)
            .addMobSpawn(MobCategory.CREATURE, EntityType.ARMADILLO, 10, 1, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(dry_coast);

        BiomeDefinition frozen_beach = BiomeDefinition.builder(BeerBiomes.COAST_FROZEN_BEACH, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.1f)
            .downfall(0.0f)
            .waterColor(4020182)

            .particle(ParticleTypes.WHITE_ASH, 0.2f)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS,
                new AmbientSounds(
                    Optional.empty(),
                    Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CAVE,
                        6000, 8, 2.0f)),
                    List.of()))

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.FROZEN_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.FROZEN_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS,
                    PlacedFeatures.REPLACE_GRASS_TO_SAND),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TREE_PALM_BEACH_PALM,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 15, 2, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.SPAWNS_COLD_VARIANT_FROGS,
                BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS)

            .build();
        reg.register(frozen_beach);

        BiomeDefinition lush_coast = BiomeDefinition.builder(BeerBiomes.COAST_LUSH_COAST, reg.getContext())
            .hasPrecipitation(true)
            .temperature(1.5f)
            .downfall(0.7f)
            .waterColor(new Color(52, 155, 148).getRGB())
            .foliageColorOverride(442658)

            .particle(ParticleTypes.CHERRY_LEAVES, 0.0005f)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_SEMI_HUMID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_SEMI_HUMID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS,
                    PlacedFeatures.DELTA_COASTAL_DELTA),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.VEGETATION_PATCH_WATER_LEAVES,
                    PlacedFeatures.TREE_PALM_BEACH_PALM,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    PlacedFeatures.TERRAIN_MOSSIFY_GRASS,
                    PlacedFeatures.BUSH_MEDITERRANEAN_BUSHES,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
                    AquaticPlacements.SEAGRASS_NORMAL),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 3, 1, 1)
            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 1, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.SPAWNS_WARM_VARIANT_FROGS, BiomeTags.IS_OVERWORLD)

            .build();
        reg.register(lush_coast);

        BiomeDefinition palm_beach = BiomeDefinition.builder(BeerBiomes.COAST_PALM_BEACH, reg.getContext())
            .hasPrecipitation(false)
            .temperature(1.5f)
            .downfall(0.2f)
            .foliageColorOverride(442658)
            .grassColorOverride(6017902)
            .waterColor(6003155)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_SEMI_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_SEMI_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_DIORITE_CLIFFS,
                    PlacedFeatures.REPLACE_GRASS_TO_SAND,
                    PlacedFeatures.DELTA_BEACH_DELTA),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TREE_PALM_BEACH_PALM,
                    VegetationPlacements.PATCH_WATERLILY,
                    PlacedFeatures.VEGETATION_PATCH_SMALL_DRIPLEAF),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 15, 2, 2)

            .addToTag(BiomeTags.IS_BEACH, BiomeTags.HAS_SHIPWRECK_BEACHED, BiomeTags.SPAWNS_WARM_VARIANT_FROGS, BiomeTags.IS_OVERWORLD)

            .build();
        reg.register(palm_beach);
    }

}
