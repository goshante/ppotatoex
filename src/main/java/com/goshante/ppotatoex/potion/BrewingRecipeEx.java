package com.goshante.ppotatoex.potion;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BrewingRecipeEx implements IBrewingRecipe
{
    private final Potion input;
    private final Item ingredient;
    private Potion output;
    private Item outputItem = null; //If not null - brewing result will be this item instead of potion

    public BrewingRecipeEx(Potion input, Item ingredient, Potion output)
    {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    public BrewingRecipeEx outputCustomItem(Item item)
    {
        this.output = Potions.EMPTY;
        this.outputItem = item;
        return this;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        return PotionUtils.getPotion(input) == this.input;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.setTag(new CompoundTag());
        PotionUtils.setPotion(itemStack, this.output);
        if (outputItem != null)
            return new ItemStack(outputItem);
        return itemStack;
    }
}
