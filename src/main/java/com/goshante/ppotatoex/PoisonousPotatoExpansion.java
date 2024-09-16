package com.goshante.ppotatoex;

import com.goshante.ppotatoex.block.ModBlocks;
import com.goshante.ppotatoex.effect.ModEffects;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.ModPotions;
import com.goshante.ppotatoex.util.player_death.DeathSaveData;
import com.mojang.logging.LogUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.goshante.ppotatoex.util.etc;
import org.slf4j.Logger;

@Mod(PoisonousPotatoExpansion.MOD_ID)
public class PoisonousPotatoExpansion
{

    public static final String MOD_ID = "ppotatoex";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static MinecraftServer Server = null;
    public static DeathSaveData PlayerDeath = null;

    public PoisonousPotatoExpansion()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        Server = event.getServer();

        if (PlayerDeath == null)
        {
            ServerLevel overworld = Server.overworld();
            DimensionDataStorage storage = overworld.getDataStorage();

            PlayerDeath = storage.computeIfAbsent(
                    DeathSaveData::load,
                    DeathSaveData::new,
                    "playerDeathTable"
            );
        }

        LOGGER.info("PoisonousPotatoExpansion mod has loaded, version v" + etc.GetModVersion(MOD_ID));
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ModPotions.registerCustomPotionRecipes();
        }
    }
}
