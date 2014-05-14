package com.zapcloudstudios.frozen.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;

public class EntityDeath implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			Player victim = (Player) e.getEntity();
			if (victim.getKiller() instanceof Player) {
				Player killer = (Player) victim.getKiller();

				API.addDeaths(victim, 1);
				API.addKills(killer, 1);
				API.addPoints(killer, 5);

				Frozen.scoreboard(killer);
				Frozen.scoreboard(victim);

				if (!Frozen.gameEnded) {
					if (API.getKills(killer) >= 5) {
						GameManager.endGame();
					}
				}
			}
		}
	}
}
