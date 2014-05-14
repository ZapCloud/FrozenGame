package com.zapcloudstudios.frozen.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.zapcloudstudios.frozen.Frozen;

public class EntityDamage implements Listener {

	@EventHandler
	public void onED(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();

			if (Frozen.spectators.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
