package com.goshante.ppotatoex.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MobEffectEx extends MobEffect
{
    public MobEffectEx(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    public void onEffectAdded(LivingEntity entity, int amplifier, int duration)
    {
    }

    public void onEffectRemoved(LivingEntity entity, int amplifier, boolean removedByExpiration)
    {
    }

    public void onTakeDamage(LivingEntity EffectOwner, DamageSource source, float damage)
    {
    }

    public void onDealDamage(LivingEntity EffectOwner, LivingEntity victim, float damage, DamageSource source)
    {
    }
}
