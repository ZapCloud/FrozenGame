package com.zapcloudstudios.frozen.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import com.zapcloudstudios.frozen.Frozen;

public class LobbyManager {

	public static Location lobby;
	
	public static void teleportToLobby(Player p) {
	p.teleport(lobby);
	}
	
	public static void loadLobbySpawn() {
	
		World world = Bukkit.getWorld(Frozen.config.getString("Lobby.world"));
		
		double x = Frozen.config.getDouble("Lobby.x");
		double y = Frozen.config.getDouble("Lobby.y");
		double z = Frozen.config.getDouble("Lobby.z");	
	
		lobby = new Location(world,x,y,z);
	}

	public static void setLobbySpawn(Player p) {
		Location loc = p.getLocation();

		World world = p.getWorld();
		
		Frozen.config.set("Lobby.world", world);
		
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();

		Frozen.config.set("Lobby.x", x);
		Frozen.config.set("Lobby.y", y);
		Frozen.config.set("Lobby.z", z);

		Frozen.plugin.saveConfig();
	}
	
}
