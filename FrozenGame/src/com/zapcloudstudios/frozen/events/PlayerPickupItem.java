package com.zapcloudstudios.frozen.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.zapcloudstudios.frozen.Frozen;

public class PlayerPickupItem implements Listener {

	@EventHandler
	public void onDrop(PlayerPickupItemEvent e) {
		Player p = e.getPlayer();

		if (Frozen.spectators.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
}
