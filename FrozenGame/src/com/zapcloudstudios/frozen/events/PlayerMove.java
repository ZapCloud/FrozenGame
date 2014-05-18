package com.zapcloudstudios.frozen.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.ParticleUtils;

public class PlayerMove implements Listener {

	@EventHandler
	public static void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for(Entity ent : p.getNearbyEntities(2, 4, 2)) {
			if(ent instanceof EnderCrystal) {
				Bukkit.getScheduler().cancelAllTasks();
				Frozen.slowAmount = 0;
				API.startSlowTimer();

				for(Player pl : Bukkit.getOnlinePlayers()) {
				
					pl.sendMessage("§6The Beast Has Been §bThawed§6!");
        		
					try {
        				ParticleUtils.spawnParticles(ent.getLocation(), pl, "magicCrit", 80);
        			} catch (ClassNotFoundException | IllegalAccessException
        					| InstantiationException | NoSuchMethodException
        					| NoSuchFieldException | IllegalArgumentException
        					| InvocationTargetException e1) {
        				e1.printStackTrace(); } }
				
					ent.remove();
			}
		}
	}
}
