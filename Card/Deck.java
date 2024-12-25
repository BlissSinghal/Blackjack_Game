package Card;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Deck {
    private Set<Card> used;
    private final String[] suites = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private final int cardValLimit = 13;
    private boolean fullDeck = false;

    public Deck() {
        used = new TreeSet<>();
    }

    public void addCard(Card card) {
        //need to check that its a valid card
        if (isValidCard(card)) {
            used.add(card);
        } else {
            throw new IllegalArgumentException("Invalid Card");
        }
    }

    public boolean isValidCard(Card card) {
        //checking to make sure that the card's number between 1-13
        if (card.getNumber() < 1 || card.getNumber() > cardValLimit) {
            return false;
        }
        for (String validSuite: suites) {
            if (card.getSuite().equals(validSuite)) {
                return true;
            }
        }
        return false;
    }


    private Card genRandomCard() {
        //gen random # between 1 and 13
        Random rand = new Random();
        int number = rand.nextInt(cardValLimit) + 1;

        //generate a random suite
        int suiteIndex = rand.nextInt(suites.length);
        String suite = suites[suiteIndex];

        // adding card to list of used
        return new Card(number, suite);
    }

    public Card drawCard() throws IllegalStateException {
        //check if the deck is completely used
        if (used.size() >= 52) {
            fullDeck = true;
            throw new IllegalStateException("Deck is Full");
        }
        //want to gen card that is not already being used
        Card card = genRandomCard();
        while (used.contains(card)) {
            card = genRandomCard();
        }
        //adding it to our list of used
        try {
            addCard(card);
        } catch (IllegalArgumentException e) {
            System.out.println("Generated an invalid card, retrying");
            return drawCard();
        }
        return card;
    }

    //accessor methods
    public Set<Card> getUsed() {
        return new TreeSet<>(used);
    }

    public boolean isFullDeck() {
        return fullDeck;
    }



}
