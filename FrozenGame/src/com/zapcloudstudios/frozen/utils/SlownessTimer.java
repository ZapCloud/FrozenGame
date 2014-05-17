package com.zapcloudstudios.frozen.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;

public class SlownessTimer extends BukkitRunnable {

	@SuppressWarnings("deprecation")
	public void run() {
		if(Frozen.slowAmount < 7){
		Frozen.frozenOne.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Frozen.slowAmount), true);
		Frozen.slowAmount++;
		}else{
			for(Player p : Bukkit.getOnlinePlayers()){
				p.sendMessage("§b" + Frozen.frozenOne + "§4 Has Frozen To Death!");
			}
			API.setSpectator(Frozen.frozenOne);
			int random = new Random().nextInt(Frozen.players.size());
			String player = Frozen.players.get(random);
			Player p = Bukkit.getPlayer(player);
			API.setFrozenOne(p, 5);
		}
	}
	
}
