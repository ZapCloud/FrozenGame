package com.zapcloudstudios.frozen.events;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.ParticleUtils;

public class SnowballHit implements Listener {

@SuppressWarnings("deprecation")
@EventHandler
public void onSnowballHit(EntityDamageByEntityEvent event)
{
    Entity damaged = event.getEntity();
    Entity damageEntity = event.getDamager();

    if(damaged instanceof Player)
    if(damageEntity instanceof Snowball) {
       
    	Snowball snowball = (Snowball)damageEntity;
        LivingEntity entityThrower = snowball.getShooter();
        	
        	if(entityThrower instanceof Player) {
        		Player playerThrower = (Player)entityThrower;
        		Player playerHit = (Player)damaged;
            
        		for (Player pl : Bukkit.getOnlinePlayers()) {
        			try {
        				ParticleUtils.spawnParticles(playerHit.getLocation(), pl, "snowshovel", 80);
        			} catch (ClassNotFoundException | IllegalAccessException
        					| InstantiationException | NoSuchMethodException
        					| NoSuchFieldException | IllegalArgumentException
        					| InvocationTargetException e1) {
        				e1.printStackTrace();
        			}
            		Frozen.scoreboard(pl);
        		}
            
        		API.addPoints(playerThrower, 5);
        		API.setFrozenOne(playerHit, 5);
        		
        }
    }else {
    	event.setCancelled(true);
    }
}
}