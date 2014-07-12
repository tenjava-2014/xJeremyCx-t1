package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;

public class WeaponEffects {

    private static ArrayList<String> recentShoot = new ArrayList<String>();

    public static void warAxe(Entity target) {
        target.getWorld().playSound(target.getLocation(), Sound.FIREWORK_LAUNCH, 1F, 1F);
        target.setVelocity(new Vector(0, 30, 0).multiply(4));
    }

    public static void multishoot(Player p) {
        if(!recentShoot.contains(p.getUniqueId().toString()))
        {
            Location eye = p.getEyeLocation();
            new MultiShoot().shoot(p);
        }
        else
        {
            p.sendMessage(ChatColor.DARK_RED + "You may not keep using this weapon! Try again later!");
        }
    }

    private static class MultiShoot {
        int id = 0;
        int times = 8;

        public void shoot(final Player p)
        {
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.getPlugin(), new Runnable() {

                @Override
                public void run() {
                    Arrow a = (Arrow)p.getEyeLocation().getWorld().spawnEntity(p.getEyeLocation(), EntityType.ARROW);
                    a.setVelocity(p.getEyeLocation().getDirection().multiply(5));
                    a.setMetadata("multishoot", new FixedMetadataValue(TenJava.getPlugin(), true));
                    p.playSound(p.getLocation(), Sound.SHOOT_ARROW, 1F, 1F);
                    times--;

                    if(times==0)
                    {
                        Bukkit.getScheduler().cancelTask(id);
                        Iterator<String> i = recentShoot.iterator();
                        while (i.hasNext())
                        {
                            String uuid = i.next();
                            if(uuid.equals(p.getUniqueId().toString()))
                            {
                                i.remove();
                            }
                        }
                    }
                }
            },0L, 4L);
        }
    }
}
