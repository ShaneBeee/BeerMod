package com.github.shanebeee.beer.mod.registration.structure.template;

import com.github.shanebeee.beer.api.registration.BaseRegistration;
import com.github.shanebeee.beer.api.registration.TemplatePoolDefinition;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class TemplatePoolRegistration extends BaseRegistration<StructureTemplatePool, TemplatePoolDefinition> {

    public TemplatePoolRegistration(BootstrapContext<StructureTemplatePool> context) {
        super(Registries.TEMPLATE_POOL, context);
        Mineshafts.register(this);
    }

}
