package shawnh;

import java.util.logging.Logger;
import java.util.*;

public class EasyBow extends org.bukkit.plugin.java.JavaPlugin {
	public EasyBow() {}
	
	public void onEnable() {
		EasyBowUtils.loadFromDisk(this);             // Load quiver amounts
		
		EB_Listener bow = new EB_Listener();                  // Setup listener
		getServer().getPluginManager().registerEvents(bow, this); // Load listener

		getCommand("easybow").setExecutor(new EB_Commands());        // Set up commands
		getCommand("easybow").setTabCompleter(new EB_TabComplete()); // Set up auto completes

		instance = this;

		Logger.getLogger("Minecraft").info("[EasyBow] Enabled");
	}
	
	public void onDisable() {
		EasyBowUtils.saveToDisk(this); // Save quiver amounts

		Logger.getLogger("Minecraft").info("[EasyBow] Disabled");
	}

	private static EasyBow instance;
	public static EasyBow inst() {
		return instance;
	}
}