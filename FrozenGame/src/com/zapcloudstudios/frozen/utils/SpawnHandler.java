package com.zapcloudstudios.frozen.utils;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.zapcloudstudios.frozen.Frozen;


public class SpawnHandler {
	
	static Random randomGenerator;
	
	public static void spawnPlayerRandom(Player p) {
		
		randomGenerator = new Random();
		int number = randomGenerator.nextInt(Frozen.arenaSpawns.size());
		
		p.teleport(Frozen.arenaSpawns.get(number));
	}

	public static void loadSpawns(){
		ArrayList<Location> temp = new ArrayList<>();

		int amount = Frozen.config.getInt("Spawns." + "amount");

		World world = Bukkit.getWorld(Frozen.config.getString("Spawns." + "world"));

		for(int i = 1; i <= amount; i++){
			double x = Frozen.config.getDouble("Spawns." + i + ".x");
			double y = Frozen.config.getDouble("Spawns." + i + ".y");
			double z = Frozen.config.getDouble("Spawns." + i + ".z");

			temp.add(new Location(world, x, y, z));
		}

		Frozen.arenaSpawns = temp;
	}

	public static void setSpawn(Player p){
		int amount = Frozen.config.getInt("Spawns." + "amount");

		int next = amount + 1;

		World world = p.getWorld();

		Frozen.config.set("Spawns." + "amount", next);
		Frozen.config.set("Spawns." + next + ".world", world);

		Location loc = p.getLocation();

		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		Frozen.config.set("Spawns." + next + ".x", x);
		Frozen.config.set("Spawns." + next + ".y", y);
		Frozen.config.set("Spawns." + next + ".z", z);

		Frozen.plugin.saveConfig();
	}
}
