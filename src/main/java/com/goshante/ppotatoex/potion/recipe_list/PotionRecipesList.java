package com.goshante.ppotatoex.potion.recipe_list;

import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PotionRecipesList
{
    private List<PotionFormula> potions = new ArrayList<>();
    private final static PotionRecipesList Instance = new PotionRecipesList();
    public static PotionRecipesList instance()
    {
        return Instance;
    }

    PotionRecipesList()
    {
        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_NetheritePotato.get(), ModPotions.potion_NetheritePotatoPotion.get()));

        potions.add(new PotionFormula(
                Potions.THICK, Items.ECHO_SHARD, ModPotions.potion_DarknessPotion.get()));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GoldenGreatPotato.get(), ModPotions.potion_ReflectionPotion.get()));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GreatPotato.get(), ModPotions.potion_HastePotion.get()));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GreatPoisonousPotato.get(), Potions.POISON, ModItems.item_BottleOfPotatoPoison.get()));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_BottleOfPotatoPoison.get(), Potions.POISON));

        potions.add(new PotionFormula(
                ModPotions.potion_NetheritePotatoPotion.get(), Items.REDSTONE, ModPotions.potion_NetheritePotatoPotion_Longer.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_NetheritePotatoPotion.get(), Items.GLOWSTONE_DUST, ModPotions.potion_NetheritePotatoPotion_Stronger.get()));

        potions.add(new PotionFormula(
                Potions.POISON, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongPoisonPotion.get()));

        potions.add(new PotionFormula(
                Potions.STRENGTH, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongStrengthPotion.get()));

        potions.add(new PotionFormula(
                Potions.SWIFTNESS, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongSwiftnessPotion.get()));

        potions.add(new PotionFormula(
                Potions.REGENERATION, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongRegenerationPotion.get()));

        potions.add(new PotionFormula(
                Potions.HEALING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHealingPotion.get()));

        potions.add(new PotionFormula(
                Potions.HARMING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHarmingPotion.get()));

        potions.add(new PotionFormula(
                Potions.LEAPING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongLeapingPotion.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_DarknessPotion.get(), Items.REDSTONE, ModPotions.potion_DarknessPotionLonger.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_HastePotion.get(), Items.REDSTONE, ModPotions.potion_HastePotionLonger.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_HastePotion.get(), Items.GLOWSTONE_DUST, ModPotions.potion_HastePotionStronger.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_HastePotion.get(), ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHastePotion.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_ReflectionPotion.get(), Items.REDSTONE, ModPotions.potion_ReflectionPotionLonger.get()));

        potions.add(new PotionFormula(
                ModPotions.potion_DarknessPotion.get(), Items.FERMENTED_SPIDER_EYE, ModPotions.potion_ReturnPotion.get(), ModItems.item_PotionOfReturn.get()));
    }

    public void EnumerateRecipes(Consumer<PotionFormula> enumerator)
    {
        for (PotionFormula potion : potions)
            enumerator.accept(potion);
    }
}