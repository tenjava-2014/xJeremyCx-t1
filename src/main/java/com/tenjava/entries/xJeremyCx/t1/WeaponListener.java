package com.tenjava.entries.xJeremyCx.t1;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class WeaponListener implements Listener {

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

            if(p.getItemInHand().equals(ItemsFactory.getEnderStick())) {
                WeaponEffects.enderSticks(p, e.getEntity());
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        if(e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }

        if(e.getPlayer().getItemInHand() != null) {
            if(e.getPlayer().getItemInHand().equals(ItemsFactory.getMultiShoots())) {
                WeaponEffects.multiShoots(e.getPlayer());
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
