import FileIO.FileInfoController;
import CurrGame.CurrentGame;
import Card.Card;
import org.junit.jupiter.api.*;


import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

public class CurrentGameTest {

    private CurrentGame currentGame;

    @BeforeEach
    public void setUp() {
        currentGame = new CurrentGame(1000, 0, 0);

    }

    @Test
    public void testInitial() {

        // Verify that the initial balance is retrieved correctly
        assertEquals(1000, currentGame.getBalance());
        assertEquals(0, currentGame.getDealerWins());
        assertEquals(0, currentGame.getUserWins());
    }

    @Test
    public void testPlayerHit() {
        Card card = currentGame.playerHit();
        assertNotNull(card); // Player receives a valid card
        //score should increase by same value as the card number
        assertEquals(currentGame.getPlayerScore(), card.getNumber());
    }

    @Test
    public void testDealerHit() {
        Card card = currentGame.dealerHit();
        assertNotNull(card);
        //dealer score should increase after hit
        assertEquals(currentGame.getDealerScore(), Math.min(card.getNumber(), 10));
    }

    @Test
    public void testBlackjackWin() {
        currentGame.setBalance(1000);
        //simulating user getting blackjack
        currentGame.setPlayerScore(21);
        currentGame.getResult();
        assertEquals("BLACKJACK", currentGame.printResult());
        //making sure balance increased by 1.5 times the player bet
        assertEquals(currentGame.getBalance(), 1000 + (int) (1.5 * currentGame.getPlayerBet()));
        assertEquals(1, currentGame.getUserWins());
    }

    @Test
    public void testUserBust() {

        // Simulate user busting
        while (!currentGame.checkUserBust()) {
            currentGame.playerHit(); // Keep hitting until bust
        }
        currentGame.getResult();
        assertEquals("You Lost", currentGame.printResult());
        //player's balance should decrease by their bet
        assertEquals(currentGame.getBalance(), 1000 - currentGame.getPlayerBet());
        assertEquals(1, currentGame.getDealerWins());
    }

    @Test
    public void testDealerBust() {

        // Simulate dealer busting
        while (!currentGame.checkDealerBust()) {
            currentGame.dealerHit(); // Keep hitting until bust
        }
        currentGame.getResult();
        assertEquals("You Won", currentGame.printResult());
        //player balance increases by the bet after winning
        assertEquals(currentGame.getBalance(), 1000 + currentGame.getPlayerBet());
        assertEquals(1, currentGame.getUserWins());
    }

    @Test
    public void testTie() {
        currentGame.setBalance(1000);
        //simulating a tie
        currentGame.setPlayerScore(20);
        currentGame.setDealerScore(20);
        currentGame.getResult();
        assertEquals("TIE", currentGame.printResult());
        assertEquals(1000, currentGame.getBalance()); // Balance should remain the same
        assertEquals(0, currentGame.getUserWins());
        assertEquals(0, currentGame.getDealerWins());
    }

    @Test
    public void testUserLoss() {
        currentGame.setBalance(1000);
        assertEquals(0, currentGame.getUserWins());
        assertEquals(0, currentGame.getDealerWins());
        currentGame.setPlayerScore(19);
        currentGame.setDealerScore(20);
        currentGame.getResult();
        assertEquals("You Lost", currentGame.printResult());
        assertEquals(currentGame.getBalance(), 1000 - currentGame.getPlayerBet());

        assertEquals(0, currentGame.getUserWins());
        assertEquals(1, currentGame.getDealerWins());
    }

    @Test
    public void testUpdateGameInfo() {

        //simulating a loss
        currentGame.setPlayerScore(22);
        // Verify that game info updates correctly
        currentGame.getResult();
        currentGame.updateGameInfo();

        FileInfoController controller =
                new FileInfoController("files/GameStateInfo.csv");

        assertEquals(975,
                Integer.parseInt(controller.getData(controller.getRowNum() - 1, 1)));
        assertEquals(0,
                Integer.parseInt(controller.getData(controller.getRowNum() - 1, 2)));
        assertEquals(1,
                Integer.parseInt(controller.getData(controller.getRowNum() - 1, 3)));




    }

    @Test
    public void testFullDeck() {
        while (true) {
            try {
                currentGame.playerHit();
            } catch (IllegalStateException e) {
                break;
            }
        }
        //hitting even tho deck is full
        assertThrows(IllegalStateException.class, () -> currentGame.playerHit());
        assertTrue(currentGame.checkFullDeck());
        Set<Card> used = currentGame.getUsedDeck();
        //creating a full deck
        String[] suites = {"Clubs", "Diamonds", "Hearts", "Spades"};

        for (String suite : suites) {
            for (int num = 1; num <= 13; num++) {
                assertTrue(used.contains(new Card(num, suite)));
            }
        }
        assertTrue(currentGame.isGameOver());

    }

    @Test
    public void testDefaultBetAmount() {
        assertEquals(25, currentGame.getPlayerBet());
    }

}
