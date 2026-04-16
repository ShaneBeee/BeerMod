package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import com.github.shanebeee.beer.mod.registry.tags.TNTBiomeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class IslandBiomes {

    public static void register(BiomeRegistration reg) {
        BootstrapContext<Biome> context = reg.getContext();

        BiomeDefinition cotton_candy = cottonCandy(BeerBiomes.ISLAND_COTTON_CANDY_ISLAND, context, false);
        reg.register(cotton_candy);

        BiomeDefinition cotton_candy_alt = cottonCandy(BeerBiomes.ISLAND_COTTON_CANDY_ISLAND_ALT, context, true);
        reg.register(cotton_candy_alt);

        BiomeDefinition forgotten_island = BiomeDefinition.builder(BeerBiomes.ISLAND_FORGOTTEN_ISLAND, context)
            .temperature(0.15f)
            .downfall(0.0f)
            .hasPrecipitation(true)

            .waterColor(new Color(153, 101, 248).getRGB())
            .grassColorOverride(new Color(78, 79, 78).getRGB())
            .particle(ParticleTypes.ASH, 0.3f)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, new Color(38, 44, 39).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(77, 77, 5).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.CLOUD_HEIGHT, 85.5f)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, new Color(69, 72, 52).getRGB())
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(77, 77, 5).getRGB())
            .setAttribute(EnvironmentAttributes.SKY_LIGHT_COLOR, new Color(77, 77, 5).getRGB())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, new Color(53, 35, 86).getRGB())

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0F)),
                List.of(new AmbientAdditionsSettings(Holder.direct(SoundEvents.FOX_SCREECH), 0.0011f))
            ))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(
                new Music(SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY, 12000, 24000, true)))
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.5f)

            .addDefaultOverworldCarvers()

            .features(List.of(PlacedFeatures.BLOB_DEAD_BRAIN,
                    PlacedFeatures.BLOB_DEAD_BUBBLE,
                    PlacedFeatures.BLOB_DEAD_FIRE,
                    PlacedFeatures.DELTA_FORGOTTEN_ISLAND_DELTA),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.TREE_DEAD_CORAL_TREES),
                null)

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CAVE_SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PILLAGER, 10, 4, 6)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 20, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.EVOKER, 100, 1, 2)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 10, 4, 4)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.HAS_SHIPWRECK_BEACHED,
                BiomeTags.HAS_NETHER_FOSSIL, BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)

            .build();
        reg.register(forgotten_island);

        BiomeDefinition moss_garden = BiomeDefinition.builder(BeerBiomes.ISLAND_MOSS_GARDEN_ISLAND, context)
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
            .modifyAttribute(EnvironmentAttributes.SKY_LIGHT_FACTOR, FloatModifier.MULTIPLY, 0.1f)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(1, 6, 52).getRGB())
            .setAttribute(EnvironmentAttributes.SKY_COLOR, new Color(23, 24, 28).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(30, 5, 5).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 8f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 15f)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, new Color(4, 63, 65).getRGB())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 5597568)
            .setAttribute(EnvironmentAttributes.NIGHT_VISION_COLOR, new Color(236, 90, 59).getRGB())

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
                List.of(PlacedFeatures.TREE_MOSS_GARDEN_ISLAND,
                    PlacedFeatures.TREE_FALLEN_CRIMSON_STEM,
                    PlacedFeatures.VEGETATION_MOSS_PATCH,
                    VegetationPlacements.PATCH_GRASS_FOREST),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultOverworldCarvers()

            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)

            .addToTag(BiomeTags.IS_FOREST, BiomeTags.IS_OVERWORLD, TNTBiomeTags.TNT_HAS_PILLAGER_OUTPOST_NILOTIC)

            .build();
        reg.register(moss_garden);
    }

    private static BiomeDefinition cottonCandy(ResourceKey<Biome> biomeKey, BootstrapContext<Biome> context, boolean alt) {
        int grassColor = new Color(238, 163, 245).getRGB();
        int foliageColor = new Color(12, 238, 238).getRGB();
        return BiomeDefinition.builder(biomeKey, context)
            .temperature(1.0f)
            .downfall(1.0f)
            .waterColor(alt ? foliageColor : grassColor)
            .grassColorOverride(alt ? grassColor : foliageColor)
            .foliageColorOverride(alt ? foliageColor : grassColor)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, alt ? grassColor : foliageColor)
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, alt ? foliageColor : grassColor)

            .features(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(
                    PlacedFeatures.TREE_COTTON_CANDY_TREES,
                    VegetationPlacements.PATCH_GRASS_JUNGLE,
                    VegetationPlacements.FLOWER_FLOWER_FOREST),
                null)

            .build();

    }

}
