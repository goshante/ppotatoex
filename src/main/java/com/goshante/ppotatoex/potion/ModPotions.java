package com.goshante.ppotatoex.potion;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.effect.ModEffects;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.util.etc;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber
public class ModPotions
{
    public static final DeferredRegister<Potion> POTIONS =
        DeferredRegister.create(Registries.POTION, PoisonousPotatoExpansion.MOD_ID);

    public static final DeferredHolder<Potion,Potion> potion_ReturnPotion =
            POTIONS.register("potion_return", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Return, 0, 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ReflectionPotion =
            POTIONS.register("potion_re", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Reflection, etc.TimeToTicks(7.5f), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ReflectionPotionLonger =
            POTIONS.register("potion_re_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Reflection, etc.TimeToTicks(15), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_DarknessPotion =
            POTIONS.register("potion_dp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DARKNESS, etc.TimeToTicks(20), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_DarknessPotionLonger =
            POTIONS.register("potion_dp_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DARKNESS, etc.TimeToTicks(60), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_HastePotion =
            POTIONS.register("potion_hap", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(180), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_HastePotionLonger =
            POTIONS.register("potion_hap_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(480), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_HastePotionStronger =
            POTIONS.register("potion_hap_s", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(90), 3);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_NetheritePotatoPotion =
            POTIONS.register("potion_npp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement, etc.TimeToTicks(20), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_NetheritePotatoPotion_Longer =
            POTIONS.register("potion_npp_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement, etc.TimeToTicks(60), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_NetheritePotatoPotion_Stronger =
            POTIONS.register("potion_npp_s", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement, etc.TimeToTicks(12.5f), 3);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongPoisonPotion =
            POTIONS.register("potion_espp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, etc.TimeToTicks(45), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongStrengthPotion =
            POTIONS.register("potion_essp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DAMAGE_BOOST, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongSwiftnessPotion =
            POTIONS.register("potion_esswp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongRegenerationPotion =
            POTIONS.register("potion_esrp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.REGENERATION, etc.TimeToTicks(90), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongHarmingPotion =
            POTIONS.register("potion_eshp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.HARM, 1, 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongHealingPotion =
            POTIONS.register("potion_eshep", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.HEAL, 1, 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final DeferredHolder<Potion,Potion> potion_ExtraStrongLeapingPotion =
            POTIONS.register("potion_eslp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.JUMP, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event)
    {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_NetheritePotato.get(), ModPotions.potion_NetheritePotatoPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_NetheritePotato.get(), ModPotions.potion_NetheritePotatoPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, Items.ECHO_SHARD, ModPotions.potion_DarknessPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_GoldenGreatPotato.get(), ModPotions.potion_ReflectionPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_GreatPotato.get(), ModPotions.potion_HastePotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_GreatPoisonousPotato.get(), Potions.POISON)
                .outputCustomItem(ModItems.item_BottleOfPotatoPoison.get()));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.THICK, ModItems.item_BottleOfPotatoPoison.get(), Potions.POISON));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_NetheritePotatoPotion, Items.REDSTONE, ModPotions.potion_NetheritePotatoPotion_Longer));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_NetheritePotatoPotion, Items.GLOWSTONE_DUST, ModPotions.potion_NetheritePotatoPotion_Stronger));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.POISON, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongPoisonPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.STRENGTH, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongStrengthPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.SWIFTNESS, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongSwiftnessPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.REGENERATION, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongRegenerationPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.HEALING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHealingPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.HARMING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongHarmingPotion));

        builder.addRecipe(new BrewingRecipeEx(
                Potions.LEAPING, ModItems.item_NetheritePotato.get(), ModPotions.potion_ExtraStrongLeapingPotion));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_DarknessPotion, Items.REDSTONE, ModPotions.potion_DarknessPotionLonger));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_HastePotion, Items.REDSTONE, ModPotions.potion_HastePotionLonger));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_HastePotion, Items.GLOWSTONE_DUST, ModPotions.potion_HastePotionStronger));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_ReflectionPotion, Items.REDSTONE, ModPotions.potion_ReflectionPotionLonger));

        builder.addRecipe(new BrewingRecipeEx(
                ModPotions.potion_DarknessPotion, Items.FERMENTED_SPIDER_EYE, ModPotions.potion_ReturnPotion)
                .outputCustomItem(ModItems.item_PotionOfReturn.get()));
    }

    public static void register(IEventBus eventBus)
    {
        POTIONS.register(eventBus);
    }
}
