package net.neounity.neolobbysystem.listener;

import net.neounity.neolobbysystem.listener.lobbyitem.PlayerHider;
import net.neounity.neolobbysystem.util.ItemBuilder;
import net.neounity.neowarpapi.NeoWarpAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class JoinListener implements Listener {

    public void applyGameRules() {
        for (World lobbyWorld : Bukkit.getWorlds()) {
            lobbyWorld.setThundering(false);
            lobbyWorld.setTime(6000);
            lobbyWorld.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            lobbyWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            lobbyWorld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        }
    }


    ItemStack profile(Player player) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(player.getName());
        skullMeta.setDisplayName("§2§lD§aein §2§lP§arofil");
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerHider playerHider = new PlayerHider();
        applyGameRules();
        event.setJoinMessage(null);
        player.setGameMode(GameMode.SURVIVAL);
        player.setLevel(0);
        player.setExp(0);
        player.setMaxHealth(6);
        player.setHealth(6);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        playerHider.showAll.add(player);
        player.getInventory().setItem(1, new ItemBuilder(Material.ENDER_PEARL, 1, "§1§lN§9avigator").build());
        player.getInventory().setItem(2, new ItemBuilder(Material.LIME_DYE, 1, "§2§lA§alle §2§lS§apieler §2§ls§aichtbar").build());
        player.getInventory().setItem(4, new ItemBuilder(Material.BARRIER, 1, "§4§lK§cein §4§lG§cadget §4§la§cusgewählt").build());
        player.getInventory().setItem(6, new ItemBuilder(Material.CHEST, 1, "§6§lG§eadgets").build());
        player.getInventory().setItem(7, profile(player));

        NeoWarpAPI.teleportToWarp(player, "SPAWN", true);

    }
}
