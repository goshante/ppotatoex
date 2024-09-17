package com.goshante.ppotatoex.effect;

import com.goshante.ppotatoex.damage_type.ModDamageTypes;
import com.goshante.ppotatoex.util.entities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Reflection extends MobEffectEx
{
    public Reflection(MobEffectCategory category, int color)
    {
        super(category, color);
    }

    @Override
    public void onTakeDamage(LivingEntity EffectOwner, DamageSource source, float damage)
    {
        if (source.is(DamageTypes.FALL)
         || source.is(ModDamageTypes.REFLECT)
         || source.is(DamageTypes.CACTUS)
         || source.is(DamageTypes.CRAMMING)
         || source.is(DamageTypes.DROWN)
         || source.is(DamageTypes.FELL_OUT_OF_WORLD)
         || source.is(DamageTypes.FLY_INTO_WALL)
         || source.is(DamageTypes.IN_WALL)
         || source.is(DamageTypes.LAVA)
         || source.is(DamageTypes.FREEZE)
         || source.is(DamageTypes.SWEET_BERRY_BUSH)
         || source.is(DamageTypes.EXPLOSION)
         || source.is(DamageTypes.BAD_RESPAWN_POINT)
         || source.is(DamageTypes.STARVE)
         || source.is(DamageTypes.THORNS)
         || source.is(DamageTypes.GENERIC_KILL))
            return;

        if (!EffectOwner.level().isClientSide())
        {
            EffectOwner.heal(damage);
            if (source.getEntity() == null || source.getEntity().isRemoved() || !source.getEntity().isAlive())
                return;

            if (!(source.getEntity() instanceof LivingEntity))
                return;

            LivingEntity entity = (LivingEntity) source.getEntity();
            entities.DealDamage(EffectOwner, EffectOwner, entity, damage, ModDamageTypes.REFLECT);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier)
    {
        return true;
    }
}
