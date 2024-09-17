package com.goshante.ppotatoex.potion.recipe_list;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

public class PotionFormula
{
    public Potion Input = null;
    public Item Catalyst = null;
    public Potion Output = null;
    public Item CustomOutputItem = null;

    PotionFormula(Potion Input, Item Catalyst, Potion Output)
    {
        this.Input = Input;
        this.Catalyst = Catalyst;
        this.Output = Output;
    }

    PotionFormula(Potion Input, Item Catalyst, Potion Output, Item CustomOutputItem)
    {
        this.Input = Input;
        this.Catalyst = Catalyst;
        this.Output = Output;
        this.CustomOutputItem = CustomOutputItem;
    }
}