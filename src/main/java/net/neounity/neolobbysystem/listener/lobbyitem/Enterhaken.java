package net.neounity.neolobbysystem.listener.lobbyitem;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class Enterhaken implements Listener {


    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        FishHook fishHook = event.getHook();
        if ((event.getState().equals(PlayerFishEvent.State.IN_GROUND) || event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)) && Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(fishHook.getLocation().getBlockX(), fishHook.getLocation().getBlockY() - 1, fishHook.getLocation().getBlockZ()).getType() != Material.AIR && Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(fishHook.getLocation().getBlockX(), fishHook.getLocation().getBlockY() - 1, fishHook.getLocation().getBlockZ()).getType() != Material.LEGACY_STATIONARY_WATER) {
            Location lc = player.getLocation();
            Location to = event.getHook().getLocation();
            lc.setY(lc.getY() + 0.8D);
            player.teleport(lc);
            double g = -0.08D;
            double t = to.distance(lc), d = t;
            double v_x = (1.0D + 0.07D * t) * (to.getX() - lc.getX()) / t;
            double v_y = (1.0D + 0.03D * t) * (to.getY() - lc.getY()) / t - -0.04D * t;
            double v_z = (1.0D + 0.07D * t) * (to.getZ() - lc.getZ()) / t;
            Vector velocity = player.getVelocity();
            velocity.setX(v_x);
            velocity.setY(v_y);
            velocity.setZ(v_z);
            player.setVelocity(velocity);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }
    }
}
