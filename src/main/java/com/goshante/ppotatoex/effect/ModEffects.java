package com.goshante.ppotatoex.effect;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.util.entities;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Supplier;


@EventBusSubscriber
public class ModEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(Registries.MOB_EFFECT, PoisonousPotatoExpansion.MOD_ID);

    public static final DeferredHolder<MobEffect, PotatoReinforcement> effect_PotatoReinforcement = MOB_EFFECTS.register("effect_pr", () ->
    {
        return new PotatoReinforcement(MobEffectCategory.BENEFICIAL, 0x515151);
    });

    public static final DeferredHolder<MobEffect, Reflection> effect_Reflection = MOB_EFFECTS.register("effect_reflection", () ->
    {
        return new Reflection(MobEffectCategory.BENEFICIAL, 0x8F10FF);
    });

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event)
    {
        Holder<MobEffect> effect = event.getEffectInstance().getEffect();
        if (effect.value() instanceof PotatoReinforcement)
        {
            PotatoReinforcement pr = (PotatoReinforcement)effect.value();
            pr.onEffectRemoved(event.getEntity(), event.getEffectInstance().getAmplifier(), false);
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event)
    {
        Holder<MobEffect> effect = Objects.requireNonNull(event.getEffectInstance()).getEffect();
        if (effect.value() instanceof MobEffectEx)
        {
            MobEffectEx effectEx = (MobEffectEx)effect.value();
            effectEx.onEffectRemoved(event.getEntity(), event.getEffectInstance().getAmplifier(), true);
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event)
    {
        Holder<MobEffect> effect = Objects.requireNonNull(event.getEffectInstance()).getEffect();
        if (effect.value() instanceof PotatoReinforcement)
        {
            MobEffectEx effectEx = (MobEffectEx)effect.value();
            effectEx.onEffectAdded(event.getEntity(), event.getEffectInstance().getAmplifier(), event.getEffectInstance().getDuration());
        }
    }

    @SubscribeEvent
    public static void onProcessDamage(LivingIncomingDamageEvent event)
    {
        DamageSource source = event.getSource();
        LivingEntity victim = event.getEntity();
        float amount = event.getAmount();

        entities.EnumerateEffects(victim, mobEffectInstance ->
        {
            MobEffect effect = mobEffectInstance.getEffect().value();
            if (effect instanceof MobEffectEx)
                ((MobEffectEx)effect).onTakeDamage(victim, source, amount);
        });

        if (source.getEntity() instanceof LivingEntity)
        {
            LivingEntity livingEntity = (LivingEntity)source.getEntity();
            entities.EnumerateEffects(livingEntity, mobEffectInstance ->
            {
                MobEffect effect = mobEffectInstance.getEffect().value();
                if (effect instanceof MobEffectEx)
                    ((MobEffectEx)effect).onDealDamage(livingEntity, victim, amount, source);
            });
        }
    }

    public static void register(IEventBus eventBus)
    {
        MOB_EFFECTS.register(eventBus);
    }
}
