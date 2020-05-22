package io.github.tererun.plugin.uno.uno.cards;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Card {

    private CardNumber cardNumber;
    private CardColor cardColor;

    public Card(CardNumber cardNumber, CardColor cardColor) {
        this.cardNumber = cardNumber;
        this.cardColor = cardColor;
    }

    public ItemStack getCardItemStack() {
        ItemStack itemStack = new ItemStack(Material.PAPER, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(cardColor.getCardColor() + cardColor.getCardColorString() + " | " + cardNumber.getNumber());
        itemMeta.setLore(Arrays.asList("UNO Cards"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static boolean isUNOCards(ItemStack itemStack) {
        if (itemStack.getItemMeta() == null) return false;
        if (itemStack.getItemMeta().getLore().get(0).equalsIgnoreCase("UNO Cards")) {
            return true;
        } else {
            return false;
        }
    }

    public static Card getCard(ItemStack itemStack) {
        String str = itemStack.getItemMeta().getDisplayName();
        int index = str.indexOf(" | ");
        int index2 = str.indexOf(" | ");
        index += " | ".length();
        Card card = new Card(CardNumber.valueOf(str.substring(index)), CardColor.valueOf(str.substring(index2)));
        return card;
    }

    public static CardColor getCardColor(Card card) {
        return card.cardColor;
    }

    public static CardNumber getCardNumber(Card card) {
        return card.cardNumber;
    }
}
