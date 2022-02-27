package com.github.cocomon3448.explosionbattle.events;

import com.github.cocomon3448.explosionbattle.ExplosionBattle;
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
        if (ExplosionBattle.joinedPlayer == 1) {

            Entity killer = e.getEntity().getKiller();

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.GOLD + (killer != null ? killer.getName() : "알 수 없음") + "님이 이기셨네요", "?", 0, 20, 0);
                p.getInventory().clear();
                p.getActivePotionEffects().forEach(pE -> p.removePotionEffect(pE.getType()));
            }
            ExplosionBattle.isRunning = false;
        }
    }
}
// ㅡㅡ ㅎㅎ :D