package com.goshante.ppotatoex.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.maven.artifact.versioning.ArtifactVersion;

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

    public static String GetModVersion(String modid)
    {
        if (!ModList.get().getModContainerById(modid).map(ModContainer::getModInfo).map(IModInfo::getVersion).isPresent())
            return "[UNKNOWN]";

        if (!ModList.get().getModContainerById(modid).map(ModContainer::getModInfo).map(IModInfo::getVersion).map(ArtifactVersion::toString).isPresent())
            ModList.get().getModContainerById(modid).map(ModContainer::getModInfo).map(IModInfo::getVersion).get().toString();

        return ModList.get().getModContainerById(modid).map(ModContainer::getModInfo).map(IModInfo::getVersion).map(ArtifactVersion::toString).get().toString();
    }
}
