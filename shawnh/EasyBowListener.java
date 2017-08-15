package shawnh;

import java.util.logging.Logger;
import java.util.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;

import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.Material;


public class EasyBowListener implements org.bukkit.event.Listener{
	public EasyBowListener() {}

	@EventHandler // Fire an arrow when Right clicking with a bow, as long as you have enough arrows
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		UUID pUUID = p.getUniqueId();
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){ // If they Right Click
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW) { // If theyre holding a bow
				if(EasyBowUtils.fireArrow(pUUID)){ // If they can fire an arrow
					Entity arrow = p.launchProjectile(Arrow.class);
				}
			}
		}
	}

	@EventHandler
	public void onArrowHit(ProjectileHitEvent event){
		if(event.getEntity() instanceof Arrow){
			Arrow arrow = (Arrow) event.getEntity();
			arrow.remove();
		}
	}

	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent e) {
		LivingEntity entity = e.getEntity();
		if(entity instanceof Player && e.getItem().getItemStack().getType() == Material.ARROW){
			Player p = (Player) entity;
			UUID pUUID = p.getUniqueId();
			int amountOfArrows = e.getItem().getItemStack().getAmount();
			EasyBowUtils.changeArrows(pUUID, amountOfArrows);

			e.getItem().remove();
			e.setCancelled(true);
		}
	}
	
}

