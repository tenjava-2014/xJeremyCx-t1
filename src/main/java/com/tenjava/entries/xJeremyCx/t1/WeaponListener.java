package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class WeaponListener implements Listener{

    @EventHandler
    public void onWeaponUse(EntityDamageByEntityEvent e) {

        if(!(e.getDamager() instanceof Player)) {
            return;
        }

        Player p = (Player)e.getDamager();

        if(p.getItemInHand() != null) {

            if(p.getItemInHand().equals(ItemsFactory.getWarAxe())) {
                WeaponEffects.warAxe(e.getEntity());
                return;
            }

            if(p.getItemInHand().equals(ItemsFactory.geMultiShoot())) {
                WeaponEffects.multishoot(p);
                return;
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(e.getItem().getType()== EntityType.ARROW) {
            if(e.getItem().hasMetadata("multishoot")) {
                e.getItem().remove();
            }
        }
    }

}
