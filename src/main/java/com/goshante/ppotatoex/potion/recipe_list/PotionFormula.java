package com.goshante.ppotatoex.potion.recipe_list;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;

public class PotionFormula
{
    public Holder<Potion> Input = null;
    public Item Catalyst = null;
    public Holder<Potion> Output = null;
    public Item CustomOutputItem = null;

    PotionFormula(Holder<Potion> Input, Item Catalyst, Holder<Potion> Output)
    {
        this.Input = Input;
        this.Catalyst = Catalyst;
        this.Output = Output;
    }

    PotionFormula(Holder<Potion> Input, Item Catalyst, Holder<Potion> Output, Item CustomOutputItem)
    {
        this.Input = Input;
        this.Catalyst = Catalyst;
        this.Output = Output;
        this.CustomOutputItem = CustomOutputItem;
    }
}
