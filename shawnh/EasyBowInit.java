package shawnh;

import org.bukkit.scoreboard.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EasyBowInit {
	public static Objective objective;
	
	public static void init() {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		objective = board.registerNewObjective("quiver", "dummy");
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		for(Player online : Bukkit.getOnlinePlayers()){
			online.setScoreboard(board);
			objective.getScore(online.getDisplayName()).setScore(100);
		}
	}
}