package shawnh;

import java.util.*;

public class EasyBowUtils {
	public EasyBowUtils() {}
	final static String saveLocation = "arrows";

	private static Map<String, Integer> pah; // playersArrowsHashmap

	public static int getArrows(UUID pUUID){
		try {
			return pah.get(pUUID.toString());
		} catch(NullPointerException e){ // Player is not in the Map
			setArrows(pUUID, 0);
			return 0;
		}
	}

	public static void setArrows(UUID pUUID, int newAmmount){
		pah.put(pUUID.toString(), newAmmount);
	}

	public static void changeArrows(UUID pUUID, int difference){
		setArrows(pUUID, getArrows(pUUID) + difference);
	}

	public static boolean fireArrow(UUID pUUID){
		int arrows = getArrows(pUUID);
		if(arrows  > 0){
			changeArrows(pUUID, -1);
			return true;
		} else {
			return false;
		}
	}

	public static void saveToDisk(org.bukkit.plugin.java.JavaPlugin plugin){
		plugin.getConfig().createSection(saveLocation, pah);
		plugin.saveConfig();
	}

	public static void loadFromDisk(org.bukkit.plugin.java.JavaPlugin plugin){
		pah = new HashMap<String, Integer>();

		for (String key : plugin.getConfig().getConfigurationSection(saveLocation).getKeys(false)) {
			pah.put(key, (Integer) plugin.getConfig().get(saveLocation+"."+key));
		}
	}
}