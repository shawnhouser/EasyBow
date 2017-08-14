package shawnh;

import org.bukkit.scoreboard.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.logging.Logger;

public class EasyBowInit {
	public static Objective objective;
	
	public static void init() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		objective = board.registerNewObjective("quiver", "dummy");
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		for(Player online : Bukkit.getOnlinePlayers()){
			online.setScoreboard(board);
			try{
				int arrows = EasyBowListener.playersArrows.get(online.getUniqueId().toString());
				objective.getScore(online.getDisplayName()).setScore(arrows);

			} catch(NullPointerException e){
				objective.getScore(online.getDisplayName()).setScore(100);
			}
		}
	}
}