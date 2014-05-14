package com.zapcloudstudios.frozen.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.zapcloudstudios.frozen.Frozen;

public class SpawnHandler {

	public static void teleportToArena() {

		int counter = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(Frozen.arenaSpawns.get(counter++));
		}
	}

	public static void loadSpawns(String arenaName){
		ArrayList<Location> temp = new ArrayList<>();

		int amount = Frozen.config.getInt("Spawns." + arenaName + ".amount");

		World world = Bukkit.getWorld(Frozen.config.getString("Spawns." + arenaName + ".world"));

		for(int i = 1; i <= amount; i++){
			double x = Frozen.config.getDouble("Spawns." + arenaName + "." + i + ".x");
			double y = Frozen.config.getDouble("Spawns." + arenaName + "." + i + ".y");
			double z = Frozen.config.getDouble("Spawns." + arenaName + "." + i + ".z");

			temp.add(new Location(world, x, y, z));
		}

		Frozen.arenaSpawns = temp;
	}

	public static void setSpawn(Player p, String arenaName){
		int amount = Frozen.config.getInt("Spawns." + arenaName + ".amount");

		int next = amount + 1;

		World world = p.getWorld();

		Frozen.config.set("Spawns." + arenaName + "amount", next);
		Frozen.config.set("Spawns." + arenaName + "." + next + ".world", world);

		Location loc = p.getLocation();

		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		Frozen.config.set("Spawns." + arenaName + "." + next + ".x", x);
		Frozen.config.set("Spawns." + arenaName + "." + next + ".y", y);
		Frozen.config.set("Spawns." + arenaName + "." + next + ".z", z);

		Frozen.plugin.saveConfig();
	}
}
