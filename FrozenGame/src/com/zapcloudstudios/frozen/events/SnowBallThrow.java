package com.zapcloudstudios.frozen.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import com.zapcloudstudios.frozen.Frozen;

public class SnowBallThrow implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
		if(Frozen.gameStarted) {
		if(!(Frozen.frozenOne == null)) {
		if(event.getEntity().getShooter() instanceof Player) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Frozen.plugin, new Runnable(){
				public void run() {
					ItemStack snowball = new ItemStack(Material.SNOW_BALL);
					Frozen.frozenOne.getInventory().addItem(snowball);
					}
				}, 10);
			}
		}
	}
}
}