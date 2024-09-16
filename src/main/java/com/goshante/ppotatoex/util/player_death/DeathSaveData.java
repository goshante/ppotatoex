package com.goshante.ppotatoex.util.player_death;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class DeathSaveData extends SavedData
{
    private final Map<String, DeathLocation> DeathMap = new HashMap<>();

    public static DeathSaveData load(CompoundTag tag)
    {
        DeathSaveData data = new DeathSaveData();
        ListTag deathsList = tag.getList("playerDeathTable", Tag.TAG_COMPOUND);

        for (Tag entry : deathsList)
        {
            CompoundTag deathTag = (CompoundTag) entry;
            String playerName = deathTag.getString("playerName");
            double x = deathTag.getDouble("x");
            double y = deathTag.getDouble("y");
            double z = deathTag.getDouble("z");
            String dim = deathTag.getString("dim");

            DeathLocation location = new DeathLocation(dim, new Vec3(x, y, z));
            data.DeathMap.put(playerName, location);
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag)
    {
        ListTag deathsList = new ListTag();

        for (Map.Entry<String, DeathLocation> entry : DeathMap.entrySet())
        {
            CompoundTag deathTag = new CompoundTag();
            deathTag.putString("playerName", entry.getKey());

            DeathLocation location = entry.getValue();
            deathTag.putDouble("x", location.GetPosition().x());
            deathTag.putDouble("y", location.GetPosition().y());
            deathTag.putDouble("z", location.GetPosition().z());
            deathTag.putString("dim", location.GetDimensionName());
            deathsList.add(deathTag);
        }

        tag.put("playerDeathTable", deathsList);
        return tag;
    }

    public void AddDeathLocation(Player entity)
    {
        DeathLocation deathLoc = new DeathLocation(entity.level(), entity.position());
        DeathMap.put(entity.getScoreboardName(), deathLoc);
        setDirty();
    }

    public DeathLocation GetDeathLocation(Player entity)
    {
        if (!DeathMap.containsKey(entity.getScoreboardName()))
            return null;

        return DeathMap.get(entity.getScoreboardName());
    }

    public DeathLocation PopDeathLocation(Player entity)
    {
        if (!DeathMap.containsKey(entity.getScoreboardName()))
            return null;

        DeathLocation loc = DeathMap.get(entity.getScoreboardName()).clone();
        DeathMap.remove(entity.getScoreboardName());
        return loc;
    }
}