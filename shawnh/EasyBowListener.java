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
import org.bukkit.scoreboard.*;


public class EasyBowListener implements org.bukkit.event.Listener{
	public EasyBowListener() {}
	
	static Map<String, Integer> playersArrows;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		String pUUID = p.getUniqueId().toString();
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW) {
				if(playersArrows.get(pUUID) == null){
					playersArrows.put(pUUID, 100);
				}
				if(playersArrows.get(pUUID) >= 1){
					playersArrows.put(pUUID, playersArrows.get(pUUID)-1);
					Entity arrow = p.launchProjectile(Arrow.class);
					EasyBowInit.objective.getScore(p.getDisplayName()).setScore(playersArrows.get(pUUID));
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
			String pUUID = p.getUniqueId().toString();
			int amountOfArrows = e.getItem().getItemStack().getAmount();
			playersArrows.put(pUUID, playersArrows.get(pUUID)+amountOfArrows);
			EasyBowInit.objective.getScore(p.getDisplayName()).setScore(playersArrows.get(pUUID));
			e.getItem().remove();
			e.setCancelled(true);
		}
	}
	
}

