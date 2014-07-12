package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class WeaponListener implements Listener{

    @EventHandler
    public void onWeaponUse(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player))
        {
            return;
        }

        Player p = (Player)e.getDamager();

        if(p.getItemInHand() != null)
        {
            if(p.getItemInHand().equals(ItemsFactory.getWarAxe()))
            {
                WeaponEffects.warAxe(e.getEntity());
                return;
            }
        }
    }

}
