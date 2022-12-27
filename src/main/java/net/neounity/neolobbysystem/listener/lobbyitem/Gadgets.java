package net.neounity.neolobbysystem.listener.lobbyitem;

import net.neounity.neolobbysystem.NeoLobbySystem;
import net.neounity.neolobbysystem.util.ItemBuilder;
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

public class Gadgets implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != null && event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.CHEST)) {
                Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§6§lG§eadgets");


                Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                    inventory.setItem(4, new ItemBuilder(Material.CHEST, 1, "§6§lG§eadgets").build());
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                        inventory.setItem(0, new ItemBuilder(Material.FISHING_ROD, 1, "§6§lE§enterhaken").build());
                        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 2);

               //         Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                   //         inventory.setItem(1, new ItemBuilder(Material.BARRIER, 1, "NIX").build());
                     //       player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                       //     player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 3);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                                inventory.setItem(2, new ItemBuilder(Material.BARRIER, 1, "§6§lG§eadget §4§le§cntfernen").build());
                                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 4);

                            }, 2);

                 //       }, 2);

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
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lE§enterhaken")) {
                    player.getInventory().setItem(4, new ItemBuilder(Material.FISHING_ROD, 1, "§6§lE§enterhaken").unbreakable().build());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
             //   } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("BALD VERFÜFBAR")) {
               //     player.getInventory().setItem(4, new ItemBuilder(Material.BARRIER, 1, "BALD VERFÜFBAR").build());
                 //   player.closeInventory();
                 //   player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lG§eadget §4§le§cntfernen")) {
                    player.getInventory().setItem(4, new ItemBuilder(Material.BARRIER, 1, "§4§lK§cein §4§lG§cadget §4§la§cusgewählt").build());
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
                }
            }
        }
    }
}
