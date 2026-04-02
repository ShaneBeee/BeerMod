package com.github.shanebeee.beer.api.registration;

import com.github.shanebeee.beer.mod.Beer;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StructureDefinition extends Definable<Structure> {

    public StructureDefinition(ResourceKey<Structure> resourceKey, @NonNull Structure value,
                               Holder.@Nullable Reference<Structure> holder, List<TagKey<Structure>> tagKeys) {
        super(resourceKey, value, holder, tagKeys);
    }

    public static JigsawBuilder jigsawBuilder(ResourceKey<Structure> resourceKey, BootstrapContext<Structure> context) {
        return new JigsawBuilder(resourceKey, context);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static class JigsawBuilder {

        private final ResourceKey<Structure> resourceKey;
        private final BootstrapContext<Structure> context;

        // Settings
        private TagKey<Biome> biomeTagKey;
        private final Map<MobCategory, StructureSpawnOverride> mobs = new HashMap<>();
        private GenerationStep.Decoration step = GenerationStep.Decoration.SURFACE_STRUCTURES;
        private int maxDepth = 10;
        private TerrainAdjustment terrainAdjustment = TerrainAdjustment.NONE;
        private ResourceKey<StructureTemplatePool> start;
        private HeightProvider startHeight;
        private boolean useExpansionHack = false;
        private Optional<Identifier> startJigsawName = Optional.empty();
        private Optional<Heightmap.Types> projectStartToHeightmap = Optional.empty();
        private JigsawStructure.MaxDistance maxDistanceFromCenter;
        private DimensionPadding dimensionPadding = DimensionPadding.ZERO;
        private LiquidSettings liquidSettings = LiquidSettings.APPLY_WATERLOGGING;
        private final List<TagKey<Structure>> tagKeys = new ArrayList<>();

        public JigsawBuilder(ResourceKey<Structure> resourceKey, BootstrapContext<Structure> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public JigsawBuilder biomeTag(TagKey<Biome> biomeTagKey) {
            this.biomeTagKey = biomeTagKey;
            return this;
        }

        public JigsawBuilder maxDepth(int maxDepth) {
            this.maxDepth = maxDepth;
            return this;
        }

        public JigsawBuilder jigsawStartName(Identifier jigsawStartName) {
            this.startJigsawName = Optional.of(jigsawStartName);
            return this;
        }

        public JigsawBuilder projectStartToHeightmap(Heightmap.Types heightmap) {
            this.projectStartToHeightmap = Optional.of(heightmap);
            return this;
        }

        public JigsawBuilder mobSpawnOverride(MobCategory category, StructureSpawnOverride spawnOverride) {
            this.mobs.put(category, spawnOverride);
            return this;
        }

        public JigsawBuilder step(GenerationStep.Decoration step) {
            this.step = step;
            return this;
        }

        public JigsawBuilder terrainAdjustment(TerrainAdjustment terrainAdjustment) {
            this.terrainAdjustment = terrainAdjustment;
            return this;
        }

        public JigsawBuilder start(ResourceKey<StructureTemplatePool> start) {
            this.start = start;
            return this;
        }

        public JigsawBuilder startHeight(HeightProvider startHeight) {
            this.startHeight = startHeight;
            return this;
        }

        public JigsawBuilder useExpansionHack(boolean useExpansionHack) {
            this.useExpansionHack = useExpansionHack;
            return this;
        }

        public JigsawBuilder maxDistanceFromCenter(int horizontal, int vertical) {
            this.maxDistanceFromCenter = new JigsawStructure.MaxDistance(horizontal, vertical);
            return this;
        }

        public JigsawBuilder maxDistanceFromCenter(int value) {
            this.maxDistanceFromCenter = new JigsawStructure.MaxDistance(value, value);
            return this;
        }

        public JigsawBuilder dimensionPadding(DimensionPadding dimensionPadding) {
            this.dimensionPadding = dimensionPadding;
            return this;
        }

        public JigsawBuilder liquidSettings(LiquidSettings liquidSettings) {
            this.liquidSettings = liquidSettings;
            return this;
        }

        @SafeVarargs
        public final JigsawBuilder addToTag(TagKey<Structure>... tagKeys) {
            Collections.addAll(this.tagKeys, tagKeys);
            return this;
        }

        public StructureDefinition build() {
            HolderSet.Named<Biome> orThrow = this.context.lookup(Registries.BIOME).getOrThrow(this.biomeTagKey);
            Structure.StructureSettings structureSettings = new Structure.StructureSettings(
                orThrow,
                this.mobs,
                this.step,
                this.terrainAdjustment
            );

            Holder.Reference<StructureTemplatePool> startPool = this.context.lookup(Registries.TEMPLATE_POOL).getOrThrow(this.start);
            JigsawStructure jigsawStructure = new JigsawStructure(structureSettings, startPool,
                this.startJigsawName,
                this.maxDepth,
                this.startHeight,
                this.useExpansionHack,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter,
                List.of(),
                this.dimensionPadding,
                this.liquidSettings);

            Holder.Reference<Structure> holder;
            if (!this.resourceKey.identifier().getNamespace().equalsIgnoreCase(Beer.MOD_ID)) {
                holder = this.context.lookup(Registries.STRUCTURE).getOrThrow(this.resourceKey);
            } else {
                holder = this.context.register(this.resourceKey, jigsawStructure);
            }

            return new StructureDefinition(this.resourceKey, jigsawStructure, holder, this.tagKeys);
        }
    }

}
