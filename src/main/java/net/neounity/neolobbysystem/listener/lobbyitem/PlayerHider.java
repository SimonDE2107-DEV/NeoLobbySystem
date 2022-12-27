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

import java.util.ArrayList;

public class PlayerHider implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() != null && event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getType().equals(Material.LIME_DYE) || player.getItemInHand().getType().equals(Material.ORANGE_DYE) ||
                    player.getItemInHand().getType().equals(Material.RED_DYE)) {

                Inventory playerhider = Bukkit.createInventory(null, InventoryType.BREWING, "§6§lSichtbarkeit");


                Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                    playerhider.setItem(4, new ItemBuilder(Material.ENDER_EYE, 1, "§6§lSichtbarkeit").build());
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);

                    Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                        playerhider.setItem(0, new ItemBuilder(Material.LIME_DYE, 1, "§2§lA§alle §2§lS§apieler §2§la§anzeigen").build());
                        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 2);

                        Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                            playerhider.setItem(1, new ItemBuilder(Material.ORANGE_DYE, 1, "§6§lN§eur §6§lV§eIP's §6§la§enzeigen").build());
                            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 3);

                            Bukkit.getScheduler().scheduleSyncDelayedTask(NeoLobbySystem.plugin, () -> {
                                playerhider.setItem(2, new ItemBuilder(Material.RED_DYE, 1, "§4§lK§ceine §4§lS§cpieler §4§la§cnzeigen").build());
                                player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 4);

                            }, 2);

                        }, 2);

                    }, 2);

                }, 2);
                player.openInventory(playerhider);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != null) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§2§lA§alle §2§lS§apieler §2§la§anzeigen")) {
                    showAllPlayers(player);
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lN§eur §6§lV§eIP's §6§la§enzeigen")) {
                    showOnlyVIPPlayers(player);
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lK§ceine §4§lS§cpieler §4§la§cnzeigen")) {
                    showNonePlayers(player);
                }
            }
        }
    }

    public ArrayList<Player> showAll = new ArrayList<Player>();
    public ArrayList<Player> showOnlyVIP = new ArrayList<Player>();
    public ArrayList<Player> showNONE = new ArrayList<Player>();
    void showAllPlayers(Player player) {
        showAll.add(player);
        showOnlyVIP.remove(player);
        showNONE.remove(player);
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
        player.getInventory().setItem(2, new ItemBuilder(Material.LIME_DYE,1,"§2§lA§alle §2§lS§apieler §2§ls§aichtbar").build());
    }

    void showOnlyVIPPlayers(Player player) {
        showAll.remove(player);
        showOnlyVIP.add(player);
        showNONE.remove(player);
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
        player.getInventory().setItem(2, new ItemBuilder(Material.ORANGE_DYE,1,"§6§lN§eur §6§lV§eIP's §6§ls§eichtbar").build());
    }

    void showNonePlayers(Player player) {
        showAll.remove(player);
        showOnlyVIP.remove(player);
        showNONE.add(player);
        player.closeInventory();
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 3);
        player.getInventory().setItem(2, new ItemBuilder(Material.RED_DYE,1,"§4§lK§ceine §4§lS§cpieler §4§ls§cichtbar").build());
    }
}
