package collection.test;

public enum Suit {
    SPADE("♠️"),
    HEART("❤️"),
    DIAMOND("♦️"),
    CLUB("♣️");

    private String icon;
    private int rank;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}