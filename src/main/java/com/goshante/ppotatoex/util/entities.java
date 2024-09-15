package com.goshante.ppotatoex.util;

import com.goshante.ppotatoex.damage_type.ModDamageTypes;
import net.minecraft.client.OptionInstance;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import java.util.ArrayList;
import java.util.Collection;

public class entities
{
    public enum MobEffectCategory
    {
        All,
        Positive,
        Negative,
        Neutral,
        PosAndNeg,
        PosAndNeu,
        NegAndNeu
    }

    public static void DealDamage(Entity inflictor, Entity directEntity, LivingEntity victim, float damage, ResourceKey<DamageType> DamageType)
    {
        DamageSource source = inflictor.level().damageSources().source(DamageType, inflictor, directEntity);
        victim.hurt(source, damage);
    }

    public static void EnumerateEffects(LivingEntity entity, Consumer<MobEffectInstance> enumerator)
    {
        Collection<MobEffectInstance> effects = new ArrayList<>(entity.getActiveEffects()); //Create a copy
        for (MobEffectInstance effectInstance : effects)
            enumerator.accept(effectInstance);
    }

    public static void RemoveEffects(LivingEntity entity, MobEffectCategory category, MobEffect exception)
    {
        ArrayList<MobEffect> exceptions = new ArrayList<MobEffect>();
        exceptions.add(exception);
        RemoveEffects(entity, category, exceptions);
    }

    public static void RemoveEffects(LivingEntity entity, MobEffectCategory category)
    {
        RemoveEffects(entity, category, new ArrayList<MobEffect>());
    }

    public static void RemoveEffects(LivingEntity entity, MobEffectCategory category, ArrayList<MobEffect> exceptions)
    {
        Collection<MobEffectInstance> effects = new ArrayList<>(entity.getActiveEffects()); //Create a copy
        for (MobEffectInstance effectInstance : effects)
        {
            Holder<MobEffect> effect = effectInstance.getEffect();
            if (category == MobEffectCategory.All)
            {
                if (!exceptions.contains(effect.value()))
                    entity.removeEffect(effect);
                continue;
            }

            if (effect.value().getCategory() == net.minecraft.world.effect.MobEffectCategory.BENEFICIAL
            && (category == MobEffectCategory.Positive
             || category == MobEffectCategory.PosAndNeg
             || category == MobEffectCategory.PosAndNeu))
            {
                if (!exceptions.contains(effect.value()))
                    entity.removeEffect(effect);
            }
            else if (effect.value().getCategory() == net.minecraft.world.effect.MobEffectCategory.HARMFUL
                    && (category == MobEffectCategory.Negative
                    || category == MobEffectCategory.PosAndNeg
                    || category == MobEffectCategory.NegAndNeu))
            {
                if (!exceptions.contains(effect.value()))
                    entity.removeEffect(effect);
            }
            else if (effect.value().getCategory() == net.minecraft.world.effect.MobEffectCategory.NEUTRAL
                    && (category == MobEffectCategory.Neutral
                    || category == MobEffectCategory.PosAndNeu
                    || category == MobEffectCategory.NegAndNeu))
            {
                if (!exceptions.contains(effect.value()))
                    entity.removeEffect(effect);
            }
        }
    }
}
