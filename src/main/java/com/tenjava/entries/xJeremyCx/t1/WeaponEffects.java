package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class WeaponEffects {

    private static ArrayList<String> recentShoot = new ArrayList<String>();
    private static ArrayList<String> recentStick = new ArrayList<String>();

    public static void warAxe(Entity target) {
        target.getWorld().playSound(target.getLocation(), Sound.FIREWORK_LAUNCH, 1F, 1F);
        target.setVelocity(new Vector(0, 30, 0).multiply(4));
    }

    public static void multiShoots(Player p) {
        if(!recentShoot.contains(p.getUniqueId().toString()))
        {
            recentShoot.add(p.getUniqueId().toString());
            new MultiShoots().shoot(p);
        }
        else {
            p.sendMessage(ChatColor.DARK_RED + "You may not keep using this weapon! Try again later!");
        }
    }

    public static void enderSticks(Player p, Entity target) {
        if(!recentStick.contains(p.getUniqueId().toString())) {
            recentStick.add(p.getUniqueId().toString());
            new EnderStick().run(target, p.getUniqueId().toString());
        }
        else {
            p.sendMessage(ChatColor.DARK_RED + "You may not keep using this weapon! Try again later!");
        }
    }

    private static class MultiShoots {
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

    private static class EnderStick {
        int id = 0;
        int times = 5;

        public void run(final Entity e, final String uuid)
        {
            final Random r = new Random();
            final LivingEntity le = (LivingEntity)e;
            id = Bukkit.getScheduler().scheduleSyncRepeatingTask(TenJava.getPlugin(), new Runnable() {

                @Override
                public void run() {

                    int x = r.nextInt(8);
                    int y = r.nextInt(6);
                    int z = r.nextInt(9);
                    Vector v = new Vector(x, y, z).multiply(2);
                    e.setVelocity(v);
                    le.damage(7D);
                    e.getWorld().playSound(e.getLocation(), Sound.HURT_FLESH, 1F, 1F);
                    e.getWorld().playEffect(e.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);

                    if(times==0)
                    {
                        Bukkit.getScheduler().cancelTask(id);
                        Iterator<String> i = recentStick.iterator();
                        while (i.hasNext())
                        {
                            String uuid1 = i.next();
                            if(uuid1.equals(uuid))
                            {
                                i.remove();
                            }
                        }
                    }
                }
            },0L, 10L);
        }
    }
}
