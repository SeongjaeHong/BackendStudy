package collection.test;

public enum Suit {
    SPADE("♠️", 1),
    HEART("❤️", 2),
    DIAMOND("♦️", 3),
    CLUB("♣️", 4);

    private String icon;
    private int rank;

    Suit(String icon, int rank) {
        this.icon = icon;
        this.rank = rank;
    }

    public String getIcon() {
        return icon;
    }

    public int getRank() {
        return rank;
    }
}