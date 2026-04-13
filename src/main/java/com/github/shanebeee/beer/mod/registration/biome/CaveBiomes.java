package com.github.shanebeee.beer.mod.registration.biome;

import com.github.shanebeee.beer.api.registration.BiomeDefinition;
import com.github.shanebeee.beer.mod.registry.tags.BeerBiomeTags;
import com.github.shanebeee.beer.mod.registry.BeerBiomes;
import com.github.shanebeee.beer.mod.registry.PlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.animal.chicken.ChickenSoundVariant;
import net.minecraft.world.entity.animal.chicken.ChickenSoundVariants;
import net.minecraft.world.level.block.Blocks;

import java.awt.Color;
import java.util.List;
import java.util.Optional;

public class CaveBiomes {

    public static void register(BiomeRegistration reg) {
        int basaltColors = new Color(52, 1, 1).getRGB();
        BiomeDefinition basalt_cave = BiomeDefinition.builder(BeerBiomes.CAVE_BASALT_CAVE, reg.getContext())
            .temperature(0.5f)
            .downfall(0.5f)
            .hasPrecipitation(false)

            .waterColor(basaltColors)

            .setAttribute(EnvironmentAttributes.FOG_COLOR, basaltColors)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(168, 3, 3).getRGB())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, basaltColors)
            .setAttribute(EnvironmentAttributes.NIGHT_VISION_COLOR, basaltColors)

            .addDefaultOverworldCarvers()

            .features(
                List.of(
                    PlacedFeatures.DELTA_BASALT_DELTA,
                    PlacedFeatures.DELTA_BASALT_POOL),
                null,
                List.of(CavePlacements.AMETHYST_GEODE),
                List.of(CavePlacements.MONSTER_ROOM,
                    CavePlacements.MONSTER_ROOM_DEEP),
                null,
                null,
                null,
                List.of(PlacedFeatures.DECOR_BASALT_PILLAR,
                    PlacedFeatures.BLOB_BLACKSTONE,
                    PlacedFeatures.BLOB_BLACKSTONE_BRICKS,
                    PlacedFeatures.BLOB_MOSSY_STONE,
                    PlacedFeatures.BLOB_TUFF,
                    PlacedFeatures.BLOB_BASALT),
                null,
                null,
                null)

            .addMobSpawn(MobCategory.AMBIENT, EntityType.BAT, 10, 4, 8)
            .addMobSpawn(MobCategory.MONSTER, EntityType.CAVE_SPIDER, 100, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 30, 4, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.WITHER_SKELETON, 20, 1, 1)
            .addMobSpawn(MobCategory.MONSTER, EntityType.BLAZE, 3, 1, 1)

            .addToTag(BiomeTags.IS_OVERWORLD, BeerBiomeTags.HAS_MINESHAFT_SPRUCE, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(basalt_cave);

        BiomeDefinition forgotten_cave = BiomeDefinition.builder(BeerBiomes.CAVE_FORGOTTEN_CAVE, reg.getContext())
            .temperature(0.5f)
            .downfall(0.5f)
            .hasPrecipitation(false)

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
        reg.register(forgotten_cave);

        BiomeDefinition diorite_cave = BiomeDefinition.builder(BeerBiomes.CAVE_DIORITE_CAVE, reg.getContext())
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
        reg.register(diorite_cave);

        BiomeDefinition dry_cave = BiomeDefinition.builder(BeerBiomes.CAVE_DRY_CAVE, reg.getContext())
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

            .addToTag(BiomeTags.IS_OVERWORLD, BeerBiomeTags.HAS_MINESHAFT_SANDSTONE, BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(dry_cave);

        BiomeDefinition ice_cave = BiomeDefinition.builder(BeerBiomes.CAVE_ICE_CAVE, reg.getContext())
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
        reg.register(ice_cave);

        BiomeDefinition muddy_fen = BiomeDefinition.builder(BeerBiomes.CAVE_MUDDY_FEN, reg.getContext())
            .hasPrecipitation(false)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(new Color(43, 38, 29).getRGB())

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(30, 31, 21).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, new Color(15, 13, 10).getRGB())
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.0f)
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(130, 115, 135).getRGB())

            .addDefaultUndergroundOreFeatures()
            .features(null,
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.DELTA_MUDDY_DELTA,
                    PlacedFeatures.DECOR_MUDDY_BLOB),
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
        reg.register(muddy_fen);

        BiomeDefinition plain_Cave = BiomeDefinition.builder(BeerBiomes.CAVE_PLAIN_CAVE, reg.getContext())
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
        reg.register(plain_Cave);

        // FOR THE MEMEs
        // Not a real biome put in place
        ChickenSoundVariant.ChickenSoundSet chickenSoundSet = SoundEvents.CHICKEN_SOUNDS.get(ChickenSoundVariants.SoundSet.CLASSIC).adultSounds();
        BiomeDefinition lava_chicken = BiomeDefinition.builder(BeerBiomes.CAVE_LAVA_CHICKEN, reg.getContext())
            .hasPrecipitation(false)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, -13487566)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 8846572)
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0.0f)
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                Optional.empty(),
                Optional.empty(),
                List.of(new AmbientAdditionsSettings(SoundEvents.MUSIC_DISC_LAVA_CHICKEN, 0.0000001f),
                    new AmbientAdditionsSettings(SoundEvents.CHICKEN_HURT_BABY, 0.0005f),
                    new AmbientAdditionsSettings(chickenSoundSet.hurtSound(), 0.0005f),
                    new AmbientAdditionsSettings(SoundEvents.CHICKEN_AMBIENT_BABY, 0.0005f),
                    new AmbientAdditionsSettings(chickenSoundSet.ambientSound(), 0.0005f)))
            )

            .addDefaultUndergroundOreFeatures()
            .features(List.of(PlacedFeatures.DELTA_STONE_LAVA_DELTA),
                null,
                null,
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.REPLACE_STONE_TO_STONE_BRICKS),
                null,
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addMobSpawn(MobCategory.MONSTER, EntityType.CHICKEN, 2, 2, 4)
            .addMobSpawn(MobCategory.MONSTER, EntityType.ZOMBIE, 3, 1, 1)

            .addToTag(BiomeTags.IS_OVERWORLD, BiomeTags.HAS_MINESHAFT,
                BiomeTags.HAS_TRIAL_CHAMBERS, BiomeTags.STRONGHOLD_BIASED_TO)

            .build();
        reg.register(lava_chicken);

        BiomeDefinition smoky_cave = BiomeDefinition.builder(BeerBiomes.CAVE_SMOKY_CAVE, reg.getContext())
            .hasPrecipitation(false)
            .temperature(0.8f)
            .downfall(0.2f)
            .waterColor(4159204)

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, -13487566)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 0.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 15.0f)
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
                List.of(PlacedFeatures.DECOR_SMOKY_GRATE,
                    PlacedFeatures.DECOR_HANGING_CHAIN),
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
        reg.register(smoky_cave);

        BiomeDefinition sulfur_cave = BiomeDefinition.builder(BeerBiomes.CAVE_SULFUR_CAVE, reg.getContext())
            .temperature(0.8f)
            .downfall(0.4f)
            .waterColor(new Color(52, 191, 137).getRGB())

            .setAttribute(EnvironmentAttributes.SKY_COLOR, 0)
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, new Color(23, 84, 60).getRGB())
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 5.0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 50.0f)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(45, 58, 16).getRGB())
            .setAttribute(EnvironmentAttributes.BLOCK_LIGHT_TINT, new Color(45, 58, 16).getRGB())

            .addDefaultOverworldCarvers()
            .addDefaultUndergroundOreFeatures()
            .addDefaultMonsterRoomFeatures()
            .features(null,
                List.of(MiscOverworldPlacements.LAKE_LAVA_UNDERGROUND,
                    MiscOverworldPlacements.LAKE_LAVA_SURFACE),
                List.of(CavePlacements.AMETHYST_GEODE),
                null,
                null,
                null,
                null,
                List.of(PlacedFeatures.BLOB_SULFUR,
                    PlacedFeatures.DELTA_SULFUR_POOL,
                    PlacedFeatures.BLOB_SOULSAND),
                null,
                null,
                List.of(MiscOverworldPlacements.FREEZE_TOP_LAYER))

            .addDefaultCaveSpawns()
            .addDefaultMonsterSpawns(false)

            .build();
        reg.register(sulfur_cave);
    }
}
