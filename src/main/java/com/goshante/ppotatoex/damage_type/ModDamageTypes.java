package com.goshante.ppotatoex.damage_type;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static final ResourceKey<DamageType> REFLECT = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("ppotatoex", "reflect"));

    public static void bootstrap(BootstapContext<DamageType> context)
    {
        context.register(REFLECT, new DamageType("reflect", 0.1F));
    }
}
