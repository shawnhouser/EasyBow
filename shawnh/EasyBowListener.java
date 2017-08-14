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
	
	Map<Player, Integer> playersArrows = new HashMap<Player, Integer>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand().getType() == Material.BOW) {
				if(playersArrows.get(p) == null){
					playersArrows.put(p, 100);
				}
				if(playersArrows.get(p) >= 1){
					playersArrows.put(p, playersArrows.get(p)-1);
					Entity arrow = p.launchProjectile(Arrow.class);
					EasyBowInit.objective.getScore(p.getDisplayName()).setScore(playersArrows.get(p));
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
			int amountOfArrows = e.getItem().getItemStack().getAmount();
			playersArrows.put(p, playersArrows.get(p)+amountOfArrows);
			EasyBowInit.objective.getScore(p.getDisplayName()).setScore(playersArrows.get(p));
			e.getItem().remove();
			e.setCancelled(true);
		}
    }
}

