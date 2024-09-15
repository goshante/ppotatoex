package com.goshante.ppotatoex.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

public final class etc
{
    public static int DefaultTPS = 20;

    public static float TicksToTime(int ticks)
    {
        float tpsTime = 1.0f / DefaultTPS;
        return ticks * tpsTime;
    }

    public static int TimeToTicks(float time)
    {
        float tpsTime = 1.0f / DefaultTPS;
        return Math.round(time / tpsTime);
    }
}
