package com.goshante.ppotatoex.block;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PoisonousPotatoExpansion.MOD_ID);

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends BlockEx> DeferredBlock<T> registerBlock(
            String name,
            Supplier<BlockBehaviour.Properties> propsSupplier,
            BiFunction<BlockBehaviour.Properties, String, T> blockFactory)
    {

        DeferredBlock<T> block = BLOCKS.register(name, () ->
        {
            BlockBehaviour.Properties props = propsSupplier.get();
            return blockFactory.apply(props, name);
        });

        registerBlockItem(name, block);
        return block;
    }

    public static final DeferredBlock<BlockEx> block_PotatoBlock = registerBlock(
            "potato_block",
            () ->
            {
                return BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD);
            },
            (props, name) ->
            {
                BlockEx block = new BlockEx(props, name);
                return block;
            }
    );

    public static final DeferredBlock<BlockEx> block_PoisonousPotatoBlock = registerBlock(
            "ppotato_block",
            () ->
            {
                return BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD);
            },
            (props, name) ->
            {
                BlockEx block = new BlockEx(props, name);
                return block;
            }
    );


    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
