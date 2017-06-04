import java.util.ArrayList;

/**
 *  Created by Orion Wolf_Hubbard on 6/2/2017.
 */

public class Player {
    ArrayList<String> books;
    ArrayList<Card> hand;
    String name;
    boolean turn;

    public Player(String name){
        hand = new ArrayList<>();
        books = new ArrayList<>();
        this.name = name;
        turn = false;
    }

    public boolean hasValue(Card card) {
        for (Card c : hand) {
            if (card.equals(c)) return true;
        }
        return false;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<String> getBooks() {
        return books;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public void removeCards(Card card) {
        //iterates through hand backwards to avoid index issues when cards are removed
        for (int i = hand.size() -1 ; i > -1; i--) {
            //if player has a match, remove it
            if (hand.get(i).equals(card)) hand.remove(i);
        }
    }

    //check if player has 4 of a kind
    public Object[] hasBook(){
        //for each possible value, checks if 4 cards of that value are in hand
        for (String value : Deck.values) {
            int counter = 0;
            Card card = new Card("fake suit", value);
            for (Card c : hand) if (c.equals(card)) counter++;

            //if there are 4 of that card value return true and the type of card
            if (counter == 4){
                Object[] obj = {true, card};
                return obj;
            }
        }
        Object[] obj = {false, new Card("","")};
        return obj;
    }

    //removes cards of one value and adds value to books
    public void addBook(Card card) {

        //adds the value to books
        this.books.add(card.getValue());

        //removes all cards of that value from hand
        for (int i = hand.size(); i > 0; i--) if (hand.get(i).equals(card)) hand.remove(i);



    }

    @Override
    public String toString() {
        return name;
    }

}
