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

		for (Player p : Bukkit.getOnlinePlayers()) {
			SpawnHandler.spawnPlayerRandom(p);
			Frozen.players.add(p.getName());
			Frozen.points.put(p.getName(), 0);
			Frozen.totalAlive++;
			Frozen.gameScoreboard();
		}
		
		Player random = Bukkit.getOnlinePlayers()[new Random().nextInt(Bukkit.getOnlinePlayers().length)];
		
		API.setFrozenOne(random, 15);
		
		for(Player p : Bukkit.getOnlinePlayers()){
			p.sendMessage("§cThe game has started! Last player alive wins!");
	}
		Frozen.gameStarted = true;
}
	
	@SuppressWarnings("deprecation")
	public static void endGame() {
		
		Bukkit.getScheduler().cancelAllTasks();
	
		Frozen.gameEnded = true;
		Frozen.gameStarted = false;

		Player winner = null;

		for(String s : Frozen.players){
			winner = Bukkit.getPlayer(s);
			for(Player pl : Bukkit.getOnlinePlayers()) {
				pl.kickPlayer("Game Ended, Winner: " + s);
			// Bukkit.broadcastMessage("§cThe game has ended! Winner: " + s);
			}
		}
		
		if(winner != null){
			API.firework(winner);
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(LobbyManager.lobby);
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
		}
		
		Frozen.players.clear();
		Frozen.spectators.clear();
		Frozen.totalAlive = 0;
		Frozen.frozenOne = null;
	}
}
