package com.github.shanebeee.beer.mod.registration.feature.config;

import com.github.shanebeee.beer.api.registration.ConfiguredFeatureDefinition;
import com.github.shanebeee.beer.api.registration.PlacedFeatureDefinition;
import com.github.shanebeee.beer.mod.registry.ConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.BlockColumnFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomSelectorFeature;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

public class DecorConfigs {

    public static void register(ConfiguredFeatureRegistration reg) {
        BootstrapContext<Feature> context = reg.getContext();
        HolderGetter<Block> blockReg = context.lookup(Registries.BLOCK);

        ConfiguredFeatureDefinition basalt_pillar = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DECOR_BASALT_PILLAR, context)
            .config(new BlockColumnFeature(
                List.of(
                    new BlockColumnFeature.Layer(UniformInt.of(4, 11), BlockStateProvider.simple(Blocks.BASALT))
                ),
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                false))
            .build();
        reg.register(basalt_pillar);

        ConfiguredFeatureDefinition muddy_blob = ConfiguredFeatureDefinition.builder(ConfiguredFeatures.DECOR_MUDDY_BLOB, context)
            .config(new VegetationPatchFeature(
                blockReg.getOrThrow(BlockTags.MOSS_REPLACEABLE),
                new WeightedStateProvider(WeightedList.<BlockState>builder()
                    .add(Blocks.MUD.defaultBlockState(), 5)
                    .add(Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState(), 1)
                    .add(Blocks.MOSS_BLOCK.defaultBlockState(), 1)
                    .build()),
                PlacedFeatureDefinition.builder()
                    .configuredFeature(new RandomSelectorFeature(
                        List.of(
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new BlockColumnFeature(
                                    List.of(
                                        new BlockColumnFeature.Layer(UniformInt.of(2, 6),
                                            BlockStateProvider.simple(Blocks.PALE_HANGING_MOSS.defaultBlockState()
                                                .setValue(HangingMossBlock.TIP, false))),
                                        new BlockColumnFeature.Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.PALE_HANGING_MOSS))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.25f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new BlockColumnFeature(
                                    List.of(
                                        new BlockColumnFeature.Layer(UniformInt.of(1, 2),
                                            BlockStateProvider.simple(Blocks.CAVE_VINES_PLANT.defaultBlockState())),
                                        new BlockColumnFeature.Layer(ConstantInt.of(1), new WeightedStateProvider(
                                            WeightedList.<BlockState>builder()
                                                .add(Blocks.CAVE_VINES.defaultBlockState()
                                                    .setValue(CaveVinesBlock.BERRIES, true), 1)
                                                .add(Blocks.CAVE_VINES.defaultBlockState()
                                                    .setValue(CaveVinesBlock.BERRIES, false), 3)
                                                .build()
                                        ))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.025f),
                            new WeightedPlacedFeature(PlacedFeatureDefinition.builder()
                                .configuredFeature(new BlockColumnFeature(
                                    List.of(
                                        new BlockColumnFeature.Layer(ConstantInt.of(1),
                                            BlockStateProvider.simple(Blocks.HANGING_ROOTS))),
                                    Direction.DOWN,
                                    BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                    false)).build().getHolder(),
                                0.25f)),
                        PlacedFeatureDefinition.builder()
                            .configuredFeature(new BlockColumnFeature(
                                List.of(
                                    new BlockColumnFeature.Layer(ConstantInt.of(1),
                                        BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)),
                                    new BlockColumnFeature.Layer(UniformInt.of(1, 4),
                                        BlockStateProvider.simple(Blocks.MANGROVE_ROOTS))),
                                Direction.DOWN,
                                BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                                false)).build().getHolder()
                    )).build().getHolder(),
                CaveSurface.CEILING,
                UniformInt.of(2, 4),
                0.3f,
                3,
                0.25f,
                UniformInt.of(3, 7),
                0.1f
            ))
            .build();
        reg.register(muddy_blob);
    }

}
