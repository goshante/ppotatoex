package com.goshante.ppotatoex.block;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PoisonousPotatoExpansion.MOD_ID);

    public static final RegistryObject<Block> block_PotatoBlock =
            registerBlock("potato_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));
    public static final RegistryObject<Block> block_PoisonousPotatoBlock =
            registerBlock("ppotato_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result);
        return result;
    }

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, ()->new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
