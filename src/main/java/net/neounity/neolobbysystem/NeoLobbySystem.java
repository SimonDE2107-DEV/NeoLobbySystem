package net.neounity.neolobbysystem;

import net.neounity.neolobbysystem.command.setCommand;
import net.neounity.neolobbysystem.listener.JoinListener;
import net.neounity.neolobbysystem.listener.QuitListener;
import net.neounity.neolobbysystem.listener.anti.*;
import net.neounity.neolobbysystem.listener.feature.DoubleJump;
import net.neounity.neolobbysystem.listener.lobbyitem.Enterhaken;
import net.neounity.neolobbysystem.listener.lobbyitem.Gadgets;
import net.neounity.neolobbysystem.listener.lobbyitem.Navigator;
import net.neounity.neolobbysystem.listener.lobbyitem.PlayerHider;
import net.neounity.neolobbysystem.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class NeoLobbySystem extends JavaPlugin {

    public static NeoLobbySystem plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerCommand();
        registerListener(
                new AntiBadWeather(), new AntiBuild(), new AntiDrop(), new AntiEntDamage(), new AntiFallToVoid(),
                new AntiHunger(), new AntiInteract(), new AntiInvClick(), new AntiMobSpawn(), new DoubleJump(),
                new Enterhaken(), new Gadgets(), new Navigator(), new PlayerHider(), new JoinListener(),
                new QuitListener());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    void registerCommand() {
        getCommand("set").setExecutor(new setCommand());
    }

    void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
