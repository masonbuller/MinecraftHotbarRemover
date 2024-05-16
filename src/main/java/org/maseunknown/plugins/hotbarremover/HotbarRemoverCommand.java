package org.maseunknown.plugins.hotbarremover;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HotbarRemoverCommand implements CommandExecutor {
    private static HotbarRemover hotbarRemover;
    private static BukkitTask task;

    public HotbarRemoverCommand(HotbarRemover hotbarRemover) {
        HotbarRemoverCommand.hotbarRemover = hotbarRemover;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "You must type an argument. For help, type: /hotbarremover help");
        }

        if (args[0].equals("help")) {
            if (args.length != 1) {
                p.sendMessage(ChatColor.RED + "Wrong use of this command. For help, type: /invshuffle help");
            } else {
                p.sendMessage(ChatColor.DARK_AQUA + "----------" + ChatColor.WHITE + "Hotbar Random Item Remover Commands" + ChatColor.DARK_AQUA + "----------");
                p.sendMessage(ChatColor.AQUA + "/hotbarremover start" + ChatColor.WHITE + " - starts the timer");
                p.sendMessage(ChatColor.AQUA + "/hotbarremover stop" + ChatColor.WHITE + " - stops the timer");
                p.sendMessage(ChatColor.AQUA + "/hotbarremover help" + ChatColor.WHITE + " - lists commands");
            }
        }

        if (args[0].equals("start") || args[0].equals("stop")) {
            if (args.length != 1) {
                p.sendMessage(ChatColor.RED + "Wrong use of this command. For help, type: /hotbarremover help");
            } else {
                task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        ItemStack item;
                        if (args[0].equals("stop")) {
                            Bukkit.getScheduler().cancelTask(task.getTaskId());
                        } else {
                            do {
                                PlayerInventory inv = p.getInventory();
                                double rand = Math.floor(Math.random() * 9);
                                int random = (int) rand;
                                item = inv.getItem(random);
                                if (item != null && !item.getType().equals(Material.AIR)) {
                                    p.getInventory().setItem(random, new ItemStack(Material.AIR));
                                    p.sendMessage(ChatColor.DARK_AQUA + "ITEM REMOVED");
                                }
                            } while (item.getType().equals(Material.AIR) && !args[0].equals("stop"));

                        }
                    }
                }.runTaskTimer(hotbarRemover, 1200L, 1200L);
            }
        }
        return true;
    }
}
