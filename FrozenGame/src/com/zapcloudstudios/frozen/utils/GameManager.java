package com.zapcloudstudios.frozen.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;

public class GameManager {

	public static void startGame() {

		Frozen.gameStarted = true;

		for (Player p : Bukkit.getOnlinePlayers()) {
			SpawnHandler.spawnPlayerRandom(p);
			Frozen.players.add(p.getName());
			Frozen.points.put(p.getName(), 0);
			Frozen.totalAlive++;
			Frozen.scoreboard(p);
		}
		
		Player random = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
		
		Frozen.frozenOne = random;
		
		Frozen.frozenOne.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 255));

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
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(LobbyManager.lobby);
		}
	}
}
