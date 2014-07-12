package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    //wtf I just started. Ends in 2 hours 26 minutes 7 seconds
    //

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("Weabomb");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WeaponListener(), this);

        ShapedRecipe waraxe = new ShapedRecipe(ItemsFactory.getWarAxe());
        waraxe.shape(new String[]{"TRT", "RAR", "TNT"});
        waraxe.setIngredient('T', Material.TNT);
        waraxe.setIngredient('R', Material.REDSTONE_BLOCK);
        waraxe.setIngredient('A', Material.DIAMOND_AXE);
        waraxe.setIngredient('N', Material.NETHER_BRICK);
        getServer().addRecipe(waraxe);

        ShapedRecipe multishoots = new ShapedRecipe(ItemsFactory.getMultiShoots());
        multishoots.shape(new String[]{"AAA", "TBT", "RNR"});
        multishoots.setIngredient('A', Material.ARROW);
        multishoots.setIngredient('T', Material.TNT);
        multishoots.setIngredient('B', Material.BOW);
        multishoots.setIngredient('R', Material.REDSTONE);
        multishoots.setIngredient('N', Material.NETHERRACK);
        getServer().addRecipe(multishoots);

        ShapedRecipe enderstick = new ShapedRecipe(ItemsFactory.getEnderStick());
        enderstick.shape(new String[]{"TTT", "RSR", "PEP"});
        enderstick.setIngredient('T', Material.TNT);
        enderstick.setIngredient('R', Material.REDSTONE_BLOCK);
        enderstick.setIngredient('S', Material.STICK);
        enderstick.setIngredient('P', Material.PISTON_STICKY_BASE);
        enderstick.setIngredient('E', Material.ENDER_STONE);
        getServer().addRecipe(enderstick);

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
