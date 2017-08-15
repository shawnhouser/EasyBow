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

		//EasyBowUtils.setHashMap(loadHashMap("arrows"));
		EasyBowUtils.loadFromDisk(this);
		getServer().getPluginManager().registerEvents(bow, this);
	}
	
	public void onDisable() {
		log.info("[EasyBow] Disabled");
		EasyBowUtils.saveToDisk(this);	
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		if(alias.equalsIgnoreCase("easybow")){
			String messageToSend = "§6/easyBow§f This Page\n" +
								   "§6/easyBow amount§f Number of arrows left\n"+
								   "§6/easyBow set {number}§f Sets number of arrows in quiver\n"+
								   "§6/easyBow change {number}§f Changes number of arrows quiver";
			UUID pUUID = ((Player) sender).getUniqueId();
			if(args.length != 0){
				switch(args[0].toLowerCase()) {
					case "a": ;
					case "arrows": ;
					case "amount": messageToSend = "§3"+EasyBowUtils.getArrows(pUUID) + " arrow(s)"; break;
					case "s": ;
					case "set": 
						if(args.length != 1) { try{
							int amountOfArrows = Integer.parseInt(args[1]);
							EasyBowUtils.setArrows(pUUID, amountOfArrows);
							messageToSend = "You now have §3"+EasyBowUtils.getArrows(pUUID) + " arrow(s)";
						} catch(NumberFormatException e){
							messageToSend = "§c[EasyBow] Enter a valid Number";
						}} break;
					case "c": ;
					case "change":
						if(args.length != 1) { try{
							int changeAmount = Integer.parseInt(args[1]);
							EasyBowUtils.changeArrows(pUUID, changeAmount);
							messageToSend = "You now have §3"+EasyBowUtils.getArrows(pUUID) + " arrow(s)";
						} catch(NumberFormatException e){
							messageToSend = "§c[EasyBow] Enter a valid Number";
						}} break;
					default: ; break;
				}
			}
			if(messageToSend.length() != 0){
				sender.sendMessage(messageToSend);
			}
		}
		return true;
	}
}