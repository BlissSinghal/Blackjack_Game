import Card.*;
import CurrGame.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;


import static org.junit.jupiter.api.Assertions.*;


public class CardStateTest {
    private CurrentGame currentGame;
    private Deck deck;

    @BeforeEach
    public void setUp() {
        currentGame = new CurrentGame();
        deck = new Deck();

    }

    @Test
    public void testAceVal() {
        Card card = new Card(1, "clubs");
        //simulating that player got an ace after a hit
        currentGame.setPlayerHit(card);

        //making sure that the curr score is showing with the card value 1
        assertEquals(1, currentGame.getPlayerScore());
        //but when u calc it, it should reveal 11 bc its closer to 21
        assertEquals(11, currentGame.getPlayerCalcScore());

    }

    @Test
    public void testMultipleAces() {
        //first ace hit
        Card card = new Card(1, "clubs");
        currentGame.setPlayerHit(card);

        assertEquals(1, currentGame.getPlayerScore());
        assertEquals(11, currentGame.getPlayerCalcScore());

        //second ace hit
        card = new Card(1, "diamonds");
        currentGame.setPlayerHit(card);

        assertEquals(2, currentGame.getPlayerScore());
        //should be 12 bc one of the aces should have val of 1
        assertEquals(12, currentGame.getPlayerCalcScore());
    }
    @Test
    public void testCardCompareToEquals() {
        Card card = new Card(1, "clubs");
        Card card2 = new Card(1, "clubs");
        assertEquals(0, card.compareTo(card2));
    }

    @Test
    public void testCardCompareToNotEquals() {
        Card card = new Card(1, "clubs");
        Card card2 = new Card(1, "diamonds");
        Card card3 = new Card(2, "clubs");
        assertEquals(-1, card.compareTo(card2));
        assertEquals(-1, card.compareTo(card3));
    }

    @Test
    public void testDeckInit() {
        assertTrue(deck.getUsed().isEmpty());
    }

    @Test
    public void testDeckAddCardInvalidNum() {
        Card badCard1 = new Card(0, "clubs");
        assertThrows(IllegalArgumentException.class, () -> deck.addCard(badCard1));
        Card badCard2 = new Card(14, "clubs");
        assertThrows(IllegalArgumentException.class, () -> deck.addCard(badCard2));
        //making sure that the card isn't actually added
        assertTrue(deck.getUsed().isEmpty());
    }

    @Test
    public void testDeckAddCardInvalidSuite() {
        Card badCard = new Card(1, "club");
        assertThrows(IllegalArgumentException.class, () -> deck.addCard(badCard));
        assertTrue(deck.getUsed().isEmpty());
    }
    @Test
    public void testDeckAddCard() {
        Card card1 = new Card(1, "Clubs");
        Card card2 = new Card(1, "Diamonds");

        deck.addCard(card1);
        deck.addCard(card2);

        Set<Card> expected = new TreeSet<>();
        expected.add(card1);
        expected.add(card2);

        assertEquals(expected, deck.getUsed());
    }

    @Test
    public void testDeckAddCardDupe() {

        //creating a card
        Card card = new Card(1, "Clubs");
        //adding a card to used in deck
        deck.addCard(card);

        Set<Card> expectedUsed = new TreeSet<>();
        expectedUsed.add(card);

        assertEquals(expectedUsed, deck.getUsed());

        //adding the same card again to the deck
        deck.addCard(card);

        //should not actually add a card
        assertEquals(expectedUsed, deck.getUsed());

    }

    @Test
    public void testDeckDrawCardValid() {
        Set<Card> orgUsed = deck.getUsed();
        assertTrue(orgUsed.isEmpty());
        Card card = deck.drawCard();
        assertTrue(deck.isValidCard(card));
        assertFalse(orgUsed.contains(card));
    }

    @Test
    public void testDeckDrawCardFullDeck() {
        while (deck.getUsed().size() < 52) {
            deck.drawCard();
        }
        assertThrows(IllegalStateException.class, () -> deck.drawCard());

    }

}
