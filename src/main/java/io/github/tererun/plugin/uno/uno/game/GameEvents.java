package io.github.tererun.plugin.uno.uno.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GameEvents implements Listener {
    @EventHandler
    public void onPlayerInteract(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        ItemStack itemStack = e.getCurrentItem();

    }
}
