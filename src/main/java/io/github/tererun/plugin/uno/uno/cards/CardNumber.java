package io.github.tererun.plugin.uno.uno.cards;

public enum CardNumber {
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    SKIP("SKIP"),
    RETURN("RETURN"),
    PLUS2("PLUS2"),
    PLUS4("PLUS4"),
    CHANGECOLOR("CHANGECOLOR");

    private String integer;

    CardNumber(String cardNumber) {
        this.integer = cardNumber;
    }

    public String getNumber() {
        return this.integer;
    }

}
