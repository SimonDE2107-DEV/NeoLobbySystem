package net.neounity.neolobbysystem.listener.feature;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJump implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    player.setAllowFlight(true);
    player.setFlying(false);
  }
  
  @EventHandler
  public void onFly(PlayerToggleFlightEvent event) {
    Player player = event.getPlayer();
    if (!player.getGameMode().equals(GameMode.CREATIVE) && 
      !player.getGameMode().equals(GameMode.SPECTATOR)) {
      event.setCancelled(true);
      player.setAllowFlight(false);
      player.setFlying(false);
      player.setVelocity(player.getLocation().getDirection().multiply(1.3).add(new Vector(0.0D, 0.8D, 0.0D)));
      event.getPlayer().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.0F);
      event.getPlayer().playEffect(player.getLocation(), Effect.SMOKE, 10);
    } 
  }
  
  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    if (!player.getGameMode().equals(GameMode.CREATIVE) && 
      !player.getGameMode().equals(GameMode.SPECTATOR) && 
      player.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType() != Material.AIR) {
      player.setAllowFlight(true);
      player.setFlying(false);
    } 
  }
}
