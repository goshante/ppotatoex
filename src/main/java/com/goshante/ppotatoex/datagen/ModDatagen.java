package com.goshante.ppotatoex.datagen;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.damage_type.ModDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = PoisonousPotatoExpansion.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModDatagen
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        var generator = event.getGenerator();
        var registries = event.getLookupProvider();
        var pack = generator.getVanillaPack(true);
        var existingFileHelper = event.getExistingFileHelper();

        pack.addProvider(output -> new DatapackBuiltinEntriesProvider(output, registries,
                createDatapackEntriesBuilder(), Set.of(PoisonousPotatoExpansion.MOD_ID)));
    }

    private static RegistrySetBuilder createDatapackEntriesBuilder()
    {
        return new RegistrySetBuilder()
                .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}