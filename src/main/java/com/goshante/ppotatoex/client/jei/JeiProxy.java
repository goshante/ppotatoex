package com.goshante.ppotatoex.client.jei;

import java.util.ArrayList;
import java.util.List;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.ModPotions;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

@JeiPlugin
public class JeiProxy implements IModPlugin
{
    public static final ResourceLocation ID = PoisonousPotatoExpansion.GetResourceLoc(PoisonousPotatoExpansion.MOD_ID);

    @Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
        registration.addRecipes(RecipeTypes.BREWING, List.of(
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.THICK,
                        MakePotion(ModPotions.potion_NetheritePotatoPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.ECHO_SHARD,
                        Potions.THICK,
                        MakePotion(ModPotions.potion_DarknessPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GoldenGreatPotato.get(),
                        Potions.THICK,
                        MakePotion(ModPotions.potion_ReflectionPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GreatPotato.get(),
                        Potions.THICK,
                        MakePotion(ModPotions.potion_HastePotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GreatPoisonousPotato.get(),
                        Potions.THICK,
                        new ItemStack(ModItems.item_BottleOfPotatoPoison.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_BottleOfPotatoPoison.get(),
                        Potions.THICK,
                        MakePotion(Potions.POISON)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_NetheritePotatoPotion.get(),
                        MakePotion(ModPotions.potion_NetheritePotatoPotion_Longer.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.GLOWSTONE_DUST,
                        ModPotions.potion_NetheritePotatoPotion.get(),
                        MakePotion(ModPotions.potion_NetheritePotatoPotion_Stronger.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.POISON,
                        MakePotion(ModPotions.potion_ExtraStrongPoisonPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.STRENGTH,
                        MakePotion(ModPotions.potion_ExtraStrongStrengthPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.SWIFTNESS,
                        MakePotion(ModPotions.potion_ExtraStrongSwiftnessPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.REGENERATION,
                        MakePotion(ModPotions.potion_ExtraStrongRegenerationPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.HEALING,
                        MakePotion(ModPotions.potion_ExtraStrongHealingPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.HARMING,
                        MakePotion(ModPotions.potion_ExtraStrongHarmingPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.LEAPING,
                        MakePotion(ModPotions.potion_ExtraStrongLeapingPotion.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_DarknessPotion.get(),
                        MakePotion(ModPotions.potion_DarknessPotionLonger.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_HastePotion.get(),
                        MakePotion(ModPotions.potion_HastePotionLonger.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.GLOWSTONE_DUST,
                        ModPotions.potion_HastePotion.get(),
                        MakePotion(ModPotions.potion_HastePotionStronger.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_ReflectionPotion.get(),
                        MakePotion(ModPotions.potion_ReflectionPotionLonger.get())),
                tagCatalyzedBrewingRecipe(factory,
                        Items.FERMENTED_SPIDER_EYE,
                        ModPotions.potion_DarknessPotion.get(),
                        new ItemStack(ModItems.item_PotionOfReturn.get()))
        ));
    }

    private static ItemStack MakePotion(Potion potion)
    {
        ItemStack stack = new ItemStack(Items.POTION);
        return PotionUtils.setPotion(stack, potion);
    }

    private static IJeiBrewingRecipe tagCatalyzedBrewingRecipe(IVanillaRecipeFactory factory, Item catalyst, Potion inputPotion, ItemStack outputPotion)
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(catalyst));
        return factory.createBrewingRecipe(
                list,
                MakePotion(inputPotion),
                outputPotion,
                PoisonousPotatoExpansion.GetResourceLoc(outputPotion.getItem().getDescriptionId()));
    }
}