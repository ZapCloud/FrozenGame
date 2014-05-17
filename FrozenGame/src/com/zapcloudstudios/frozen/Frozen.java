package com.zapcloudstudios.frozen;


import java.util.ArrayList;
import java.util.HashMap;

import com.zapcloudstudios.frozen.events.EntityDamage;
import com.zapcloudstudios.frozen.events.PlayerDeath;
import com.zapcloudstudios.frozen.events.PlayerDropItem;
import com.zapcloudstudios.frozen.events.PlayerJoin;
import com.zapcloudstudios.frozen.events.PlayerPickupItem;
import com.zapcloudstudios.frozen.events.PlayerQuit;
import com.zapcloudstudios.frozen.events.SnowballHit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Frozen extends JavaPlugin implements Listener {

	public static Frozen plugin;

	public static Boolean gameStarted, gameEnded;

	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> spectators = new ArrayList<>();

	public static ArrayList<Location> arenaSpawns = new ArrayList<>();

	public static FileConfiguration config;

	public static int totalAlive;

	public static HashMap<String, Integer> points = new HashMap<>();
	
	public static Player frozenOne;

	public void onEnable() {

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new EntityDamage(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerDropItem(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerPickupItem(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new SnowballHit(), this);

		frozenOne = null;
		
		totalAlive = 0;

		plugin = this;

		config = getConfig();

		saveDefaultConfig();

		gameStarted = false;
		gameEnded = false;
	}

	@SuppressWarnings("deprecation")
	public static void scoreboard(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();

		Objective objective = board.registerNewObjective("Test", "Test2");
		objective.setDisplayName("§b§lFrozen");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score score = objective.getScore(Bukkit.getOfflinePlayer("§6Alive"));
		score.setScore(API.getAlive());

		Score score2 = objective.getScore(Bukkit.getOfflinePlayer("§6Points"));
		score2.setScore(API.getPoints(p));

		p.setScoreboard(board);
	}
}
