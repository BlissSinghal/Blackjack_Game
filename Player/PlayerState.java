package Player;
import Card.*;

import java.util.Set;

public abstract class PlayerState {
    private int score = 0;

    private Deck deck;
    private int aceCount;



    //want to return something that tells we should bust or something
    public Card hit() throws IllegalStateException {
        //generate a card
        Card newCard = deck.drawCard();
        //getting the card's value
        int cardVal = Math.min(newCard.getNumber(), 10);

        //updating our score
        score += cardVal;

        //checking to see if we have an ace bc it can also be 11
        if (cardVal == 1) {
            aceCount++;
        }
        return newCard;
    }

    //overloading this method when we want to give it a card
    public Card hit(Card card) {
        int cardVal = Math.min(card.getNumber(), 10);
        score += cardVal;
        if (cardVal == 1) {
            aceCount++;
        }
        return card;
    }

    public int calcScore() {
        //checking to see if aceCount > 0
        //only possible to have 1 ace whose val is 11 w/out busting
        if (aceCount > 0 && (score + 10) <= 21) {
            return score + 10;
        } else {
            return score;
        }
    }

    public void setDeck(Deck d) {
        deck = d;
    }

    public int getScore() {
        return score;
    }

    public boolean checkBust() {
        return score > 21;
    }

    public boolean isFullDeck() {
        return deck.isFullDeck();
    }

    public Set<Card> getDeckUsed() {
        return deck.getUsed();
    }


    //set methods
    public void setScore(int s) {
        score = s;
    }


}
