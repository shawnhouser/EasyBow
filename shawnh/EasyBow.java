package shawnh;

import java.util.logging.Logger;
import java.util.*;
import org.bukkit.command.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class EasyBow extends org.bukkit.plugin.java.JavaPlugin {
	public EasyBow() {}
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		log.info("[EasyBow] Enabled");
		EasyBowListener bow = new EasyBowListener();

		EasyBowListener.playersArrows = loadHashMap("arrows");
		EasyBowInit.init();
		getServer().getPluginManager().registerEvents(bow, this);
	}
	
	public void onDisable() {
		log.info("[EasyBow] Disabled");

		this.getConfig().createSection("arrows", EasyBowListener.playersArrows);
		this.saveConfig();
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(alias.equalsIgnoreCase("easybow")){
			String messageToSend = "/easyBow: This Page\n" +
								   "/easyBow amount: Number of arrows left\n"+
								   "/easyBow add {number}: Add {number} arrows to your quiver";
			String pUUID = ((Player) sender).getUniqueId().toString();
			if(args.length != 0){
				switch(args[0].toLowerCase()) {
					case "amount": messageToSend = EasyBowListener.playersArrows.get(pUUID) + " arrow(s)"; break;
					default: ; break;
				}
			}
			sender.sendMessage(messageToSend);
		}
		return true;
	}

	public HashMap<String, Integer> loadHashMap(String name) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		for (String key : this.getConfig().getConfigurationSection(name).getKeys(false)) {
			hm.put(key, (Integer) this.getConfig().get(name+"."+key));
		}
		return hm;
	}
}