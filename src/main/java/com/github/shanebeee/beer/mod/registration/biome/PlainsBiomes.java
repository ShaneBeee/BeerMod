package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.biome.BiomeColors;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.awt.Color;
import java.util.List;

public class PlainsBiomes {

    public static void register(BiomeRegistration reg) {
        BiomeDefinition semi_arid_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_SEMI_ARID_PLAINS, reg.getContext())
            .hasPrecipitation(false)
            .temperature(1.5f)
            .downfall(0.25f)
            .waterColor(new Color(112, 148, 96).getRGB())

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_ARID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_ARID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2171215)

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
                List.of(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    VegetationPlacements.PATCH_GRASS_PLAIN,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_RUINED_PORTAL_STANDARD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_PILLAGER_OUTPOST,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(semi_arid_plains);

        BiomeDefinition lush_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_LUSH_PLAINS, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.65f)
            .waterColor(4159204)
            .dryFoliageColorOverride(new Color(181, 147, 187).getRGB())

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.WARM_SEMI_HUMID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.WARM_SEMI_HUMID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.TERRAIN_STONE_CLIFF,
                    PlacedFeatures.TERRAIN_LUSH_PLAINS_LAKE),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    PlacedFeatures.TREE_TALL_STRIPPED_PALE_OAK,
                    VegetationPlacements.FLOWER_PLAINS,
                    VegetationPlacements.PATCH_GRASS_PLAIN,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    PlacedFeatures.VEGETATION_PILE_AZALEA_LEAVES,
                    PlacedFeatures.VEGETATION_PILE_HAY_BALE,
                    PlacedFeatures.VEGETATION_PILE_MOSS,
                    PlacedFeatures.VEGETATION_PATCH_CHERRY_PETALS,
                    PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB,
                    PlacedFeatures.TREE_FALLEN_STRIPPED_PALE_OAK),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_RUINED_PORTAL_STANDARD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_PILLAGER_OUTPOST,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(lush_plains);

        BiomeDefinition temperate_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_TEMPERATE_PLAINS, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.85f)
            .downfall(0.5f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.TEMPERATE_MODERATE.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.TEMPERATE_MODERATE.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

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
                List.of(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    VegetationPlacements.FLOWER_PLAINS,
                    VegetationPlacements.PATCH_GRASS_PLAIN,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    PlacedFeatures.VEGETATION_PILE_HAY_BALE,
                    PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_RUINED_PORTAL_STANDARD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_PILLAGER_OUTPOST,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(temperate_plains);

        BiomeDefinition cold_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_COLD_PLAINS, reg.getContext())
            .hasPrecipitation(true)
            .temperature(0.25f)
            .downfall(0.8f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, BiomeColors.COLD_SEMI_HUMID.skyColor())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, BiomeColors.COLD_SEMI_HUMID.fogColor())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

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
                List.of(PlacedFeatures.VEGETATION_PATCH_CLIFF_GRASS,
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    VegetationPlacements.FLOWER_PLAINS,
                    VegetationPlacements.PATCH_GRASS_PLAIN,
                    VegetationPlacements.PATCH_WATERLILY,
                    VegetationPlacements.PATCH_SUGAR_CANE,
                    PlacedFeatures.VEGETATION_PILE_HAY_BALE,
                    PlacedFeatures.VEGETATION_ROOT_DIRT_BLOB),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.CREATURE, EntityType.COW, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.SHEEP, 10, 2, 4)
            .addMobSpawn(MobCategory.CREATURE, EntityType.PIG, 10, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.HAS_VILLAGE_PLAINS, BiomeTags.IS_OVERWORLD, BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS,
                BiomeTags.HAS_RUINED_PORTAL_STANDARD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_PILLAGER_OUTPOST,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(cold_plains);
    }

}
