package com.goshante.ppotatoex.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.common.brewing.IBrewingRecipe;
import org.jetbrains.annotations.NotNull;

public class BrewingRecipeEx implements IBrewingRecipe
{
    private final Holder<Potion> input;
    private final Item ingredient;
    private Holder<Potion> output;
    private Item outputItem = null; //If not null - brewing result will be this item instead of potion

    public BrewingRecipeEx(Holder<Potion> input, Item ingredient, Holder<Potion> output)
    {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    public BrewingRecipeEx outputCustomItem(Item item)
    {
        this.output = Potions.WATER;
        this.outputItem = item;
        return this;
    }

    @Override
    public boolean isInput(ItemStack input)
    {
        PotionContents pc = input.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
        if (pc != null && pc.potion().isPresent())
        {
            return pc.potion().get().value() == this.input.value();
        }
        return false;
    }

    @Override
    public boolean isIngredient(ItemStack ingredient)
    {
        return ingredient.getItem() == this.ingredient;
    }

    @Override
    public @NotNull ItemStack getOutput(ItemStack input, ItemStack ingredient)
    {
        if(!this.isInput(input) || !this.isIngredient(ingredient))
        {
            return ItemStack.EMPTY;
        }

        ItemStack itemStack = new ItemStack(input.getItem());
        itemStack.set(DataComponents.POTION_CONTENTS, new PotionContents(output));
        if (outputItem != null)
            return new ItemStack(outputItem);
        return itemStack;
    }
}
