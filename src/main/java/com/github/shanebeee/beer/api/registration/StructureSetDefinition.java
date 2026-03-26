package com.github.shanebeee.beer.api.registration;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StructureSetDefinition extends Definable<StructureSet> {

    public StructureSetDefinition(ResourceKey<StructureSet> resourceKey, @NonNull StructureSet value, Holder.@Nullable Reference<StructureSet> holder, List<TagKey<StructureSet>> tagKeys) {
        super(resourceKey, value, holder, tagKeys);
    }

    public static Builder builder(ResourceKey<StructureSet> resourceKey, BootstrapContext<StructureSet> context) {
        return new Builder(resourceKey, context);
    }

    public static class Builder {

        private final ResourceKey<StructureSet> resourceKey;
        private final BootstrapContext<StructureSet> context;
        private final List<TagKey<StructureSet>> tagKeys = new ArrayList<>();
        private final List<StructureSet.StructureSelectionEntry> set = new ArrayList<>();
        private StructurePlacement placement;

        private Builder(ResourceKey<StructureSet> resourceKey, BootstrapContext<StructureSet> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public Builder placement(StructurePlacement placement) {
            this.placement = placement;
            return this;
        }

        public Builder addStructure(ResourceKey<Structure> key, int weight) {
            Holder.Reference<Structure> structure = this.context.lookup(Registries.STRUCTURE).getOrThrow(key);
            this.set.add(StructureSet.entry(structure, weight));
            return this;
        }

        public Builder addStructure(ResourceKey<Structure> key) {
            Holder.Reference<Structure> structure = this.context.lookup(Registries.STRUCTURE).getOrThrow(key);
            this.set.add(StructureSet.entry(structure));
            return this;
        }

        public StructureSetDefinition build() {
            StructureSet structureSet = new StructureSet(this.set, this.placement);
            Holder.Reference<StructureSet> holder = this.context.register(this.resourceKey, structureSet);
            return new StructureSetDefinition(this.resourceKey, structureSet, holder, this.tagKeys);
        }
    }

}
