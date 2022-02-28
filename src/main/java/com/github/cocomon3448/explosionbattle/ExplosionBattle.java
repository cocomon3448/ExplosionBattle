package com.github.cocomon3448.explosionbattle;

import com.github.cocomon3448.explosionbattle.commands.StartGameCommand;
import com.github.cocomon3448.explosionbattle.commands.StopGameCommand;
import com.github.cocomon3448.explosionbattle.events.OnConsumeTotem;
import com.github.cocomon3448.explosionbattle.events.OnWin;
import com.github.cocomon3448.explosionbattle.utils.FileUtils;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosionBattle extends JavaPlugin {

    public static World WORLD;
    public static Server SERVER;
    public static Plugin PLUGIN;
    public static int joinedPlayer = 0;
    public static boolean isRunning = false;

    @Override
    public void onEnable() {
        FileUtils.copyWorld(this.getServer().getWorld("world"), "world-backup");
        this.getCommand("start").setExecutor(new StartGameCommand());
        this.getCommand("stop").setExecutor(new StopGameCommand());
        this.getServer().getPluginManager().registerEvents(new OnConsumeTotem(), this);
        this.getServer().getPluginManager().registerEvents(new OnWin(), this);
        this.getLogger().info("Pvp plugin loaded");
        WORLD = this.getServer().getWorld("world-backup");
        PLUGIN = this;
        SERVER = this.getServer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("Pvp Plugin died during...your server stops");
    }
    public static World getWorld() {
        return WORLD;
    }
    public static Plugin getPlugin() {
        return PLUGIN;
    }
    public static Server getPServer() {
        return SERVER;
    }
}
