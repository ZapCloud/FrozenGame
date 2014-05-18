package com.zapcloudstudios.frozen.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.ParticleUtils;

public class PlayerMove implements Listener {

	@EventHandler
	public static void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		for(Entity ent : p.getNearbyEntities(1, 3, 1)) {
			if(ent instanceof EnderCrystal) {
				Bukkit.getScheduler().cancelAllTasks();
				Frozen.slowAmount = 0;
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 0, 0), true);
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
