package com.goshante.ppotatoex.damage_type;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes
{
    public static final ResourceKey<DamageType> REFLECT = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(PoisonousPotatoExpansion.MOD_ID, "reflect"));

    public static void bootstrap(BootstrapContext<DamageType> context)
    {
        context.register(REFLECT, new DamageType("reflect", 0.1F));
    }
}
