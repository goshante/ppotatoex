package com.goshante.ppotatoex.item;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.effect.ModEffects;
import com.goshante.ppotatoex.util.etc;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModItems
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PoisonousPotatoExpansion.MOD_ID);

    private static <T extends ItemEx> DeferredItem<T> registerItem(
            String name,
            Supplier<ItemEx.Properties> propsSupplier,
            BiFunction<ItemEx.Properties, String, T> itemFactory)
    {
        DeferredItem<T> item = ITEMS.register(name, () ->
        {
            ItemEx.Properties props = propsSupplier.get();
            return itemFactory.apply(props, name);
        });

        return item;
    }


    public static final DeferredItem<ItemEx> item_GreatPoisonousPotato =
            registerItem("item_gpp",
                    () ->
                    {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(4)
                                .saturationModifier(0.5f)
                                .effect(() -> new MobEffectInstance(MobEffects.POISON, etc.TimeToTicks(15), 3), 1)
                                .build());
                        props.rarity(Rarity.UNCOMMON);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        return item;
                    });

    public static final DeferredItem<ItemEx> item_PotatoShard =
            registerItem("item_ps",
                    () ->
                    {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.durability(3);
                        props.rarity(Rarity.EPIC);
                        props.useDurabilityCrafting(true);
                        props.preventFromEnchantingOnTable(true);
                        props.bookEnchantType(ItemEx.BookEnchantType_t.Whitelist);
                        props.addEnchantmentToWhiteList(Enchantments.UNBREAKING);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        item.setTooltipCount(1);
                        return item;
                    });

    public static final DeferredItem<ItemEx> item_GreatPotato =
            registerItem("item_gp",
                    () -> {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(6)
                                .saturationModifier(0.6f)
                                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, etc.TimeToTicks(5), 0), 1)
                                .build());
                        props.rarity(Rarity.RARE);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        return item;
                    }
            );

    public static final DeferredItem<ItemEx> item_GoldenGreatPotato =
            registerItem("item_ggp",
                    () -> {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(10)
                                .saturationModifier(0.75f)
                                .alwaysEdible()
                                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, etc.TimeToTicks(5), 1), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, etc.TimeToTicks(3.5f), 1), 1)
                                .build());
                        props.removeEffectsOnEat(true);
                        props.rarity(Rarity.RARE);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        return item;
                    }
            );

    public static final DeferredItem<ItemEx> item_NetheritePotato =
            registerItem("item_np",
                    () -> {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(20)
                                .saturationModifier(1.00f)
                                .alwaysEdible()
                                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, etc.TimeToTicks(10), 3), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, etc.TimeToTicks(10), 2), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, etc.TimeToTicks(25), 2), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, etc.TimeToTicks(10), 2), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, etc.TimeToTicks(25), 2), 1)
                                .effect(() -> new MobEffectInstance(MobEffects.WATER_BREATHING, etc.TimeToTicks(25), 0), 1)
                                .build());
                        props.removeEffectsOnEat(true);
                        props.rarity(Rarity.EPIC);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        item.setTooltipCount(1);
                        return item;
                    }
            );

    public static final DeferredItem<ItemEx> item_BottleOfPotatoPoison =
            registerItem("item_bopp",
                    () -> {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(0)
                                .saturationModifier(0.0f)
                                .alwaysEdible()
                                .effect(() -> new MobEffectInstance(MobEffects.POISON, etc.TimeToTicks(15), 3), 1)
                                .build());
                        props.durability(8);
                        props.rarity(Rarity.RARE);
                        props.useDurabilityCrafting(true);
                        props.preventFromEnchantingOnTable(true);
                        props.bookEnchantType(ItemEx.BookEnchantType_t.Disabled);
                        props.replaceAfterConsuming(Items.GLASS_BOTTLE);
                        props.replaceAfterCrafting(Items.GLASS_BOTTLE);
                        props.itemType(ItemEx.Type.Drink);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        item.setTooltipCount(2);
                        return item;
                    }
            );

    public static final DeferredItem<ItemEx> item_PotionOfReturn =
            registerItem("item_rp",
                    () -> {
                        ItemEx.Properties props = new ItemEx.Properties();
                        props.food(new FoodProperties.Builder()
                                .nutrition(0)
                                .saturationModifier(0.0f)
                                .alwaysEdible()
                                .effect(() -> new MobEffectInstance(ModEffects.effect_Return, 0, 0), 1)
                                .build());
                        props.rarity(Rarity.EPIC);
                        props.stacksTo(1);
                        props.replaceAfterConsuming(Items.GLASS_BOTTLE);
                        props.replaceAfterCrafting(Items.GLASS_BOTTLE);
                        props.itemType(ItemEx.Type.Drink);
                        return props;
                    },
                    (props, name) ->
                    {
                        ItemEx item = new ItemEx(props, name);
                        item.setTooltipCount(3);
                        return item;
                    }
            );

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
