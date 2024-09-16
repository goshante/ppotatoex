package com.goshante.ppotatoex.util.player_death;

import com.goshante.ppotatoex.PoisonousPotatoExpansion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;

public class DeathLocation
{
    private final String Dimension;
    private final Vec3 DeathPosition;

    public DeathLocation(Level level, Vec3 pos)
    {
        Dimension = level.dimension().toString();
        DeathPosition = pos;
    }

    public DeathLocation(String dimensionName, Vec3 pos)
    {
        Dimension = dimensionName;
        DeathPosition = pos;
    }

    public ServerLevel GetLevel()
    {
        if (PoisonousPotatoExpansion.Server == null)
            return null;

        for (ServerLevel level : PoisonousPotatoExpansion.Server.getAllLevels())
        {
            ResourceKey<Level> dimension = level.dimension();
            if (dimension.toString().equals(Dimension))
                return level;
        }
        return null;
    }

    public String GetDimensionName()
    {
        return Dimension;
    }

    public Vec3 GetPosition()
    {
        return DeathPosition;
    }

    public void Teleport(Entity entity)
    {
        entity.teleportTo(GetLevel(), DeathPosition.x, DeathPosition.y, DeathPosition.z, Collections.emptySet(), 0, 0);
    }

    public DeathLocation clone()
    {
        return new DeathLocation(Dimension, DeathPosition);
    }
}