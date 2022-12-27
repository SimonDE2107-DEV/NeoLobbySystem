package net.neounity.neolobbysystem.listener.anti;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class AntiMobSpawn implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        event.setCancelled(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CHUNK_GEN || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL);
    }
}
