package com.goshante.ppotatoex.datagen;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.damage_type.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = PoisonousPotatoExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDatagen extends DatapackBuiltinEntriesProvider
{

    public ModDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries)
    {
        super(output, registries, createDatapackEntriesBuilder(), Set.of("ppotatoex"));
    }

    private static RegistrySetBuilder createDatapackEntriesBuilder()
    {
        return new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}