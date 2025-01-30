package collection.test;

import java.util.ArrayList;
import java.util.List;

public class CardGameMain {
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        deck.shuffle();

        User user1 = new User();
        User user2 = new User();

        user1.getCards(deck);
        user2.getCards(deck);

        System.out.print("Player1's card: " + user1.getCardStatus() + ", Sum: " + user1.getSum() + "\n");
        System.out.print("Player2's card: " + user2.getCardStatus() + ", Sum: " + user2.getSum() + "\n");

        if (user1.getSum() > user2.getSum()) {
            System.out.println("Player1 won");
        } else if(user1.getSum() < user2.getSum()) {
            System.out.println("Player2 won");
        } else {
            System.out.println("Draw");
        }

    }
}
