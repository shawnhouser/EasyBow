package shawnh;

import java.util.logging.Logger;

public class EasyBow extends org.bukkit.plugin.java.JavaPlugin {
	public EasyBow() {}
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		log.info("[EasyBow] Enabled");
		EasyBowListener bow = new EasyBowListener();
		EasyBowInit.init();
		getServer().getPluginManager().registerEvents(bow, this);
	}
	
	public void onDisable() {
		log.info("[EasyBow] Disabled");

		this.getConfig().set("path.to.boolean", true);
	}
}