package com.goshante.ppotatoex.effect;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.util.entities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;


@Mod.EventBusSubscriber
public class ModEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PoisonousPotatoExpansion.MOD_ID);

    public static final RegistryObject<MobEffect> effect_PotatoReinforcement = MOB_EFFECTS.register("effect_pr", () ->
    {
        return new PotatoReinforcement(MobEffectCategory.BENEFICIAL, 0x515151);
    });

    public static final RegistryObject<MobEffect> effect_Reflection = MOB_EFFECTS.register("effect_reflection", () ->
    {
        return new Reflection(MobEffectCategory.BENEFICIAL, 0x8F10FF);
    });

    public static final RegistryObject<MobEffect> effect_Return = MOB_EFFECTS.register("effect_return", () ->
    {
        return new Return(MobEffectCategory.BENEFICIAL, 0xFF6666);
    });

    @SubscribeEvent
    public static void onEffectRemoved(MobEffectEvent.Remove event)
    {
        MobEffectInstance inst = event.getEffectInstance();
        if (inst == null)
            return;

        MobEffect effect = inst.getEffect();
        if (effect instanceof MobEffectEx effectEx)
        {
            effectEx.onEffectRemoved(event.getEntity(), event.getEffectInstance().getAmplifier(), false);
        }
    }

    @SubscribeEvent
    public static void onEffectExpired(MobEffectEvent.Expired event)
    {
        MobEffectInstance inst = event.getEffectInstance();
        if (inst == null)
            return;

        MobEffect effect = inst.getEffect();
        if (effect instanceof MobEffectEx effectEx)
        {
            effectEx.onEffectRemoved(event.getEntity(), event.getEffectInstance().getAmplifier(), true);
        }
    }

    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event)
    {
        MobEffectInstance inst = event.getEffectInstance();
        if (inst == null)
            return;

        MobEffect effect = inst.getEffect();
        if (effect instanceof MobEffectEx effectEx)
        {
            effectEx.onEffectAdded(event.getEntity(), event.getEffectInstance().getAmplifier(), event.getEffectInstance().getDuration());
        }
    }

    @SubscribeEvent
    public static void onProcessDamage(LivingDamageEvent event)
    {
        DamageSource source = event.getSource();
        LivingEntity victim = event.getEntity();
        float amount = event.getAmount();

        entities.EnumerateEffects(victim, mobEffectInstance ->
        {
            MobEffect effect = mobEffectInstance.getEffect();
            if (effect instanceof MobEffectEx)
                ((MobEffectEx)effect).onTakeDamage(victim, source, amount);
        });

        if (source.getEntity() instanceof LivingEntity livingEntity)
        {
            entities.EnumerateEffects(livingEntity, mobEffectInstance ->
            {
                MobEffect effect = mobEffectInstance.getEffect();
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
