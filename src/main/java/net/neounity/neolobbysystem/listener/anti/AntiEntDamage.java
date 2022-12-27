package net.neounity.neolobbysystem.listener.anti;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class AntiEntDamage implements Listener {

    @EventHandler
    public void onEntDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            event.setCancelled(!player.getGameMode().equals(GameMode.CREATIVE));
        }
    }
}
