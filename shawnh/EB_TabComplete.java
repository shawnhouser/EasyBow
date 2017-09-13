package shawnh;

import java.util.*;
import org.bukkit.command.*;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class EB_TabComplete implements org.bukkit.command.TabCompleter {
	public EB_TabComplete() {}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		ArrayList<String> completes = new ArrayList<String>();
		boolean isEasyBow = cmd.getName().equalsIgnoreCase("easybow") || cmd.getName().equalsIgnoreCase("eb");
		if (isEasyBow && args.length == 1) {
			completes.add("amount");
			completes.add("change");
			completes.add("set");
		}	   
		return completes;
	}
}