package net.neounity.neolobbysystem.command;

import net.neounity.neolobbysystem.util.Data;
import net.neounity.neowarpapi.NeoWarpAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Data.ONLY_INGAME);
            return true;
        }
        Player player = (Player) sender;

        if (player.hasPermission("lobbysystem.set")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("spawn")) {
                    NeoWarpAPI.createWarp(player,"SPAWN");
                } else {
                    sender.sendMessage(Data.USAGE + "/set [SPAWN]");
                }
            } else {
                sender.sendMessage(Data.USAGE + "/set [SPAWN]");
            }
        } else {
            player.sendMessage(Data.NO_PERMISSIONS + "lobbysystem.set");
        }
        return true;
    }
}
