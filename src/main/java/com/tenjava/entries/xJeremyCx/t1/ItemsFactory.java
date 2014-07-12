package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemsFactory {
    //Create items

    public static ItemStack getWarAxe()
    {
        ItemStack i = new ItemStack(Material.IRON_AXE);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "WarAxe");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "Rocket target to the sky when hit!");
        i.setItemMeta(im);
        return i;
    }

    public static ItemStack geMultiShoot()
    {
        ItemStack i = new ItemStack(Material.BOW);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "MultiShoot");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "Target and click to shoot!");
        i.setItemMeta(im);
        return i;
    }
}
