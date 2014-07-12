package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    //wtf I just started ends in 2 hours 26 minutes 7 seconds
    //

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("Weabomb");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WeaponListener(), this);

        getLogger().info("Weabomb v" + getDescription().getVersion() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Weabomb v" + getDescription().getVersion() + " has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender cs, Command c, String arg, String[] args) {

        if(!(cs instanceof Player)) {
            cs.sendMessage("This command can only be run by player!");
            return true;
        }

        if(!cs.isOp() || !cs.hasPermission("warbomb.info")) {
            cs.sendMessage(ChatColor.DARK_RED + "You don't have permission to do this!");
            return true;
        }

        if(args.length==0) {
            cs.sendMessage(ChatColor.GREEN + "=============================================");
            cs.sendMessage(ChatColor.AQUA + "/warbomb spawn <weapon>");
            cs.sendMessage(ChatColor.GOLD + "Available weapons: waraxe, multishoots, enderstick");
            cs.sendMessage(ChatColor.GREEN + "=============================================");
            return true;
        } else {
            if(args[0].equalsIgnoreCase("spawn")) {
                if(args.length==1) {
                    cs.sendMessage(ChatColor.DARK_RED + "Correct usage: /warbomb spawn <weapon>");
                    return true;
                }
                else
                {
                    if(!cs.isOp() || !cs.hasPermission("warbomb.spawn")) {
                        cs.sendMessage(ChatColor.DARK_RED + "You don't have permission spawn weapons!");
                        return true;
                    }
                    String weapon = args[1];
                    Player p = (Player)cs;
                    if(weapon.equalsIgnoreCase("waraxe")) {
                        if(!p.isOp() || !p.hasPermission("warbomb.spawn.waraxe")) {
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission spawn this weapon!");
                            return true;
                        }
                        p.getInventory().addItem(ItemsFactory.getWarAxe());
                        return true;
                    } else if(weapon.equalsIgnoreCase("multishoots")) {
                        if(!p.isOp() || !p.hasPermission("warbomb.spawn.multishoots")) {
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission spawn this weapon!");
                            return true;
                        }
                        p.getInventory().addItem(ItemsFactory.getMultiShoots());
                        return true;
                    } else if(weapon.equalsIgnoreCase("enderstick")) {
                        if(!p.isOp() || !p.hasPermission("warbomb.spawn.enderstick")) {
                            p.sendMessage(ChatColor.DARK_RED + "You don't have permission spawn this weapon!");
                            return true;
                        }
                        p.getInventory().addItem(ItemsFactory.getEnderStick());
                        return true;
                    } else {
                        cs.sendMessage(ChatColor.GOLD + "Available weapons: waraxe, multishoots, enderstick");
                        return true;
                    }
                }
            }
        }

        return true;
    }
}
