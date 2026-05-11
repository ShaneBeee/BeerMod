package com.github.shanebeee.beer.mod.registration;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.DimensionTypeDefinition;
import com.github.shanebeee.beer.mod.registry.DimensionTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.AmbientSounds;
import net.minecraft.world.attribute.BackgroundMusic;
import net.minecraft.world.attribute.BedRule;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.dimension.DimensionType;

import java.awt.Color;

public class DimensionTypeRegistration extends BaseRegistration<DimensionType, DimensionTypeDefinition> {

    public DimensionTypeRegistration(BootstrapContext<DimensionType> context) {
        super(Registries.DIMENSION_TYPE, context);

        DimensionTypeDefinition overworld = DimensionTypeDefinition.builder(DimensionTypes.OVERWORLD, context)
            .ambientLight(0.0f)
            .coordinateScale(1.0)
            .defaultClock(WorldClocks.OVERWORLD)
            .hasCeiling(false)
            .hasEnderDragonFight(false)
            .hasSkyLight(true)
            .height(-256, 512, 512)
            .monsterSettings(new DimensionType.MonsterSettings(
                UniformInt.of(0, 7),
                0))
            .infiniburn(BlockTags.INFINIBURN_OVERWORLD)
            .setTimeline(TimelineTags.IN_OVERWORLD)

            // Attributes
            .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, AmbientSounds.LEGACY_CAVE_SETTINGS)
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic.OVERWORLD)
            .setAttribute(EnvironmentAttributes.BED_RULE, BedRule.CAN_SLEEP_WHEN_DARK)
            .setAttribute(EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, true)
            .setAttribute(EnvironmentAttributes.RESPAWN_ANCHOR_WORKS, false)
            .setAttribute(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, new Color(10, 10, 10).getRGB())
            .setAttribute(EnvironmentAttributes.CLOUD_COLOR, new Color(204, 255, 255, 1).getRGB())
            .setAttribute(EnvironmentAttributes.CLOUD_HEIGHT, 192.33f)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, new Color(192, 216, 255).getRGB())
            .setAttribute(EnvironmentAttributes.SKY_COLOR, new Color(120, 167, 255).getRGB())
            .build();

        register(overworld);
    }

}
