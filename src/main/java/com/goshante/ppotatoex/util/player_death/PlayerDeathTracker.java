package com.goshante.ppotatoex.util.player_death;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber
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