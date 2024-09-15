package com.goshante.ppotatoex.block;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.tools.Tool;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PoisonousPotatoExpansion.MOD_ID);

    private static <T extends Block> RegistryObject<BlockItem> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, ()->new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends BlockEx> RegistryObject<T> registerBlock(
            String name,
            Supplier<BlockBehaviour.Properties> propsSupplier,
            BiFunction<BlockBehaviour.Properties, String, T> blockFactory)
    {

        RegistryObject<T> block = BLOCKS.register(name, () ->
        {
            BlockBehaviour.Properties props = propsSupplier.get();
            return blockFactory.apply(props, name);
        });

        registerBlockItem(name, block);
        return block;
    }

    public static final RegistryObject<BlockEx> block_PotatoBlock = registerBlock(
            "potato_block",
            () ->
            {
                return BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS);
            },
            (props, name) ->
            {
                BlockEx block = new BlockEx(props, name);
                return block;
            }
    );

    public static final RegistryObject<BlockEx> block_PoisonousPotatoBlock = registerBlock(
            "ppotato_block",
            () ->
            {
                return BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS);
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
