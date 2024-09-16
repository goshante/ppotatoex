package com.goshante.ppotatoex.effect;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.util.etc;
import com.goshante.ppotatoex.util.player_death.DeathLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Return extends MobEffectEx
{
    public Return(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier, int duration)
    {
        if (!entity.level().isClientSide() && entity instanceof Player)
        {
            DeathLocation loc = PoisonousPotatoExpansion.PlayerDeath.PopDeathLocation((Player)entity);
            if (loc != null)
                loc.Teleport(entity);
            entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, etc.TimeToTicks(10), 4));
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        return false;
    }
}