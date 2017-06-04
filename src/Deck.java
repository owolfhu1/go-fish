import java.util.ArrayList;
import java.util.Collections;

/**
 *   Created by Orion Wolf-Hubbard on 6/2/2017.
 */

public class Deck {
    private ArrayList<Card> cards;

    String[] suits = {
        "hearts", "spades", "diamonds", "clubs"
    };
    public static final String[] values = {
            "ace","two","three","four","five",
            "six","seven","eight","nine","ten",
            "jack","queen","king"
    };

    public Deck() {
        cards = new ArrayList<>();

        //builds a deck of cards using the suits and values array
        for (int s = 0; s < suits.length; s++) {
            for (int v = 0; v < values.length; v++) {
                cards.add(new Card(suits[s], values[v]));
            }
        }

        //shuffles the deck
        Collections.shuffle(cards);

    }

    public Card draw() {
        return cards.remove(0);
    }

    @Override
    public String toString() {
        return String.format("%d cards are left in the deck", cards.size());
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
