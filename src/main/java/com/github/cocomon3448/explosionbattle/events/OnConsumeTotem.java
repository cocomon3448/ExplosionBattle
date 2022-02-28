package com.github.cocomon3448.explosionbattle.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class OnConsumeTotem implements Listener {

    @EventHandler
    public void onTotemUse(EntityResurrectEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            for(PotionEffect pE:p.getActivePotionEffects()) {
                p.removePotionEffect(pE.getType());
            }
            p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, true, true,true));
        }
    }
}
