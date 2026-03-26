package com.github.shanebeee.beer.api.registration;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Definable<T> {

    private final ResourceKey<T> resourceKey;
    private final T value;
    private final @Nullable Holder.Reference<T> holder;
    private final List<TagKey<T>> tagKeys;

    public Definable(ResourceKey<T> resourceKey, @NotNull T value, @Nullable Holder.Reference<T> holder, List<TagKey<T>> tagKeys) {
        this.resourceKey = resourceKey;
        this.value = value;
        this.holder = holder;
        this.tagKeys = tagKeys;
    }

    public Definable(ResourceKey<T> resourceKey, @NotNull T value, @Nullable Holder.Reference<T> holder) {
        this.resourceKey = resourceKey;
        this.value = value;
        this.holder = holder;
        this.tagKeys = List.of();
    }

    public @NotNull T getValue() {
        return this.value;
    }

    /**
     * Get the {@link Holder.Reference} of this definable.
     * If not available, will return a direct holder.
     *
     * @return Holder reference of this definable.
     */
    public @NotNull Holder<T> getHolder() {
        if (this.holder != null) {
            return this.holder;
        }
        return Holder.direct(this.value);
    }

    /**
     * Get the {@link ResourceKey} of this definable.
     *
     * @return ResourceKey of this definable.
     */
    public @Nullable ResourceKey<T> getResourceKey() {
        return this.resourceKey;
    }

    public @NotNull List<TagKey<T>> getTagKeys() {
        return this.tagKeys;
    }

}
