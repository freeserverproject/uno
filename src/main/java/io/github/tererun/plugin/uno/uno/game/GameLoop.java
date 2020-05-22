package io.github.tererun.plugin.uno.uno.game;

import io.github.tererun.plugin.uno.uno.UNO;
import io.github.tererun.plugin.uno.uno.rooms.Room;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GameLoop extends BukkitRunnable {
    private Room room;
    private int timer; //10秒タイマー
    private int count; //何人目か
    private int savesize; //人数
    private List<Player> saveListPlayer; //人数のList版 比較用

    public GameLoop(Room room) {
        this.room = room;
        this.timer = 0;
        this.count = 0;
        this.savesize = UNO.roomPlayerList.get(room).size();
        this.saveListPlayer = UNO.roomPlayerList.get(room);
    }

    @Override
    public void run() {
        Player player = UNO.roomPlayerList.get(room).get(count);

        if (!saveListPlayer.equals(UNO.roomPlayerList.get(player))) {
            for (Player player1 : saveListPlayer) {
                if (UNO.roomPlayerList.containsKey(player1)) {
                    if (player1.equals(saveListPlayer.get(count))) {
                        count++;
                        player = UNO.roomPlayerList.get(room).get(count);
                        sendRoomPlayerMessage(room, UNO.quit + player1 + " が退出したため、スキップしました");
                    } else {
                        sendRoomPlayerMessage(room, UNO.quit + player1 + " が退出しました");
                    }
                    saveListPlayer = UNO.roomPlayerList.get(room);
                    savesize = UNO.roomPlayerList.get(room).size();
                }
            }
        }

        if (count == savesize - 1) {
            count = 0;
        }

        if (timer >= 9) {
            if (!UNO.playerPlacedCheck.get(player)) {
                //player
            }
            timer = 0;
            count++;
        }

        if (UNO.playerPlacedCheck.get(player)) {
            timer = 0;
            count++;
        }

        timer++;
    }

    public void sendRoomPlayerMessage(Room room, String...message) {
        for (Player player : room.getPlayers()) {
            player.sendMessage(message);
        }
    }
}
