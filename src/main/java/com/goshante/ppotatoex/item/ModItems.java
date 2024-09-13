package com.goshante.ppotatoex.item;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, PoisonousPotatoExpansion.MOD_ID);

    public static final RegistryObject<Item> item_GreatPoisonousPotato =
            ITEMS.register("item_gpp", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_GoldenGreatPotato =
            ITEMS.register("item_ggp", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_NetheritePotato =
            ITEMS.register("item_np", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_NetheritePotatoPotion =
            ITEMS.register("item_npp", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_BottleOfPotatoPoison =
            ITEMS.register("item_bopp", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_PotionOfStrengthV =
            ITEMS.register("item_posv", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> item_ExplosivePotionOfStrengthV =
            ITEMS.register("item_eposv", ()->new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
