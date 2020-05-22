package io.github.tererun.plugin.uno.uno;

import io.github.tererun.plugin.uno.uno.rooms.Room;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class UNO extends JavaPlugin {

    public static List<Room> roomList = new ArrayList<>();
    public static HashMap<Room, List<Player>> roomPlayerList = new HashMap<>();
    public static HashMap<Player, Room> playerJoiningRoom = new HashMap<>();
    public static HashMap<Player, Boolean> playerPlacedCheck = new HashMap<>();
    public static FileConfiguration config;

    public static String success = ChatColor.WHITE + "[" + ChatColor.GOLD + "UNO" + ChatColor.WHITE + "]: " + ChatColor.GREEN;
    public static String failed = ChatColor.WHITE + "[" + ChatColor.GOLD + "UNO" + ChatColor.WHITE + "]: " + ChatColor.DARK_RED;
    public static String quit = ChatColor.WHITE + "[" + ChatColor.GOLD + "UNO" + ChatColor.WHITE + "]: " + ChatColor.YELLOW;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();
    }

}
