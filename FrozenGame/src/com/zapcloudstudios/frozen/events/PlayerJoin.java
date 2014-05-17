package com.zapcloudstudios.frozen.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;
import com.zapcloudstudios.frozen.utils.SpawnHandler;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		if (!Frozen.gameStarted) {
			if (Bukkit.getOnlinePlayers().length >= 18) {
				GameManager.startGame();
			}
		}else {
			API.setSpectator(p);
			SpawnHandler.spawnPlayerRandom(p);
		}

		API.json(p, "§fWelcome to §6Frozen ", "§bClick Here", "hoverEvent", "show_text", "§cClick to see stats", "run_command", "/frozen stats");
	}
}
