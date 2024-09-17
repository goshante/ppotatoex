package com.goshante.ppotatoex.client.jei;

import java.util.ArrayList;
import java.util.List;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.ModPotions;
import com.goshante.ppotatoex.potion.recipe_list.PotionRecipesList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiBrewingRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
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
        registration.addRecipes(RecipeTypes.BREWING, buildPotionRecipeList(factory));
    }

    List<IJeiBrewingRecipe> buildPotionRecipeList(IVanillaRecipeFactory factory)
    {
        List<IJeiBrewingRecipe> recipes = new ArrayList<>();
        PotionRecipesList.instance().EnumerateRecipes((recipe)->
        {
            ItemStack output;
            if (recipe.CustomOutputItem == null)
                output = MakePotion(recipe.Output);
            else
                output = new ItemStack(recipe.CustomOutputItem);
            recipes.add(catalyzedBrewingRecipe(factory, recipe.Catalyst, recipe.Input, output));
        });
        return recipes;
    }

    private static ItemStack MakePotion(Potion potion)
    {
        ItemStack stack = new ItemStack(Items.POTION);
        return PotionUtils.setPotion(stack, potion);
    }

    private static IJeiBrewingRecipe catalyzedBrewingRecipe(IVanillaRecipeFactory factory, Item catalyst, Potion inputPotion, ItemStack outputPotion)
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