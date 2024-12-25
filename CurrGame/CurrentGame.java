package CurrGame;

import Card.*;

import FileIO.GameState;
import Player.DealerState;
import Player.UserState;

import java.util.Set;


public class CurrentGame {

    private int playerBet = 25;
    private int balance;
    private int userWins;
    private int dealerWins;
    private int numGames;
    private final UserState user;
    private final DealerState dealer;
    private String result;

    private final GameState gameState;
    private boolean gameOver = false;




    public CurrentGame() {
        //creating our GameInfo Obj
        gameState = new GameState();
        balance = gameState.getPlayerBalance();
        userWins = gameState.getPlayerWins();
        dealerWins = gameState.getDealerWins();
        numGames = gameState.getNumGames();


        Deck deck = new Deck();
        //initializing our player states
        user = new UserState(deck);
        dealer = new DealerState(deck);

    }

    public CurrentGame(int balance, int userWins, int dealerWins) {
        gameState = new GameState();
        this.balance = balance;
        this.userWins = userWins;
        this.dealerWins = dealerWins;

        Deck deck = new Deck();
        user = new UserState(deck);
        dealer = new DealerState(deck);
    }

    public Card playerHit() throws IllegalStateException {
        try {
            return user.hit();
        } catch (IllegalStateException e) {
            gameOver = true;
            throw new IllegalStateException();
        }
    }

    public Card dealerHit() throws IllegalStateException {
        try {
            return dealer.hit();
        } catch (IllegalStateException e) {
            gameOver = true;
            throw new IllegalStateException();
        }
    }


    public int getDealerCalcScore() {
        return dealer.calcScore();
    }

    public int getPlayerCalcScore() {
        return user.calcScore();
    }


    public int getPlayerScore() {
        return user.getScore();
    }

    public int getDealerScore() {
        return dealer.getScore();
    }



    public int getPlayerBet() {
        return playerBet;
    }

    public int getBalance() {
        return balance;
    }
    public int getDealerWins() {
        return dealerWins;
    }

    public int getUserWins() {
        return userWins;
    }

    public int getNumGames() {
        return numGames;
    }

    public boolean checkDealerBust() {
        return dealer.checkBust();
    }

    public boolean checkUserBust() {
        return user.checkBust();
    }

    public boolean checkBlackJack() {
        return user.checkBlackJack();
    }

    public boolean checkFullDeck() {
        return user.isFullDeck();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public String printResult() {
        return result;
    }

    public Set<Card> getUsedDeck() {
        return user.getDeckUsed();
    }

    //set methods

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setPlayerScore(int playerScore) {
        user.setScore(playerScore);
    }

    public void setDealerScore(int dealerScore) {
        dealer.setScore(dealerScore);
    }

    public void setUserWins(int userWins) {
        this.userWins = userWins;
    }

    public void setDealerWins(int dealerWins) {
        this.dealerWins = dealerWins;
    }

    public void reset() {
        gameState.reset();
    }

    public Card setPlayerHit(Card card) {
        return user.hit(card);
    }


    public void getResult() {
        gameOver = true;
        //check blackjack
        if (user.checkBlackJack()) {
            balance += (int) (1.5 * playerBet);
            userWins ++;
            result = "BLACKJACK";
        } else if (user.checkBust()) {
            dealerWins++;
            balance -= playerBet;
            result = "You Lost";
        } else if (dealer.checkBust() || user.calcScore() > dealer.calcScore()) {
            balance += playerBet;
            userWins ++;
            result = "You Won";
        } else if (user.calcScore() == dealer.calcScore()) {
            result = "TIE";
        } else {
            dealerWins++;
            balance -= playerBet;
            result = "You Lost";
        }

    }

    public void updateGameInfo() {
        gameState.updateGameInfo(balance, userWins, dealerWins);
    }






}
