package com.zapcloudstudios.frozen.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.zapcloudstudios.frozen.API;
import com.zapcloudstudios.frozen.Frozen;
import com.zapcloudstudios.frozen.utils.GameManager;

public class EntityDeath implements Listener {

    @SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.LOW, ignoreCancelled = false)
    public void onSnowballHit(EntityDamageByEntityEvent event)
    {
        Entity damaged = event.getEntity();
        Entity damageEntity = event.getDamager();
 
        if(damaged instanceof Player)
        if(damageEntity instanceof Snowball)
        {
            Snowball snowball = (Snowball)damageEntity;
            LivingEntity entityThrower = snowball.getShooter();
            if(entityThrower instanceof Player)
            {
                Player playerThrower = (Player)entityThrower;
                Player playerHit = (Player)damaged;
                
                API.addDeaths(playerHit, 1);
				API.addKills(playerThrower, 1);
				API.addPoints(playerThrower, 5);

				Frozen.scoreboard(playerThrower);
				Frozen.scoreboard(playerHit);
                
            }
        }
    }
	
//	@EventHandler
//	public void onDeath(EntityDeathEvent e) {
//		if (e.getEntity() instanceof Player) {
//			Player victim = (Player) e.getEntity();
//			if (victim.getKiller() instanceof Player) {
//				Player killer = (Player) victim.getKiller();
//
//				API.addDeaths(victim, 1);
//				API.addKills(killer, 1);
//				API.addPoints(killer, 5);
//
//				Frozen.scoreboard(killer);
//				Frozen.scoreboard(victim);
//
//				if (!Frozen.gameEnded) {
//					if (API.getKills(killer) >= 5) {
//						GameManager.endGame();
//					}
//				}
//			}
//		}
//	}
}
