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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;


public class EB_Listener implements org.bukkit.event.Listener{
	public EB_Listener() {}

	/* Fire an arrow when Right clicking with a bow, as long as you have enough arrows */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		UUID pUUID = p.getUniqueId();
		if(p.getInventory().getItemInMainHand().getType() == Material.BOW) { // If theyre holding a bow
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){ // If they Right Click
				if(event.getHand().equals(EquipmentSlot.HAND)){ // Fired from Main Hand
					if(EasyBowUtils.fireArrow(pUUID)){ // If they can fire an arrow
						Arrow arrow = p.launchProjectile(Arrow.class);
						//EB_ShootArrow.s(p, 400);
//						net.minecraft.server.EntityArrow nmsArrow = ((CraftArrow)arrow).getHandle();

						//public void setMetadata(Entity player, String key, Object value, Plugin plugin){
							arrow.setMetadata("damage",new FixedMetadataValue(EasyBow.inst(), 40));
							//Logger.getLogger("Minecraft").info(arrow.getMetadata("damage").get(0).value().toString());
						//}
					}
				}
			}
		}
	}

	/* Remove arrows when they hit the ground */
	@EventHandler
	public void onArrowHit(ProjectileHitEvent event){
		if(event.getEntity() instanceof Arrow){
			Arrow arrow = (Arrow) event.getEntity();
			arrow.remove();
		}
	}

	/* Add arrows to quiver on pickup rather than inventory */
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

	@EventHandler
	public void onArrowHit (EntityDamageByEntityEvent event){
		//if (event.getEntity() instanceof Player){
		//	Player player = (Player) event.getEntity();
			if (event.getDamager() instanceof Arrow){
				Arrow arrow = (Arrow) event.getDamager();
				if (arrow.getShooter() instanceof Player){
					double damage = (int) arrow.getMetadata("damage").get(0).value() + 0.0;
					event.setDamage(damage);
				}
			}
		
	}
}

