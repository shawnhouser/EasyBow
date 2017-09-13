package shawnh;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class EB_ShootArrow{
	EB_ShootArrow() {}

	public static void s(Player player, int damage){
		//Arrow arrow = null;
		//Location location = player.getLocation();
		//location.setX(location.getX() + direction.getX() * (1 + Math.random() * arrowCount));
		//location.setY(location.getY() + 1.5f);
		//location.setZ(location.getZ() + direction.getZ() * (1 + Math.random() * arrowCount));


		//Location loc = player.getLocation(); // Get the player Location
		//loc.add(0, 1, 0); //Add 1 to the Y, makes the arrow go at chest level instead of feet
		//Arrow arrow = player.getWorld().spawn(loc, Arrow.class); //Spawn Entity
		//arrow.setShooter(player); //Set the shooter to your player

		/*arrow = player.getWorld().spawnArrow(location, direction, speed, spread);

		if (arrow == null) {
			sendMessage("One of your arrows fizzled");
			return SpellResult.FAILURE;
		}

		arrow.setShooter(player);

		if (useFire) {
			arrow.setFireTicks(300);
		}*/

		// Hackily make this an infinite arrow and set damage
		Arrow arrow = player.launchProjectile(Arrow.class);
		try {
			Method getHandleMethod = arrow.getClass().getMethod("getHandle");
			Object handle = getHandleMethod.invoke(arrow);
			Field fromPlayerField = handle.getClass().getField("fromPlayer");
			fromPlayerField.setInt(handle, 2);
			Method setDamageMethod = handle.getClass().getMethod("a", Double.TYPE);
			setDamageMethod.invoke(handle, damage);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		arrow.setTicksLived(300);
	}
}