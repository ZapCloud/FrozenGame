package com.zapcloudstudios.frozen.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;

public class PlayerQuit implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if (Frozen.gameStarted) {
			if(Frozen.players.contains(p.getName())){
				Frozen.totalAlive--;
				Frozen.players.remove(p.getName());
				Frozen.gameScoreboard();
			}
			if (Frozen.totalAlive == 1) {
				if (!Frozen.gameEnded) {
					GameManager.endGame();
				}
			}else {
				if(Frozen.frozenOne == p){
					int random = new Random().nextInt(Frozen.players.size());
					String player = Frozen.players.get(random);
					Player pl = Bukkit.getPlayer(player);
					API.setFrozenOne(pl, 5);
			}
		}
	}
}
}


