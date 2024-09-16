package com.goshante.ppotatoex.effect;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.util.entities;
import com.goshante.ppotatoex.util.player_death.DeathLocation;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PotatoReinforcement extends MobEffectEx
{
    public PotatoReinforcement(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public void onEffectAdded(LivingEntity entity, int amplifier, int duration)
    {
        if (!entity.level().isClientSide())
        {
            entities.RemoveEffects(entity, entities.MobEffectCategory.NegAndNeu, this);
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amplifier));
            entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, duration, amplifier));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, amplifier));
            entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration * 3, 2));
            entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, duration * 3, 0));
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration * 3, 0));
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier)
    {
        float healBase = 0.1f;
        if (!entity.level().isClientSide())
        {
            entity.heal(healBase * (1 + amplifier));
            entity.resetFallDistance();
            entities.RemoveEffects(entity, entities.MobEffectCategory.NegAndNeu);
        }
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int pDuration, int pAmplifier)
    {
        return true;
    }
}
