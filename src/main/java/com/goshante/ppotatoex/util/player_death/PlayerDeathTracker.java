package com.goshante.ppotatoex.util.player_death;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class PlayerDeathTracker
{
    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event)
    {
        if (!(event.getEntity() instanceof Player) || event.getEntity().level().isClientSide())
            return;

        PoisonousPotatoExpansion.PlayerDeath.AddDeathLocation((Player)event.getEntity());
    }
}
