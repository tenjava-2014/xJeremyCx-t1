package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class WeaponEffects {

    public static void warAxe(Entity target) {
        target.setVelocity(new Vector(0, 30, 0).multiply(4));
    }
}
