package io.github.tererun.plugin.uno.uno.commands;

import io.github.tererun.plugin.uno.uno.guis.CreateRoom;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("uno")) {
            Player player = (Player) sender;
            player.openInventory(CreateRoom.getMenuInventory(player));
        }
        return false;
    }
}
