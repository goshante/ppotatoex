package com.goshante.ppotatoex.client.jei;

import java.util.ArrayList;
import java.util.List;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.ModPotions;
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
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.common.Tags;

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
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_NetheritePotatoPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.ECHO_SHARD,
                        Potions.THICK,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_DarknessPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GoldenGreatPotato.get(),
                        Potions.THICK,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ReflectionPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GreatPotato.get(),
                        Potions.THICK,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_HastePotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_GreatPoisonousPotato.get(),
                        Potions.THICK,
                        new ItemStack(ModItems.item_BottleOfPotatoPoison.get())),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_BottleOfPotatoPoison.get(),
                        Potions.THICK,
                        PotionContents.createItemStack(Items.POTION, Potions.POISON)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_NetheritePotatoPotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_NetheritePotatoPotion_Longer)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.GLOWSTONE_DUST,
                        ModPotions.potion_NetheritePotatoPotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_NetheritePotatoPotion_Stronger)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.POISON,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongPoisonPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.STRENGTH,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongStrengthPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.SWIFTNESS,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongSwiftnessPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.REGENERATION,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongRegenerationPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.HEALING,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongHealingPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.HARMING,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongHarmingPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        ModItems.item_NetheritePotato.get(),
                        Potions.LEAPING,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ExtraStrongLeapingPotion)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_DarknessPotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_DarknessPotionLonger)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_HastePotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_HastePotionLonger)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.GLOWSTONE_DUST,
                        ModPotions.potion_HastePotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_HastePotionStronger)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.REDSTONE,
                        ModPotions.potion_ReflectionPotion,
                        PotionContents.createItemStack(Items.POTION, ModPotions.potion_ReflectionPotionLonger)),
                tagCatalyzedBrewingRecipe(factory,
                        Items.FERMENTED_SPIDER_EYE,
                        ModPotions.potion_DarknessPotion,
                        new ItemStack(ModItems.item_PotionOfReturn.get()))
        ));
    }

    private static IJeiBrewingRecipe tagCatalyzedBrewingRecipe(IVanillaRecipeFactory factory, Item catalyst, Holder<Potion> inputPotion, ItemStack outputPotion)
    {
        List<ItemStack> list = new ArrayList<>();
        list.add(new ItemStack(catalyst));
        return factory.createBrewingRecipe(
                list,
                PotionContents.createItemStack(Items.POTION, inputPotion),
                outputPotion,
                PoisonousPotatoExpansion.GetResourceLoc(outputPotion.getItem().getDescriptionId()));
    }
}