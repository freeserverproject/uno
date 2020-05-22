package io.github.tererun.plugin.uno.uno.guis;

import io.github.tererun.plugin.uno.uno.UNO;
import io.github.tererun.plugin.uno.uno.game.GameLoop;
import io.github.tererun.plugin.uno.uno.rooms.Room;
import io.github.tererun.plugin.uno.uno.rooms.RoomSetting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateRoom implements Listener {
    public static HashMap<Player, String> roomName = new HashMap<>();
    public static HashMap<Player, Integer> roomID = new HashMap<>();
    public static HashMap<Player, RoomSetting> roomSettings = new HashMap<>();

    public static Inventory getMenuInventory(Player player) {
        if (UNO.playerJoiningRoom.containsKey(player)) {
            if (UNO.playerJoiningRoom.get(player).getOwner().equals(player)) {
                Inventory inventory = Bukkit.createInventory(null, 9, "メニュー");
                inventory.setItem(2, GUIAPI.getItemStack(Material.GREEN_WOOL, "ゲームを開始する"));
                inventory.setItem(6, GUIAPI.getItemStack(Material.RED_WOOL, "部屋を削除する", UNO.playerJoiningRoom.get(player).getRoomName() + " のオーナーです", "削除する場合はクリックしてください"));
                return inventory;
            } else {
                Inventory inventory = Bukkit.createInventory(null, 9, "メニュー");
                inventory.setItem(4, GUIAPI.getItemStack(Material.RED_WOOL, "部屋を退出する", UNO.playerJoiningRoom.get(player).getRoomName() + " に入っています", "退出する場合はクリックしてください"));
                return inventory;
            }
        } else {
            Inventory inventory = Bukkit.createInventory(null, 9, "メニュー");
            inventory.setItem(2, GUIAPI.getItemStack(Material.GREEN_WOOL, "部屋に参加する"));
            inventory.setItem(6, GUIAPI.getItemStack(Material.YELLOW_WOOL, "部屋を作成する"));
            return inventory;
        }
    }

    public static Inventory getSelectRoomInventory() {
        Inventory inventory = Bukkit.createInventory(null, 9*6, "部屋選択");
        if (!UNO.roomList.isEmpty()) {
            for (Room room : UNO.roomList) {
                inventory.addItem(GUIAPI.getItemStack(Material.OAK_DOOR, room.getRoomName(), room.getRoomID().toString()));
            }
        }
        return inventory;
    }

    public static Inventory getCreateRoomInventory(Player player) {
        if (!roomName.containsKey(player)) roomName.put(player, player.getName());
        if (!roomID.containsKey(player)) roomID.put(player, UNO.roomList.size() + 1);

        Inventory inventory = Bukkit.createInventory(null, 9, "部屋作成");
        inventory.setItem(0, GUIAPI.getItemStack(Material.NAME_TAG, "名前変更", roomName.get(player)));
        inventory.setItem(1, GUIAPI.getItemStack(Material.OAK_SIGN, "ID変更", roomID.get(player).toString()));
        inventory.setItem(2, GUIAPI.getItemStack(Material.ENCHANTING_TABLE, "設定変更", roomSettings.get(player).toString()));
        inventory.setItem(8, GUIAPI.getItemStack(Material.STRUCTURE_VOID, "作成"));
        return inventory;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;

        String displayName = e.getCurrentItem().getItemMeta().getDisplayName();
        String lore = e.getCurrentItem().getItemMeta().getLore().get(0);
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase("部屋作成")) {
            ItemStack clickedItem = e.getCurrentItem();
            if (checkItemStack(clickedItem, Material.NAME_TAG, "名前変更")) {

            } else if (checkItemStack(clickedItem, Material.OAK_SIGN, "ID変更")) {

            } else if (checkItemStack(clickedItem, Material.ENCHANTING_TABLE, "設定変更")) {
                RoomSetting roomSetting = new RoomSetting();
                roomSetting.setPrivateRoom(false);
                roomSettings.put(player, roomSetting);
            } else if (checkItemStack(clickedItem, Material.STRUCTURE_VOID, "作成")) {
                Room room = new Room(roomID.get(player), roomName.get(player), roomSettings.get(player), player);
                UNO.roomList.add(room);
                UNO.roomPlayerList.put(room, new ArrayList<>());
                UNO.roomPlayerList.get(room).add(player);
                UNO.playerJoiningRoom.put(player, room);
                player.sendMessage(UNO.success + room.getRoomName() + "(" + room.getRoomID() + ") を作成し参加しました!");
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase("部屋選択")) {
            if (!UNO.roomList.isEmpty()) {
                for (Room room : UNO.roomList) {
                    if ((displayName.equalsIgnoreCase(room.getRoomName())) && (lore.equalsIgnoreCase(String.valueOf(roomID)))) {
                        UNO.roomPlayerList.get(room).add(player);
                        UNO.playerJoiningRoom.put(player, room);
                        player.closeInventory();
                        player.sendMessage(UNO.success + room.getRoomName() + "(" + room.getRoomID() + ") に参加しました!");
                        for (Player player1 : UNO.roomPlayerList.get(room)) {
                            player1.sendMessage(UNO.success + player.getName() + " がゲームに参加しました!");
                        }
                    }
                }
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase("メニュー")) {
            ItemStack clickedItem = e.getCurrentItem();
            if (checkItemStack(clickedItem, Material.RED_WOOL, "部屋を退出する")) {
                Room room = UNO.playerJoiningRoom.get(player);
                UNO.roomPlayerList.get(room).remove(player);
                player.closeInventory();
                for (Player player1 : UNO.roomPlayerList.get(room)) {
                    player1.sendMessage(UNO.quit + player.getName() + " がゲームを退出しました");
                }
                player.sendMessage(UNO.success + room.getRoomName() + "(" + room.getRoomID() + ") を退出しました!");
                UNO.playerJoiningRoom.remove(player);
            } else if (checkItemStack(clickedItem, Material.GREEN_WOOL, "部屋に参加する")) {
                player.openInventory(getSelectRoomInventory());
            } else if (checkItemStack(clickedItem, Material.YELLOW_WOOL, "部屋を作成する")) {
                player.openInventory(getCreateRoomInventory(player));
            } else if (checkItemStack(clickedItem, Material.YELLOW_WOOL, "部屋を削除する")) {
                Room room = UNO.playerJoiningRoom.get(player);
                for (Player player1 : UNO.roomPlayerList.get(room)) {
                    if (UNO.playerJoiningRoom.get(player1).equals(room)) {
                        player1.sendMessage(UNO.failed + room.getRoomName() + "(" + room.getRoomID() + ") が閉じられたため、ゲームを退出しました");
                        UNO.playerJoiningRoom.remove(player1);
                    }
                }
                UNO.roomList.remove(room);
                UNO.roomPlayerList.remove(room);
                player.sendMessage(UNO.success + room.getRoomName() + "(" + room.getRoomID() + ") を閉じました!");
            } else if (checkItemStack(clickedItem, Material.YELLOW_WOOL, "ゲームを開始する")) {

            }
            e.setCancelled(true);
        }
    }


    public static boolean checkItemStack(ItemStack fromItemStack, Material material, String displayName) {
        if (fromItemStack == null) return false;
        if (fromItemStack.getItemMeta() == null) return false;
        Material fromMaterial = fromItemStack.getType();
        String fromDisplayName = fromItemStack.getItemMeta().getDisplayName();
        if ((fromMaterial.equals(material)) && (fromDisplayName.equalsIgnoreCase(displayName))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkItemStack(ItemStack fromItemStack, Material material, String displayName, String lore) {
        if (fromItemStack == null) return false;
        if (fromItemStack.getItemMeta() == null) return false;
        Material fromMaterial = fromItemStack.getType();
        String fromDisplayName = fromItemStack.getItemMeta().getDisplayName();
        String fromLore = fromItemStack.getItemMeta().getLore().get(0);
        if ((fromMaterial.equals(material)) && (fromDisplayName.equalsIgnoreCase(displayName)) && (fromLore.equalsIgnoreCase(lore))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkItemStack(ItemStack fromItemStack, Material material, String displayName, List<String> lore) {
        if (fromItemStack == null) return false;
        if (fromItemStack.getItemMeta() == null) return false;
        Material fromMaterial = fromItemStack.getType();
        String fromDisplayName = fromItemStack.getItemMeta().getDisplayName();
        List<String> fromLore = fromItemStack.getItemMeta().getLore();
        if ((fromMaterial.equals(material)) && (fromDisplayName.equalsIgnoreCase(displayName)) && (fromLore.equals(lore))) {
            return true;
        } else {
            return false;
        }
    }

}
