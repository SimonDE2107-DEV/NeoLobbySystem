package net.neounity.neolobbysystem.listener.lobbyitem;

import net.neounity.neolobbysystem.NeoLobbySystem;
import net.neounity.neolobbysystem.util.GameProfileBuilder;
import net.neounity.neolobbysystem.util.ItemBuilder;
import net.neounity.neowarpapi.NeoWarpAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Navigator implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        GameProfileBuilder gameProfileBuilder = new GameProfileBuilder();

        if (event.getAction() != null && event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.ENDER_PEARL)) {


                Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§1§lN§9avigator");

                Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                    inventory.setItem(4, new ItemBuilder(Material.ENDER_PEARL, 1, "§1§lN§9avigator").build());
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                        inventory.setItem(3, new ItemBuilder(Material.NETHER_STAR, 1, "§b§lSpawn").build());
                        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                            inventory.setItem(0, gameProfileBuilder.getSkullByTextureURL("e6959058c0c05a417fd757cb85b4415d966f2733d2e7ca54f7ba868e324909e2", "§4NIX"));
                            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 2);

                            //           Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                            // inventory.setItem(1, gameProfileBuilder.getSkullByTextureURL("e6959058c0c05a417fd757cb85b4415d966f2733d2e7ca54f7ba868e324909e2", "§4NIX"));
                            //  player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                            //    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 3);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                                inventory.setItem(2, gameProfileBuilder.getSkullByTextureURL("e6959058c0c05a417fd757cb85b4415d966f2733d2e7ca54f7ba868e324909e2", "§4NIX"));
                                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 4);

                            }, 2);

                            //  }, 2);
                        }, 2);

                    }, 2);

                }, 2);
                player.openInventory(inventory);

            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lSpawn")) {
                    NeoWarpAPI.teleportToWarp(player, "SPAWN", true);
                }
            }
        }
    }
}
