package com.goshante.ppotatoex.item;

import com.goshante.ppotatoex.util.log;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        ArrayList<ResourceKey<Enchantment>> EnchantmentWhiteList;

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

        public ItemEx.Properties addEnchantmentToWhiteList(ResourceKey<Enchantment> enchantment)
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
    private final ArrayList<ResourceKey<Enchantment>> EnchantmentWhiteList;

    private int TooltipCount = 0;
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
        this.EnchantmentWhiteList = new ArrayList<ResourceKey<Enchantment>>(props.EnchantmentWhiteList);
    }

    public String GetItemNameID()
    {
        return Name;
    }

    public ItemEx setTooltipCount(int count)
    {
        this.TooltipCount = count;
        return this;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving)
    {
        if (pEntityLiving instanceof ServerPlayer serverplayer)
        {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!pLevel.isClientSide && RemoveEffectsOnEat)
            pEntityLiving.removeAllEffects();

        ItemStack items = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        if (ConsumingReplacer != null && pEntityLiving instanceof Player player && !player.isCreative())
            return new ItemStack(ConsumingReplacer);
        else
            return items;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if (ItemType == Type.Drink)
            return ItemUtils.startUsingInstantly(level, player, hand);
        return super.use(level, player, hand);
    }

    @Override
    public SoundEvent getEatingSound()
    {
        if (ItemType == Type.Drink)
            return SoundEvents.GENERIC_DRINK;
        else
            return super.getEatingSound();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack)
    {
        if (!UseDurabilityCrafting)
        {
            if (CraftingReplacer == null)
                return super.getCraftingRemainingItem(itemStack);

            if (itemStack.getMaxStackSize() == 1)
                return new ItemStack(CraftingReplacer);
            else
            {
                return super.getCraftingRemainingItem(itemStack);
            }
        }

        //Imlpementation of Unbreaking enchantment for crafting
        int currentDurabilityDamage = 1;
        ItemEnchantments enchantments = itemStack.getComponents().get(DataComponents.ENCHANTMENTS);
        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchEntries = enchantments.entrySet();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchEntries)
        {
            Holder<Enchantment> enchantmentHolder = entry.getKey();
            if (enchantmentHolder.getKey() == Enchantments.UNBREAKING)
            {
                int randomNumber = random.nextInt(100) + 1;
                int enchLevel = entry.getIntValue();
                if (randomNumber <= (50 + (13 * (enchLevel - 1))))
                    currentDurabilityDamage = 0;
                break;
            }
        }

        ItemStack damagedStack = itemStack.copy();
        damagedStack.setDamageValue(damagedStack.getDamageValue() + currentDurabilityDamage);
        boolean broken = damagedStack.getDamageValue() >= damagedStack.getMaxDamage();

        if (broken)
            damagedStack = ItemStack.EMPTY;

        if (broken && CraftingReplacer != null)
            damagedStack = new ItemStack(CraftingReplacer);

        return damagedStack;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack)
    {
        if (CraftingReplacer == null)
            return super.hasCraftingRemainingItem(stack);

        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity)
    {
        if (ItemType == Type.Drink)
            return 32;

        return super.getUseDuration(stack, entity);
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

        ItemEnchantments enchantments = book.getComponents().get(DataComponents.STORED_ENCHANTMENTS);
        if (enchantments == null || enchantments.entrySet().isEmpty())
            return false;

        Set<Object2IntMap.Entry<Holder<Enchantment>>> enchEntries = enchantments.entrySet();
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchEntries)
        {
            Holder<Enchantment> enchantmentHolder = entry.getKey();
            if (!EnchantmentWhiteList.contains(enchantmentHolder.getKey()))
                return false;
        }

        return true;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        if (TooltipCount > 0)
        {
            for (int i = 0; i < TooltipCount; i++)
            {
                tooltipComponents.add(Component.translatable("tooltip.ppotatoex." + Name + ".tooltip." + String.valueOf(i + 1)));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
