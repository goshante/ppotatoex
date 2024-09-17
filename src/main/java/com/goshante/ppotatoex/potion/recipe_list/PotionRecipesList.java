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
                Potions.THICK, ModItems.item_NetheritePotato.get(), ModPotions.potion_NetheritePotatoPotion));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_NetheritePotato.get(), ModPotions.potion_NetheritePotatoPotion));

        potions.add(new PotionFormula(
                Potions.THICK, Items.ECHO_SHARD, ModPotions.potion_DarknessPotion));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GoldenGreatPotato.get(), ModPotions.potion_ReflectionPotion));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GreatPotato.get(), ModPotions.potion_HastePotion));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_GreatPoisonousPotato.get(), Potions.POISON, ModItems.item_BottleOfPotatoPoison.get()));

        potions.add(new PotionFormula(
                Potions.THICK, ModItems.item_BottleOfPotatoPoison.get(), Potions.POISON));

        potions.add(new PotionFormula(
                ModPotions.potion_NetheritePotatoPotion, Items.REDSTONE, ModPotions.potion_NetheritePotatoPotion_Longer));

        potions.add(new PotionFormula(
                ModPotions.potion_NetheritePotatoPotion, Items.GLOWSTONE_DUST, ModPotions.potion_NetheritePotatoPotion_Stronger));

        potions.add(new PotionFormula(
                Potions.POISON, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongPoisonPotion));

        potions.add(new PotionFormula(
                Potions.STRENGTH, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongStrengthPotion));

        potions.add(new PotionFormula(
                Potions.SWIFTNESS, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongSwiftnessPotion));

        potions.add(new PotionFormula(
                Potions.REGENERATION, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongRegenerationPotion));

        potions.add(new PotionFormula(
                Potions.HEALING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHealingPotion));

        potions.add(new PotionFormula(
                Potions.HARMING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHarmingPotion));

        potions.add(new PotionFormula(
                Potions.LEAPING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongLeapingPotion));

        potions.add(new PotionFormula(
                ModPotions.potion_DarknessPotion, Items.REDSTONE, ModPotions.potion_DarknessPotionLonger));

        potions.add(new PotionFormula(
                ModPotions.potion_HastePotion, Items.REDSTONE, ModPotions.potion_HastePotionLonger));

        potions.add(new PotionFormula(
                ModPotions.potion_HastePotion, Items.GLOWSTONE_DUST, ModPotions.potion_HastePotionStronger));

        potions.add(new PotionFormula(
                ModPotions.potion_ReflectionPotion, Items.REDSTONE, ModPotions.potion_ReflectionPotionLonger));

        potions.add(new PotionFormula(
                ModPotions.potion_DarknessPotion, Items.FERMENTED_SPIDER_EYE, ModPotions.potion_ReturnPotion, ModItems.item_PotionOfReturn.get()));
    }

    public void EnumerateRecipes(Consumer<PotionFormula> enumerator)
    {
        for (PotionFormula potion : potions)
            enumerator.accept(potion);
    }
}
