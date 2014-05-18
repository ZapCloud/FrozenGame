package com.zapcloudstudios.frozen.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;
import com.zapcloudstudios.frozen.utils.LobbyManager;
import com.zapcloudstudios.frozen.utils.SpawnHandler;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
		if (!Frozen.gameStarted) {
			p.teleport(LobbyManager.lobby);
			p.getInventory().clear();
			if (Bukkit.getOnlinePlayers().length >= 2) {
				GameManager.startGame();
			}
		}else {
			API.setSpectator(p);
			SpawnHandler.spawnPlayerRandom(p);
		}

		p.sendMessage("§6Welcome to §bFrozen§6!");
		p.setFoodLevel(20);
	}
}
