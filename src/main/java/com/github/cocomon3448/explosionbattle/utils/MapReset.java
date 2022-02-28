package com.github.cocomon3448.explosionbattle.utils;

import com.github.cocomon3448.explosionbattle.ExplosionBattle;
import org.bukkit.World;

public class MapReset {
    public static World oldW;
    public static World newOneW;

    public static void resetMap(World old,World newWrl) {
        if(old.getName().equals("world")) {
            FileUtils.unloadWorld(old);
            old.getWorldFolder().delete();
            FileUtils.copyWorld(newWrl, "world");
            newOneW = ExplosionBattle.getPlugin().getServer().getWorld("world");
            oldW = ExplosionBattle.getPlugin().getServer().getWorld("world-backup");
        } else if(old.getName().equals("world-backup")) {
            FileUtils.unloadWorld(old);
            old.getWorldFolder().delete();
            FileUtils.copyWorld(newWrl, "world-backup");
            newOneW = ExplosionBattle.getPlugin().getServer().getWorld("world-backup");
            oldW = ExplosionBattle.getPlugin().getServer().getWorld("world");
        }
    }
}
