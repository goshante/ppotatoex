package com.goshante.ppotatoex.potion;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import com.goshante.ppotatoex.effect.ModEffects;
import com.goshante.ppotatoex.item.ModItems;
import com.goshante.ppotatoex.potion.recipe_list.PotionRecipesList;
import com.goshante.ppotatoex.util.etc;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class ModPotions
{
    public static final DeferredRegister<Potion> POTIONS =
        DeferredRegister.create(ForgeRegistries.POTIONS, PoisonousPotatoExpansion.MOD_ID);

    public static final RegistryObject<Potion> potion_ReflectionPotion =
            POTIONS.register("potion_re", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Reflection.get(), etc.TimeToTicks(7.5f), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ReflectionPotionLonger =
            POTIONS.register("potion_re_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Reflection.get(), etc.TimeToTicks(15), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_DarknessPotion =
            POTIONS.register("potion_dp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DARKNESS, etc.TimeToTicks(20), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_DarknessPotionLonger =
            POTIONS.register("potion_dp_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DARKNESS, etc.TimeToTicks(60), 0);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_HastePotion =
            POTIONS.register("potion_hap", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(180), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_HastePotionLonger =
            POTIONS.register("potion_hap_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(480), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_HastePotionStronger =
            POTIONS.register("potion_hap_s", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DIG_SPEED, etc.TimeToTicks(90), 3);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_NetheritePotatoPotion =
            POTIONS.register("potion_npp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement.get(), etc.TimeToTicks(20), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_NetheritePotatoPotion_Longer =
            POTIONS.register("potion_npp_l", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement.get(), etc.TimeToTicks(60), 2);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_NetheritePotatoPotion_Stronger =
            POTIONS.register("potion_npp_s", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_PotatoReinforcement.get(), etc.TimeToTicks(12.5f), 3);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongPoisonPotion =
            POTIONS.register("potion_espp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.POISON, etc.TimeToTicks(45), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongStrengthPotion =
            POTIONS.register("potion_essp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.DAMAGE_BOOST, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongSwiftnessPotion =
            POTIONS.register("potion_esswp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.MOVEMENT_SPEED, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongRegenerationPotion =
            POTIONS.register("potion_esrp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.REGENERATION, etc.TimeToTicks(90), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongHarmingPotion =
            POTIONS.register("potion_eshp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.HARM, 1, 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongHealingPotion =
            POTIONS.register("potion_eshep", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.HEAL, 1, 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ExtraStrongLeapingPotion =
            POTIONS.register("potion_eslp", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(MobEffects.JUMP, etc.TimeToTicks(180), 4);
                Potion potion = new Potion(effect);
                return potion;
            });

    public static final RegistryObject<Potion> potion_ReturnPotion =
            POTIONS.register("potion_return", () ->
            {
                MobEffectInstance effect = new MobEffectInstance(ModEffects.effect_Return.get(), 0, 1);
                Potion potion = new Potion(effect);
                return potion;
            });


    public static void registerCustomPotionRecipes()
    {
        PotionRecipesList.instance().EnumerateRecipes((formula)->
        {
            BrewingRecipeEx recipe = new BrewingRecipeEx(
                formula.Input, formula.Catalyst, formula.Output);
            if (formula.CustomOutputItem != null)
                recipe.outputCustomItem(formula.CustomOutputItem);
            BrewingRecipeRegistry.addRecipe(recipe);
        });
    }

    public static void register(IEventBus eventBus)
    {
        POTIONS.register(eventBus);
    }
}
