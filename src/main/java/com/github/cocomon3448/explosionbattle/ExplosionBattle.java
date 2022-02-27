package com.github.cocomon3448.explosionbattle;

import com.github.cocomon3448.explosionbattle.commands.StartGameCommand;
import com.github.cocomon3448.explosionbattle.events.OnConsumeTotem;
import com.github.cocomon3448.explosionbattle.events.OnWin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosionBattle extends JavaPlugin {

    public static int joinedPlayer = 0;
    public static boolean isRunning = false;

    @Override
    public void onEnable() {
        this.getCommand("start").setExecutor(new StartGameCommand());
        this.getServer().getPluginManager().registerEvents(new OnConsumeTotem(), this);
        this.getServer().getPluginManager().registerEvents(new OnWin(), this);
        this.getLogger().info("피빞피 플러그인이 로딩되었습니다!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("피빞피 플러그인이 죽어버렸습니다!");
    }
}
