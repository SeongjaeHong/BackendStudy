package collection.test;

import java.util.*;

public class CardDeck {
    private static final int CAPACITY = 52;
    private static final List<Card> deck = new ArrayList<>(CAPACITY);
    private int currentIndex = 0;

    CardDeck() {
        for (Suit suit: Suit.values()){
            for (int rank=1; rank <= 13; rank++) {
                deck.add(deck.size(), new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card get() {
        if (currentIndex < CAPACITY) {
            return deck.get(currentIndex++);
        } else {
            return null;
        }
    }
}
