package com.zapcloudstudios.frozen;


import java.util.Random;

import net.minecraft.server.v1_7_R3.ChatSerializer;
import net.minecraft.server.v1_7_R3.IChatBaseComponent;
import net.minecraft.server.v1_7_R3.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.zapcloudstudios.frozen.utils.GameManager;


public class API {
	
	public static void setSpectator(Player p) {

		Frozen.players.remove(p.getName());
		Frozen.totalAlive--;
		
		if(Frozen.totalAlive <= 1){
			GameManager.endGame();
		}else {
			
		p.setAllowFlight(true);
		p.setFlying(true);

		for (Player pl : Bukkit.getOnlinePlayers()) {
			pl.hidePlayer(p);
		}

		p.sendMessage("�aYou are now a spectator!");
	}

}
	public static void setFrozenOne(Player p, int blindTime) {
		Bukkit.getScheduler().cancelAllTasks();
		Frozen.frozenOne = p;
		Frozen.frozenOne.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (blindTime * 20), 255));
		Frozen.slowAmount = 0;
		Bukkit.getScheduler().scheduleSyncDelayedTask(Frozen.plugin, new Runnable(){
			public void run() {
				Frozen.frozenOne.getInventory().clear();
				ItemStack snowball = new ItemStack(Material.SNOW_BALL);
				Frozen.frozenOne.getInventory().addItem(snowball);
			}
		}, 10);
		startSlowTimer();
	}
	
	public static void startSlowTimer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Frozen.plugin, new Runnable(){
			public void run() {
				if(Frozen.slowAmount < 5){
					Frozen.frozenOne.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Frozen.slowAmount), true);
					Frozen.slowAmount++;
					}else{
						for(Player p : Bukkit.getOnlinePlayers()){
							p.sendMessage("�b" + Frozen.frozenOne.getDisplayName() + "�4 Has Frozen To Death!");
						}
						API.setSpectator(Frozen.frozenOne);
						if(Frozen.totalAlive > 2) {
							int random = new Random().nextInt(Frozen.players.size());
							String player = Frozen.players.get(random);
							@SuppressWarnings("deprecation")
							Player p = Bukkit.getPlayer(player);
							API.setFrozenOne(p, 5);
					}
				}
			}	
	}, 500, 500);
}
	
	
	
	public static void addPoints(Player p, int i) {
		Frozen.points.put(p.getName(), Frozen.points.get(p.getName()) + i);
	}

	public static void removePoints(Player p, int i) {
		Frozen.points.put(p.getName(), Frozen.points.get(p.getName()) - i);
	}

	public static int getPoints(Player p) {
		return Frozen.points.get(p.getName());
	}
	
	public static int getAlive() {
		return Frozen.totalAlive;
	}

	public static void firework(Player p) {
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(),
				EntityType.FIREWORK);
		FireworkMeta fwmeta = fw.getFireworkMeta();
		FireworkEffect.Builder builder = FireworkEffect.builder();
		builder.withTrail().withFlicker().withFade(Color.BLUE).withColor(Color.WHITE).withColor(Color.BLUE).with(FireworkEffect.Type.BALL_LARGE);
		fwmeta.addEffect(builder.build());
		fwmeta.setPower(1);
		fw.setFireworkMeta(fwmeta);
	}

	public static void json(Player player, String message, String special, String action, String result, String event, String action2, String fin) {
		IChatBaseComponent comp = ChatSerializer
				.a("{\"text\":\" + " + message + " \",\"extra\":[{\"text\":\" " + special + "\",\"hoverEvent\":{\"action\":\" " + action + "\",\"value\":\" " + result + "\"},\" " + event + "\":{\"action\":\" " + action2 + "\",\"value\":\" " + fin + "}}]}");
		PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
	}
}
