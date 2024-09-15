package com.goshante.ppotatoex;

import com.goshante.ppotatoex.block.ModBlocks;
import com.goshante.ppotatoex.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PoisonousPotatoExpansion.MOD_ID);

    public static final RegistryObject<CreativeModeTab> POTATO_TAB = CREATIVE_MODE_TABS.register("potato_tab",
            ()->CreativeModeTab.builder()
                    .icon(()->new ItemStack(Items.POTATO))
                    .title(Component.translatable("creativetab.potato_tab"))
                    .displayItems((itemDisplayParameters, output) ->
        {
            //Items
            output.accept(Items.POTATO);
            output.accept(Items.BAKED_POTATO);
            output.accept(Items.POISONOUS_POTATO);
            output.accept(ModItems.item_GreatPoisonousPotato.get());
            output.accept(ModItems.item_GreatPotato.get());
            output.accept(ModItems.item_BottleOfPotatoPoison.get());
            output.accept(ModItems.item_BottleOfPotatoPoison.get());
            output.accept(ModItems.item_GoldenGreatPotato.get());
            output.accept(ModItems.item_NetheritePotato.get());
            output.accept(ModItems.item_PotatoShard.get());
            //Blocks
            output.accept(ModBlocks.block_PotatoBlock.get());
            output.accept(ModBlocks.block_PoisonousPotatoBlock.get());
        }).build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
