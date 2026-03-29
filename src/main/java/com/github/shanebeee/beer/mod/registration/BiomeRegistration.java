package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.BeerBiomeTags;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Optional;

public class BiomeRegistration extends BaseRegistration<Biome, BiomeDefinition> {

    public BiomeRegistration(BootstrapContext<Biome> context) {
        super(Registries.BIOME, context);
        caveBiomes(context);
        coastBiomes(context);
        desertBiomes(context);
        forestBiomes(context);
        plainsBiomes(context);
        riverBiomes(context);
        swampBiomes(context);
    }

    private void caveBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition forgotten_cave = BiomeDefinition.builder(BeerBiomes.CAVE_FORGOTTEN_CAVE, context)
            .temperature(0.5f)
            .downfall(0.5f)
            .hasPrecipitation(true)

            .waterColor(-11711227)
            .particle(ParticleTypes.ASH, 0.2f)

            .setAttribute(EnvironmentAttributes.FOG_COLOR, -11711227)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 8103167)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, -11711227)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 329011)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0F)),
                List.of(new AmbientAdditionsSettings(Holder.direct(SoundEvents.FOX_SCREECH), 0.0011f))
            ))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(
                new Music(SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY, 12000, 24000, true)))
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.5f)

            .addDefaultOverworldCarvers()

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.DELTA_FORGOTTEN_DELTA),
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                null,
                null,
                null,
                List.of(PlacedFeatures.BLOB_DEAD_BRAIN,
                    PlacedFeatures.BLOB_DEAD_BUBBLE,
                    PlacedFeatures.BLOB_DEAD_FIRE),
                List.of(MiscOverworldPlacements.SPRING_WATER, MiscOverworldPlacements.SPRING_LAVA),
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CAVE_SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 30, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 20, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.EVOKER, 100, 1, 2)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.SALMON, 25, 8, 8)

            .addToTag(BiomeTags.IS_OVERWORLD, BeerBiomeTags.HAS_MINESHAFT_SPRUCE, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(forgotten_cave);

        BiomeDefinition diorite_cave = BiomeDefinition.builder(BeerBiomes.CAVE_DIORITE_CAVE, context)
            .temperature(0.5f)
            .downfall(0.5f)
            .hasPrecipitation(true)

            .waterColor(-14168075)

            .setAttribute(EnvironmentAttributes.FOG_COLOR, -16573926)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 8103167)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, -14168075)

            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0F)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 0.0011f))
            ))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(
                new Music(SoundEvents.MUSIC_BIOME_LUSH_CAVES, 12000, 24000, true)))
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.5f)

            .addDefaultOverworldCarvers()

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
                List.of(PlacedFeatures.REPLACE_STONE_TO_DIORITE,
                    PlacedFeatures.REPLACE_DEEPSLATE_TO_DIORITE,
                    PlacedFeatures.BLOB_STONE,
                    PlacedFeatures.BLOB_TUFF),
                List.of(MiscOverworldPlacements.SPRING_WATER, MiscOverworldPlacements.SPRING_LAVA),
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.BOGGED, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.COD, 25, 8, 8)

            .addToTag(BiomeTags.IS_OVERWORLD, BeerBiomeTags.HAS_MINESHAFT_SPRUCE, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(diorite_cave);

        BiomeDefinition dry_cave = BiomeDefinition.builder(BeerBiomes.CAVE_DRY_CAVE, context)
            .temperature(0.5f)
            .downfall(0.5f)
            .hasPrecipitation(true)

            // Special Effects
            .waterColor(-6390206)

            // Attributes
            // Colors
            .setAttribute(EnvironmentAttributes.FOG_COLOR, -11058147)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 8103167)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, -6390206)

            // Sounds
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0F)),
                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 0.0011f))
            ))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(
                new Music(SoundEvents.MUSIC_BIOME_LUSH_CAVES, 12000, 24000, true)))
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.5f)

            // Particles
            .particle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), 0.005f)
            .particle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.BROWN_CONCRETE_POWDER.defaultBlockState()), 0.005f)

            // Carvers
            .addDefaultOverworldCarvers()

            // Features
            .addDefaultUndergroundOreFeatures()
            .features(null,
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE,
                    PlacedFeatures.DELTA_DRY_CAVE_DELTA),
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                null,
                null,
                null,
                List.of(PlacedFeatures.TERRAIN_BROWN_CONCRETE_DISK),
                List.of(MiscOverworldPlacements.SPRING_WATER, MiscOverworldPlacements.SPRING_LAVA),
                List.of(VegetationPlacements.PATCH_TALL_GRASS_2,
                    PlacedFeatures.DECOR_HANGING_FENCE),
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            // Spawners
            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.HUSK, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.PARCHED, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.WATER_AMBIENT, EntityType.TROPICAL_FISH, 25, 8, 8)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(dry_cave);

        BiomeDefinition ice_cave = BiomeDefinition.builder(BeerBiomes.CAVE_ICE_CAVE, context)
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)

            .waterColor(6003155)
            .foliageColorOverride(442658)
            .grassColorOverride(6017902)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 5634012)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 16564102)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)

            .particle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.BLUE_ICE.defaultBlockState()), 0.005f)
            .particle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SNOW_BLOCK.defaultBlockState()), 0.005f)

            .addDefaultUndergroundOreFeatures()
            .features(null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.REPLACE_DEEPSLATE_TO_ICE,
                    PlacedFeatures.REPLACE_STONE_TO_ICE, PlacedFeatures.REPLACE_STONE_TO_SNOW),
                null,
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.MONSTER, EntityType.ILLUSIONER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.STRAY, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 3, 1, 1)
            .addMobSpawn(MobCategory.CREATURE, EntityType.FROG, 10, 1, 2)

            .addToTag(BiomeTags.SPAWNS_COLD_VARIANT_FROGS, BiomeTags.IS_OVERWORLD,
                BiomeTags.HAS_MINESHAFT, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(ice_cave);

        BiomeDefinition plain_Cave = BiomeDefinition.builder(BeerBiomes.CAVE_PLAIN_CAVE, context)
            .hasPrecipitation(false)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, -13487566)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)

            .addDefaultUndergroundOreFeatures()
            .features(null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.BLOB_TERRACOTTA_LIGHT_GRAY,
                    PlacedFeatures.BLOB_TERRACOTTA_LIGHT_BLUE,
                    PlacedFeatures.DELTA_PLAIN_CAVE_DELTA,
                    PlacedFeatures.DECOR_HANGING_STONE),
                null,
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.MONSTER, EntityType.ILLUSIONER, 1, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.STRAY, 3, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 3, 1, 1)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.HAS_MINESHAFT,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(plain_Cave);
    }

    private void coastBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition beachy_beach = BiomeDefinition.builder(BeerBiomes.COAST_BEACHY_COAST, context)
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.4f)

            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
        register(beachy_beach);

        BiomeDefinition coast = BiomeDefinition.builder(BeerBiomes.COAST_COAST, context)
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.5f)
            .waterColor(6003155)
            .foliageColorOverride(442658)
            .grassColorOverride(11060330)

            .particle(ParticleTypes.CHERRY_LEAVES, 0.0005f)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 5634012)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 16564102)
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
                    PlacedFeatures.TERRAIN_SAND_SHORE_DISK,
                    PlacedFeatures.TREE_PALM_BEACH_PALM,
                    "wythers:vegetation/placed_random_patch/large_ferns_dense_forests",
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    "wythers:vegetation/placed_random_patch/dark_oak_roots",
                    PlacedFeatures.TERRAIN_MOSSIFY_GRASS,
                    "wythers:vegetation/bushes_mediterranean",
                    "wythers:vegetation/placed_random_patch/mediterranean_lilacs",
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
        register(coast);

        BiomeDefinition dry_coast = BiomeDefinition.builder(BeerBiomes.COAST_DRY_COAST, context)
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.05f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
                    "wythers:vegetation/placed_random_patch/dark_oak_roots",
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
        register(dry_coast);

        BiomeDefinition frozen_beach = BiomeDefinition.builder(BeerBiomes.COAST_FROZEN_BEACH, context)
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
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 8364543)
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
        register(frozen_beach);

        BiomeDefinition lush_coast = BiomeDefinition.builder(BeerBiomes.COAST_LUSH_COAST, context)
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)
            .waterColor(6003155)
            .foliageColorOverride(442658)
            .grassColorOverride(6017902)

            .particle(ParticleTypes.CHERRY_LEAVES, 0.0005f)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 5634012)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 16564102)
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
                    "wythers:vegetation/placed_random_patch/large_ferns_dense_forests",
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    "wythers:vegetation/placed_random_patch/dark_oak_roots",
                    PlacedFeatures.TERRAIN_MOSSIFY_GRASS,
                    "wythers:vegetation/bushes_mediterranean",
                    "wythers:vegetation/placed_random_patch/mediterranean_lilacs",
                    "wythers:vegetation/placed_random_patch/dripleaves_bayou",
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
        register(lush_coast);

        BiomeDefinition palm_beach = BiomeDefinition.builder(BeerBiomes.COAST_PALM_BEACH, context)
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)
            .foliageColorOverride(442658)
            .grassColorOverride(6017902)
            .waterColor(6003155)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 5634012)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 16564102)
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
        register(palm_beach);
    }

    private void desertBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition dry_desert = BiomeDefinition.builder(BeerBiomes.DESERT_DRY_DESERT, context)
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
        register(dry_desert);

        BiomeDefinition lush_desert = BiomeDefinition.builder(BeerBiomes.DESERT_LUSH_DESERT, context)
            .hasPrecipitation(true)
            .temperature(0.7f)
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
            .features(List.of(PlacedFeatures.REPLACE_GRASS_TO_SAND,
                    PlacedFeatures.DELTA_LUSH_DESERT_DELTA),
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
                List.of(PlacedFeatures.TREE_LUSH_DESERT_PALM,
                    PlacedFeatures.VEGETATION_LUSH_DESERT_AZALEA_SCRUB,
                    PlacedFeatures.VEGETATION_AZALEA_BUSH_OR_SCRUB,
                    "wythers:vegetation/placed_random_patch/dark_oak_roots",
                    VegetationPlacements.FLOWER_DEFAULT,
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
        register(lush_desert);
    }

    private void forestBiomes(BootstrapContext<Biome> context) {


        BiomeDefinition dry_forest = BiomeDefinition.builder(BeerBiomes.FOREST_DRY_FOREST, context)
            .hasPrecipitation(false)
            .temperature(0.7f)
            .downfall(0.0f)
            .waterColor(7768221)
            .foliageColorOverride(-7250899)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.0f)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)

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
        register(dry_forest);

        BiomeDefinition moss_garden = BiomeDefinition.builder(BeerBiomes.FOREST_MOSS_GARDEN, context)
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
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 12171705)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 8484720)
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

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 8, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 95, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE_VILLAGER, 5, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SKELETON, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CREEPER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.SLIME, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ENDERMAN, 10, 1, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITCH, 5, 1, 1)
            .addMobSpawn(MobCategory.WATER_CREATURE, EntityType.GLOW_SQUID, 10, 4, 6)

            .addToTag(BiomeTags.IS_FOREST, BiomeTags.IS_OVERWORLD, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        register(moss_garden);

        BiomeDefinition tall_oak = BiomeDefinition.builder(BeerBiomes.FOREST_TALL_OAK, context)
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)

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
        register(tall_oak);

        BiomeDefinition lush_forest = BiomeDefinition.builder(BeerBiomes.FOREST_LUSH_FOREST, context)
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.5f)
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
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7782102)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
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
                List.of("wythers:vegetation/placed_random_patch/flooded_savanna_water_plants",
                    "wythers:vegetation/placed_random_patch/flowers_tropical_forest",
                    PlacedFeatures.TREE_TROPICAL_FOREST,
                    VegetationPlacements.PATCH_TALL_GRASS,
                    VegetationPlacements.PATCH_GRASS_SAVANNA,
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
        register(lush_forest);
    }

    private void plainsBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition dry_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_DRY_PLAINS, context)
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.05f)
            .waterColor(5336976)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 16379351)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2171215)

            .addDefaultOverworldCarvers()
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
        register(dry_plains);

        BiomeDefinition lush_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_LUSH_PLAINS, context)
            .hasPrecipitation(true)
            .temperature(0.95f)
            .downfall(0.5f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
                    PlacedFeatures.VEGETATION_PATCH_HAY_BALE,
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
        register(lush_plains);

        BiomeDefinition temperate_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_TEMPERATE_PLAINS, context)
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7907327)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
                    PlacedFeatures.VEGETATION_PATCH_HAY_BALE,
                    PlacedFeatures.VEGETATION_PATCH_CHERRY_PETALS,
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
        register(temperate_plains);

        BiomeDefinition cold_plains = BiomeDefinition.builder(BeerBiomes.PLAINS_COLD_PLAINS, context)
            .hasPrecipitation(true)
            .temperature(0.25f)
            .downfall(0.8f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, -8543233)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
                    PlacedFeatures.VEGETATION_PATCH_HAY_BALE,
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
        register(cold_plains);
    }

    private void riverBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition desert_river = BiomeDefinition.builder(BeerBiomes.RIVER_DESERT_RIVER, context)
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)
            .waterColor(4112789)
            .foliageColorOverride(9285927)

            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 1.0f)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7788235)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
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
        register(desert_river);

        BiomeDefinition lush_river = BiomeDefinition.builder(BeerBiomes.RIVER_LUSH_RIVER, context)
            .hasPrecipitation(true)
            .temperature(2f)
            .downfall(0.2f)
            .waterColor(3832426)
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
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7782102)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 13880215)
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
                List.of("wythers:vegetation/placed_random_patch/flooded_savanna_water_plants",
                    "wythers:vegetation/placed_random_patch/flowers_tropical_forest",
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
        register(lush_river);

        BiomeDefinition temperate_river = BiomeDefinition.builder(BeerBiomes.RIVER_TEMPERATE_RIVER, context)
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
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 7391487)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
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
        register(temperate_river);
    }

    private void swampBiomes(BootstrapContext<Biome> context) {
        BiomeDefinition cold_swamp = BiomeDefinition.builder(BeerBiomes.SWAMP_COLD_SWAMP, context)
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
        register(cold_swamp);

        BiomeDefinition dripleaf_swamp = BiomeDefinition.builder(BeerBiomes.SWAMP_DRIPLEAF_SWAMP, context)
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
                List.of(VegetationPlacements.TREES_SWAMP,
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
        register(dripleaf_swamp);
    }

}
