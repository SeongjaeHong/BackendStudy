package collection.test;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final static int INIT_CAPACITY = 5;
    List<Card> cards = new ArrayList<>(INIT_CAPACITY);

    public void getCards(CardDeck deck) {
        for (int i=0; i<INIT_CAPACITY; i++) {
            Card card = deck.get();
            if (card != null) {
                cards.add(deck.get());
            } else {
                System.out.println("Deck is run out.");
                break;
            }
        }

        sortCards();
    }

    private void sortCards() {
        cards.sort(null);
    }

    public String getCardStatus() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<cards.size(); i++) {
            sb.append(cards.get(i).toString());
            if (i < cards.size()-1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public int getSum() {
        int sum = 0;
        for (Card card: cards) {
            sum += card.getRank();
        }
        return sum;
    }
}
