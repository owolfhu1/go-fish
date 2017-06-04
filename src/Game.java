import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 *  Created by Orion Wolf_Hubbard on 6/2/2017.
 */

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private int turn;

    //CONSTRUCTOR
    public Game(ArrayList<Player> players) {
        this.players = players;

        //create a new deck of cards
        deck = new Deck();

        //deal 5 cards to each player
        for (int i =0; i < players.size(); i++) {
            for (int x = 0; i < 5; x++) this.players.get(i).addCard(deck.draw());
        }

        //assign random player to first turn
        Random random = new Random();
        turn = random.nextInt(players.size());
        players.get(turn).turn = true;
    }

    //flips turn boolean off for current player and on for next player in order (0 -> 1 -> 2 -> 3 -> 0 -> 1-> 2...)
    public void nextPlayer() {
        players.get(turn).turn = false;
        if (turn == players.size() -1) turn = 0;
        else turn++;
        players.get(turn).turn = true;
        sendAnnouncement("It is now %s's turn", players.get(turn));
    }

    //takes index of player requesting a card, index of player being requested, and a card to request.
    public void requestCard(int requester, int requested, Card card) {
        boolean goAgain = false;

        //tells all players what is happening
        sendAnnouncement("%s is requesting a %s from %s.", players.get(requester), card.getValue(), players.get(requested));

        //check to see if requested has card
        if (players.get(requested).hasValue(card)) {
            goAgain = true;

            //give the cards to requester
            giveCards(requested, requester, card);

            //checks for books
            while ((Boolean)players.get(requester).hasBook()[0]) {
                Card c = (Card)players.get(requester).hasBook()[1];
                players.get(requester).addBook(c);
                sendAnnouncement("%s reveals 4 %s's, puts them down and draws 4 more cards", players.get(requester), c.getValue());
                //draws 4 cards
                for (int i = 0; i < 4; i++) if (!deck.isEmpty()) players.get(requester).addCard(deck.draw());
            }
        }
        else {
            //if the requested doesn't have card, requester draws a card.
            if (!deck.isEmpty()) {
                Card temp = deck.draw();
                players.get(requester).addCard(temp);

                //inform players what is happening
                sendAnnouncement("%s says, 'no %s here, Go fish!', %s draws a card and %s", players.get(requested), card.getValue(), players.get(requester), deck);

                //if the card is what they asked for, announce and flip goAgain boolean
                if (card.equals(temp)) {
                    sendAnnouncement("%s says, 'I fished a %s!' and goes again");
                    goAgain = true;
                }
            }
            //unless there are no cards left
            else sendAnnouncement("%s says, 'no %s here, Go fish!', but there are no cards left in the deck", players.get(requested), card.getValue());

            //checks and acts if player has a book to put down
            while ((Boolean)players.get(requester).hasBook()[0]) {
                Card c = (Card)players.get(requester).hasBook()[1];
                players.get(requester).addBook(c);
                sendAnnouncement("%s reveals 4 %s's, puts them down and draws 4 more cards", players.get(requester), c.getValue());

                //draws 4 cards
                for (int i = 0; i < 4; i++) if (!deck.isEmpty()) players.get(requester).addCard(deck.draw());
            }
        }

        //if go again boolean is false, next player is set to true
        if (!goAgain) nextPlayer();
    }

    //used to pass cards from one player to another
    private void giveCards(int requested, int requester, Card card) {
        for (Card c : players.get(requested).getHand()) {
            if (c.equals(card)) {
                //tell players a card is being transferred
                sendAnnouncement("%s gives %s to %s", players.get(requested), c, players.get(requester));

                //adds card to requester's hand
                players.get(requester).addCard(c);
            }
        }
        //removes all transferred cards from requested's hand
        players.get(requested).removeCards(card);
    }

    public int getTurn() {
        return turn;
    }

    //checks if the game is over (when all 13 books have been won)
    public boolean isOver() {
        int total = 0;
        for (Player player: players){
            total += player.getBooks().size();
        }
        return total == 13;
    }

    //sends announcements to players
    public void sendAnnouncement(String format, Object... args) {
        String message = String.format(format, args);

        /*
            TODO write this method
        */

    }

}
