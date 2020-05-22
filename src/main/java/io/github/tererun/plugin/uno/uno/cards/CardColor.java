package io.github.tererun.plugin.uno.uno.cards;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public enum CardColor {
    RED(ChatColor.RED),
    BLUE(ChatColor.BLUE),
    YELLOW(ChatColor.YELLOW),
    GREEN(ChatColor.GREEN),
    OTHER(ChatColor.BLACK);

    private ChatColor color;

    CardColor(ChatColor color) {
        this.color = color;
    }

    public ChatColor getCardColor() {
        return this.color;
    }

    public String getCardColorString() {
        String string = "null";
        if (this.color.equals(ChatColor.RED)) {
            string = "RED";
        } else if (this.color.equals(ChatColor.BLUE)) {
            string = "BLUE";
        } else if (this.color.equals(ChatColor.YELLOW)) {
            string = "YELLOW";
        } else if (this.color.equals(ChatColor.GREEN)) {
            string = "GREEN";
        } else if (this.color.equals(ChatColor.BLACK)) {
            string = "BLACK";
        }
        return string;
    }

}
