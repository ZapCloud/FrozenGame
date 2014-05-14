package com.zapcloudstudios.frozen.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (Frozen.gameStarted) {
			if(Frozen.players.contains(p.getName())){
				API.addDeaths(p, 1);
				Frozen.totalAlive--;
				Frozen.players.remove(p.getName());
			}
		}

		if (Frozen.totalAlive <= 2) {
			if (Frozen.gameEnded) {
				GameManager.endGame();
			}
		}
	}
}
