package com.zapcloudstudios.frozen.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;

import com.zapcloudstudios.frozen.Frozen;

public class CrystalManager {

	public static void loadCrystals(){
		ArrayList<Location> temp = new ArrayList<>();

		int amount = Frozen.config.getInt("Crystals.amount");

		World world = Bukkit.getWorld(Frozen.config.getString("Spawns.world"));

		for(int i = 1; i <= amount; i++){
			double x = Frozen.config.getDouble("Crystals." + i + ".x");
			double y = Frozen.config.getDouble("Crystals." + i + ".y");
			double z = Frozen.config.getDouble("Crystals." + i + ".z");

			temp.add(new Location(world, x, y, z));
		}

		Frozen.enderCrystals = temp;
	}

	public static void setCrystals(Player p){
		int amount = Frozen.config.getInt("Crystals.amount");

		int next = amount + 1;

		Frozen.config.set("Crystals.amount", next);

		Location loc = p.getLocation();

		double x = loc.getX();
		double y = (loc.getY() - 0.55 );
		double z = loc.getZ();

		Frozen.config.set("Crystals." + next + ".x", x);
		Frozen.config.set("Crystals." + next + ".y", y);
		Frozen.config.set("Crystals." + next + ".z", z);

		Frozen.plugin.saveConfig();
	}
	
	public static void spawnCrystals() {
		int amount = Frozen.config.getInt("Crystals.amount");
		World world = Bukkit.getWorld(Frozen.config.getString("Spawns.world"));
		for(int i = 1; i <= amount; i++) {
			world.spawn(Frozen.enderCrystals.get(i), EnderCrystal.class);
		}
	}
}
