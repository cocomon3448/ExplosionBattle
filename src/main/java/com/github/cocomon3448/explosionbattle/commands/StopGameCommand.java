package com.github.cocomon3448.explosionbattle.commands;

import com.github.cocomon3448.explosionbattle.ExplosionBattle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class StopGameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(ExplosionBattle.isRunning) {
            ExplosionBattle.isRunning = false;
            sender.sendMessage("Game stopped");
        }

        return true;
    }
}
