package com.github.shanebeee.beer.api.registration;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TemplatePoolDefinition {

    private final ResourceKey<StructureTemplatePool> resourceKey;
    private final StructureTemplatePool templatePool;
    private final List<TagKey<StructureTemplatePool>> tagKeys;

    private TemplatePoolDefinition(ResourceKey<StructureTemplatePool> resourceKey, StructureTemplatePool templatePool, List<TagKey<StructureTemplatePool>> tagKeys) {
        this.resourceKey = resourceKey;
        this.templatePool = templatePool;
        this.tagKeys = tagKeys;
    }

    public ResourceKey<StructureTemplatePool> getResourceKey() {
        return this.resourceKey;
    }

    public StructureTemplatePool getValue() {
        return this.templatePool;
    }

    public List<TagKey<StructureTemplatePool>> getTagKeys() {
        return this.tagKeys;
    }

    public static Builder builder(ResourceKey<StructureTemplatePool> resourceKey, BootstrapContext<StructureTemplatePool> context) {
        return new Builder(resourceKey, context);
    }

    public static class Builder {

        private final ResourceKey<StructureTemplatePool> resourceKey;
        private final BootstrapContext<StructureTemplatePool> context;
        private final List<Pair<Function<Projection, ? extends StructurePoolElement>, Integer>> templates = new ArrayList<>();
        private ResourceKey<StructureTemplatePool> fallback = Pools.EMPTY;
        private Projection projection = Projection.RIGID;

        private Builder(ResourceKey<StructureTemplatePool> resourceKey, BootstrapContext<StructureTemplatePool> context) {
            this.resourceKey = resourceKey;
            this.context = context;
        }

        public Builder addTemplate(Function<Projection, ? extends StructurePoolElement> element, int weight) {
            this.templates.add(Pair.of(element, weight));
            return this;
        }

        public Builder fallback(ResourceKey<StructureTemplatePool> fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder projection(Projection projection) {
            this.projection = projection;
            return this;
        }

        public TemplatePoolDefinition build() {
            Holder.Reference<StructureTemplatePool> pool = this.context.lookup(Registries.TEMPLATE_POOL).getOrThrow(this.fallback);
            StructureTemplatePool structureTemplatePool = new StructureTemplatePool(pool, this.templates, this.projection);
            this.context.register(this.resourceKey, structureTemplatePool);
            return new TemplatePoolDefinition(this.resourceKey, structureTemplatePool, List.of());
        }

    }

}
