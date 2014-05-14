package com.zapcloudstudios.frozen.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.ParticleUtils;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();

		API.addDeaths(p, 1);

		if (API.getDeaths(p) == 5) {
			API.setSpectator(p);
		}

		for (Player pl : Bukkit.getOnlinePlayers()) {
			try {
				ParticleUtils.spawnParticles(p.getLocation(), pl, "flame", 80);
			} catch (ClassNotFoundException | IllegalAccessException
					| InstantiationException | NoSuchMethodException
					| NoSuchFieldException | IllegalArgumentException
					| InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}

		Frozen.scoreboard(p);

		p.setHealth(20D);
		p.teleport(Frozen.arenaSpawns.get(1));
	}
}
