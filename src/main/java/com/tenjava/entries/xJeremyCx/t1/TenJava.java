package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        return true;
    }
}
