/**
 *  Created by Orion Wolf_Hubbard on 6/2/2017.
 */

public class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return String.format("%s of %s", value, suit);
    }

    @Override
    public boolean equals(Object obj) {
        Card card = (Card)obj;
        return this.value.equals(card.getValue());
    }
}
