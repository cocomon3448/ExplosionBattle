package com.github.cocomon3448.explosionbattle.commands;


import com.github.cocomon3448.explosionbattle.ExplosionBattle;
import com.github.cocomon3448.explosionbattle.utils.GenerateRandomNo;
import com.github.cocomon3448.explosionbattle.utils.ItemBuilder;
import com.github.cocomon3448.explosionbattle.utils.MapReset;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.Objects;


public class StartGameCommand implements CommandExecutor {

    public static Collection<Player> joinedPlayerP;

    private void giveItem(Player player) {
        ItemStack helmet = new ItemBuilder(Material.NETHERITE_HELMET, 1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack chestPlate = new ItemBuilder(Material.NETHERITE_CHESTPLATE, 1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack leggings = new ItemBuilder(Material.NETHERITE_LEGGINGS, 1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack boots = new ItemBuilder(Material.NETHERITE_BOOTS, 1).setUnbreakable(true).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).toItemStack();
        ItemStack sword = new ItemBuilder(Material.NETHERITE_SWORD, 1).setUnbreakable(true).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.SWEEPING_EDGE, 3).toItemStack();
        ItemStack tOfU = new ItemStack(Material.TOTEM_OF_UNDYING, 1);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestPlate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.getInventory().setItem(0, sword);
        player.getInventory().setItem(1, new ItemStack(Material.GOLDEN_APPLE, 16));
        player.getInventory().setItem(2, new ItemStack(Material.END_CRYSTAL, 64));
        player.getInventory().setItem(3, new ItemStack(Material.END_CRYSTAL, 64));
        player.getInventory().setItem(4, new ItemStack(Material.OBSIDIAN, 64));
        player.getInventory().setItem(5, new ItemStack(Material.OBSIDIAN, 64));
        player.getInventory().setItem(6, new ItemStack(Material.RESPAWN_ANCHOR, 64));
        player.getInventory().setItem(7, new ItemStack(Material.GLOWSTONE, 64));
        player.getInventory().setItem(8, new ItemStack(Material.ENDER_PEARL, 16));
        player.getInventory().setItem(9, tOfU);
        player.getInventory().setItem(10, tOfU);
        player.getInventory().setItemInOffHand(tOfU);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!ExplosionBattle.isRunning && !(Bukkit.getOnlinePlayers().size() <= 1)) {
            ExplosionBattle.isRunning = true;
            if (sender instanceof Player) {
                Player sendP = (Player) sender;
                Location loc = new Location(((Player) sender).getWorld(), 0, 0, 0);
                for (int i = 340; i >= -60; i--) {
                    loc = new Location(((Player) sender).getWorld(), 0, i, 0);
                    if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.WATER) {
                        break;
                    }
                }
                ExplosionBattle.joinedPlayer = Bukkit.getOnlinePlayers().size();
                joinedPlayerP = (Collection<Player>) Bukkit.getOnlinePlayers();

                sendP.sendTitle(ExplosionBattle.joinedPlayer+" Players Joined Game","",0,20, 0);

                int s = 0;
                for (Player player : joinedPlayerP) {
                    WorldBorder wb = player.getWorld().getWorldBorder();
                    wb.setCenter(0, 0);
                    wb.setSize(100); // world border size 100x100

                    int x = GenerateRandomNo.randomRangeInt(-50, 50);
                    int z = GenerateRandomNo.randomRangeInt(-50, 50);
                    int y = 0;
                    for(int i = 310; i>= -60; i--) {
                        Location cheakSurface = new Location(player.getWorld(), x, i, z);
                        if(cheakSurface != null) {
                            if(!(cheakSurface.getBlock().getType().equals(Material.AIR))) {
                                y = i;
                                break;
                            }
                        }
                    }

                    Location randomLoc = new Location(player.getWorld(), x, y+3, z);
                    player.teleport(randomLoc);
                    player.getInventory().clear();
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, true, true, true));
                    player.setHealth(20.0F);
                    player.setFoodLevel(20);
                    player.setBedSpawnLocation(loc);
//                    player.get

                    giveItem(player);
                    s++;
                }
            }
        } else if(ExplosionBattle.isRunning) {
            sender.sendMessage("Already Running");
        } else {
            sender.sendMessage("Need at least 2 players!");
        }

        return true;
    }
}
