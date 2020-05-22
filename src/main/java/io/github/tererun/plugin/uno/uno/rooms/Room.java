package io.github.tererun.plugin.uno.uno.rooms;

import io.github.tererun.plugin.uno.uno.UNO;
import org.bukkit.entity.Player;

import java.util.List;

public class Room {
    private Integer roomID;
    private String roomName;
    private RoomSetting setting;
    private Player owner;

    public Room(Integer roomID, String roomName, RoomSetting setting, Player owner) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.setting = setting;
        this.owner = owner;
    }

    public Integer getRoomID() {
        return this.roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public RoomSetting getRoomSetting() {
        return this.setting;
    }

    public Player getOwner() {
        return this.owner;
    }

    public List<Player> getPlayers() {
        return UNO.roomPlayerList.get(this);
    }

}
