package net.neounity.neolobbysystem.listener.anti;

import net.neounity.neowarpapi.NeoWarpAPI;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AntiFallToVoid implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getLocation().getY() <= 0) {
            if(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                NeoWarpAPI.teleportToWarp(player, "SPAWN", true);
            }
        }
    }
}
