package com.zapcloudstudios.frozen.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {

	@EventHandler
	public void onED(EntityDamageEvent e) {
		e.setCancelled(true);
	}
}
