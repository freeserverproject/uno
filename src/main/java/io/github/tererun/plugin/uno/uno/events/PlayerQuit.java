package io.github.tererun.plugin.uno.uno.events;

import io.github.tererun.plugin.uno.uno.UNO;
import io.github.tererun.plugin.uno.uno.rooms.Room;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (UNO.playerJoiningRoom.containsKey(e.getPlayer())) {
            Room room = UNO.playerJoiningRoom.get(e.getPlayer());
            UNO.roomPlayerList.get(room).remove(e.getPlayer());
            UNO.playerJoiningRoom.remove(e.getPlayer());
            for (Player player : UNO.roomPlayerList.get(room)) {
                player.sendMessage(UNO.quit + e.getPlayer().getName() + " がサーバーを退出したため、部屋から退出しました");
            }
        }
    }
}
