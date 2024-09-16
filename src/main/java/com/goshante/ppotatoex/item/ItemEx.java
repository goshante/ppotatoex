package com.goshante.ppotatoex.item;

import com.goshante.ppotatoex.util.log;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemEx extends Item
{
    public enum Type
    {
        Resource,
        Food,
        Drink,
        Tool
    }

    public enum BookEnchantType_t
    {
        ByDefault,
        AllowAll,
        Whitelist,
        Disabled
    }

    public static class Properties extends Item.Properties
    {
        boolean UseDurabilityCrafting = false;
        boolean RemoveEffectsOnEat = false;
        boolean PreventFromEnchantingOnTable = false;
        Type ItemType = Type.Resource;
        Item CraftingReplacer = null;
        Item ConsumingReplacer = null;
        BookEnchantType_t BookEnchantType = BookEnchantType_t.ByDefault;
        ArrayList<Enchantment> EnchantmentWhiteList;

        public Properties()
        {
            EnchantmentWhiteList = new ArrayList<>();
        }

        @Override
        public ItemEx.Properties food(FoodProperties pFood)
        {
            super.food(pFood);
            ItemType = Type.Food;
            return this;
        }

        public ItemEx.Properties addEnchantmentToWhiteList(Enchantment enchantment)
        {
            EnchantmentWhiteList.add(enchantment);
            return this;
        }

        public ItemEx.Properties bookEnchantType(BookEnchantType_t type)
        {
            BookEnchantType = type;
            return this;
        }

        public ItemEx.Properties preventFromEnchantingOnTable(boolean prevent)
        {
            PreventFromEnchantingOnTable = prevent;
            return this;
        }

        public ItemEx.Properties itemType(Type type)
        {
            ItemType = type;
            return this;
        }

        public ItemEx.Properties useDurabilityCrafting(boolean use)
        {
            UseDurabilityCrafting = use;
            return this;
        }

        public ItemEx.Properties removeEffectsOnEat(boolean remove)
        {
            RemoveEffectsOnEat = remove;
            return this;
        }

        public ItemEx.Properties replaceAfterCrafting(Item item)
        {
            CraftingReplacer = item;
            return this;
        }

        public ItemEx.Properties replaceAfterConsuming(Item item)
        {
            ConsumingReplacer = item;
            return this;
        }
    }

    private final String Name;
    private final RandomSource random = RandomSource.create();
    private final boolean UseDurabilityCrafting;
    private final boolean RemoveEffectsOnEat;
    private final boolean PreventFromEnchantingOnTable;
    private final BookEnchantType_t BookEnchantType;
    private final ArrayList<Enchantment> EnchantmentWhiteList;

    private boolean UseTooltip = false;
    private Type ItemType = Type.Resource;
    private Item CraftingReplacer = null;
    private Item ConsumingReplacer = null;

    public ItemEx(ItemEx.Properties props, String name)
    {
        super(props);
        Name = name;
        this.UseDurabilityCrafting = props.UseDurabilityCrafting;
        this.RemoveEffectsOnEat = props.RemoveEffectsOnEat;
        this.CraftingReplacer = props.CraftingReplacer;
        this.ConsumingReplacer = props.ConsumingReplacer;
        this.ItemType = props.ItemType;
        this.PreventFromEnchantingOnTable = props.PreventFromEnchantingOnTable;
        this.BookEnchantType = props.BookEnchantType;
        this.EnchantmentWhiteList = new ArrayList<Enchantment>(props.EnchantmentWhiteList);
    }

    public String GetItemNameID()
    {
        return Name;
    }

    public ItemEx setTooltip()
    {
        this.UseTooltip = true;
        return this;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving)
    {
        if (!pLevel.isClientSide && RemoveEffectsOnEat)
            pEntityLiving.removeAllEffects();

        ItemStack items = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (ConsumingReplacer != null)
            return new ItemStack(ConsumingReplacer);
        else
            return items;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack)
    {
        if (!UseDurabilityCrafting)
        {
            if (CraftingReplacer == null)
                return super.getCraftingRemainingItem(itemStack);

            if (itemStack.getMaxStackSize() == 1 && CraftingReplacer != null)
                return new ItemStack(CraftingReplacer);
            else
            {
                log.write(log.level.Warning, "This should never happen, but it happened. Durability crafting is not applicable to stacked items.");
                return super.getCraftingRemainingItem(itemStack);
            }
        }

        ItemStack damagedStack = itemStack.copy();
        boolean broken = damagedStack.hurt(1, random, null);
        if (broken)
            damagedStack = ItemStack.EMPTY;

        if (broken && CraftingReplacer != null)
            damagedStack = new ItemStack(CraftingReplacer);

        return damagedStack;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack)
    {
        if (!UseDurabilityCrafting)
            return super.hasCraftingRemainingItem(stack);

        return true;
    }

    @Override
    public int getUseDuration(ItemStack pStack)
    {
        if (ItemType == Type.Drink)
            return 32;

        return super.getUseDuration(pStack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack)
    {
        switch(ItemType)
        {
            case Resource:
                return UseAnim.NONE;
            case Food:
                return UseAnim.EAT;
            case Drink:
                return UseAnim.DRINK;
            case Tool:
                return UseAnim.NONE;
        }

        return super.getUseAnimation(pStack);
    }

    @Override
    public boolean isEnchantable(ItemStack pStack)
    {
        if (PreventFromEnchantingOnTable)
            return false;
        return super.isEnchantable(pStack);
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book)
    {
        switch(BookEnchantType)
        {
            case ByDefault:
                return super.isBookEnchantable(stack, book);
            case AllowAll:
                return true;
            case Disabled:
                return false;
        }

        Map<Enchantment, Integer> enchMap = EnchantmentHelper.getEnchantments(book);
        boolean whitelistFail = false;
        for (Map.Entry<Enchantment, Integer> entry : enchMap.entrySet())
        {
            Enchantment enchantment = entry.getKey();
            if (!EnchantmentWhiteList.contains(enchantment))
            {
                whitelistFail = true;
                break;
            }
        }
        return !whitelistFail;
    }


    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        if (UseTooltip)
        {
            pTooltipComponents.add(Component.translatable("tooltip.ppotatoex." + Name + ".tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
