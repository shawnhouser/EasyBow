package shawnh;

//import java.util.logging.Logger;
import java.util.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.Material;

public class EasyBowListener implements org.bukkit.event.Listener{
	public EasyBowListener() {}
	
	Map<Player, Integer> playersArrows = new HashMap<Player, Integer>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(player.getInventory().getItemInMainHand().getType() == Material.BOW) {
				if(playersArrows.get(player) == null){
					playersArrows.put(player, 99);
				}
				if(playersArrows.get(player) >= 1){
					playersArrows.put(player, playersArrows.get(player)-1);
					player.launchProjectile(Arrow.class);
				} else {
					player.sendMessage("No Arrows Left");
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
}

