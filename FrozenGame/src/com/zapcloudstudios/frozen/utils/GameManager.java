package com.zapcloudstudios.frozen.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;

public class GameManager {

	public static void startGame() {

		Frozen.gameStarted = true;

		SpawnHandler.teleportToArena();

		for (Player p : Bukkit.getOnlinePlayers()) {
			Frozen.players.add(p.getName());
			Frozen.points.put(p.getName(), 0);
			Frozen.kills.put(p.getName(), 0);
			Frozen.deaths.put(p.getName(), 0);
			Frozen.totalAlive++;
			Frozen.scoreboard(p);
		}

		Bukkit.broadcastMessage("§cThe game has started! Last man standing wins!");
	}

	@SuppressWarnings("deprecation")
	public static void endGame() {

		Frozen.gameEnded = true;

		Player winner = null;

		for(String s : Frozen.players){
			winner = Bukkit.getPlayer(s);
			Bukkit.broadcastMessage("§cThe game has ended! Winner: " + s);
		}

		if(winner != null){
			API.firework(winner);
		}
	}
}
