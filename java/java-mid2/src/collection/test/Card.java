package collection.test;

public class Card implements Comparable<Card>{

    @Override
    public int compareTo(Card o) {
        if (this.rank > o.rank) {
            return 1;
        } else if (this.rank < o.rank) {
            return -1;
        } else {
            return Integer.compare(this.suit.getRank(), o.suit.getRank());
        }
    }

    private final Suit suit;
    private final int rank;

    public Card(Suit suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + "(" + suit.getIcon() + ")";
    }
}
