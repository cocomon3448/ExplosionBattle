package com.github.cocomon3448.explosionbattle.events;

import com.github.cocomon3448.explosionbattle.ExplosionBattle;
import com.github.cocomon3448.explosionbattle.commands.StartGameCommand;
import com.github.cocomon3448.explosionbattle.utils.MapReset;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnWin implements Listener {
    @EventHandler
    public void OnWin(PlayerDeathEvent e) {
        ExplosionBattle.joinedPlayer -= 1;
        if (ExplosionBattle.joinedPlayer == 0) {
            Entity killer = e.getEntity().getKiller(); // if killer is console not work

            for (Player p : StartGameCommand.joinedPlayerP) {
                p.sendTitle(ChatColor.GOLD + (killer != null ? killer.getName() : "Unknown") + " Wins", "Wow", 0, 20, 0);
                p.getInventory().clear();
                p.getActivePotionEffects().forEach(pE -> p.removePotionEffect(pE.getType()));
            }
            MapReset.resetMap(killer.getWorld(),ExplosionBattle.getWorld());
            for(Player p: Bukkit.getOnlinePlayers()) {
                p.sendTitle("Preparing","wait for 5 sec", 0, 5, 0);
                p.teleport(MapReset.newOneW.getSpawnLocation());
            }

            StartGameCommand.joinedPlayerP = null;
            ExplosionBattle.isRunning = false;
        }
    }
}
// ㅡㅡ ㅎㅎ :D