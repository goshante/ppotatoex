package com.goshante.ppotatoex.block;

import com.goshante.ppotatoex.util.log;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import java.util.List;

public class BlockEx extends Block
{
    private boolean UseTooltip = false;
    private final String Name;

    public BlockEx(Properties props, String name)
    {
        super(props);
        Name = name;
    }

    public BlockEx setTooltip()
    {
        this.UseTooltip = true;
        return this;
    }

    public String GetBlockNameID()
    {
        return Name;
    }

    @Override
    public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag)
    {
        if (UseTooltip)
        {
            pTooltip.add(Component.translatable("tooltip.ppotatoex." + Name + ".tooltip"));
        }
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
