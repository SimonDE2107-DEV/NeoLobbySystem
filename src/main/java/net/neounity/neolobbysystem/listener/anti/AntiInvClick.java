package net.neounity.neolobbysystem.listener.anti;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class AntiInvClick implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        event.setCancelled(!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE));
    }

    @EventHandler
    public void onSwapItem(PlayerSwapHandItemsEvent event) {
        event.setCancelled(!event.getPlayer().getGameMode().equals(GameMode.CREATIVE));
    }
}
