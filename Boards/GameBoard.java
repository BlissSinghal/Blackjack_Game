package Boards;

import Card.*;
import Card.HoleCardComponent;
import Card.RealCardComponent;
import CurrGame.CurrentGame;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;

public class GameBoard extends Board {
    private final CurrentGame currentGame;
    private Card playerCard;
    private final RealCardComponent playerCardComponent;
    private Card dealerCard;
    private CardComponent holeCardComponent;
    private final RealCardComponent dealerCardComponent;




    private final JButton hit;
    private final JButton stand;

    //allows us to add a property listener to this state
    SwingPropertyChangeSupport support;


    public GameBoard(CurrentGame currentGame, SwingPropertyChangeSupport support) {
        super();
        //initialize our GameState
        this.currentGame = currentGame;

        //initializing our layout
        setLayout(new BorderLayout());

        //have a default player hit since player gets one card in the set up
        playerCard = currentGame.playerHit();

        //have a default player card
        playerCardComponent =
                new RealCardComponent(playerCard.getSuite(), playerCard.getNumber());
        dealerCard = currentGame.dealerHit();

        //default dealer card
        dealerCardComponent =
                new RealCardComponent(dealerCard.getSuite(), dealerCard.getNumber());

        //creating our hole card
        holeCardComponent = new HoleCardComponent();

        dealerCardComponent.repaint();
        holeCardComponent.repaint();
        playerCardComponent.repaint();

        this.support = support;

        //creating our buttons
        hit = new JButton("Hit");
        hit.addActionListener(e -> {
            try {
                //when pressed we want to hit
                playerCard = currentGame.playerHit();
                //want to display the new card
                playerCardComponent.updateCard(playerCard.getSuite(), playerCard.getNumber());
                playerCardComponent.repaint();
            } catch (IllegalStateException ie) {
                gameOver();
            }

            //wanna check if we blackjacked or busted
            if (currentGame.checkUserBust() || currentGame.checkBlackJack()) {
                gameOver();
            }


        }

        );

        //when player stands, its the dealer's turn, so dealer must hit
        //If their score is less than or equal to 17
        stand = new JButton("Stand");
        stand.addActionListener(e -> {
            if (currentGame.getDealerCalcScore() < 17) {
                try {
                    dealerCard = currentGame.dealerHit();
                    dealerCardComponent.updateCard(dealerCard.getSuite(), dealerCard.getNumber());
                    dealerCardComponent.repaint();
                } catch (IllegalStateException ie) {
                    gameOver();
                }
                // Check if dealer busted
                if (currentGame.checkDealerBust()) {
                    gameOver();
                }
            } else {
                try {
                    Card holeCard = currentGame.dealerHit();


                    // Create the new RealCardComponent
                    CardComponent newHoleCardComponent =
                            new RealCardComponent(holeCard.getSuite(), holeCard.getNumber());

                    // Replace the old holeCardComponent
                    JPanel dealerPanel = (JPanel) holeCardComponent.getParent();
                    dealerPanel.remove(holeCardComponent);
                    holeCardComponent = newHoleCardComponent;
                    dealerPanel.add(holeCardComponent);

                    // Refresh the dealerPanel
                    dealerPanel.revalidate();
                    dealerPanel.repaint();
                } catch (IllegalStateException ignored) {

                }
                gameOver();
            }
        });

        // Set layouts for panels
        JPanel dealerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dealerPanel.add(dealerCardComponent);
        dealerPanel.add(holeCardComponent);

        JPanel playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        playerPanel.add(playerCardComponent);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        buttonPanel.add(hit);
        buttonPanel.add(stand);

        JLabel bottomLeftLabel1 = new JLabel("Your balance: $" + currentGame.getBalance());
        bottomLeftLabel1.setFont(new Font("Arial", Font.ITALIC, 12));
        JLabel bottomLeftLabel2 = new JLabel("Your Bet: $" + currentGame.getPlayerBet());
        bottomLeftLabel2.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(
                new BoxLayout(bottomLeftPanel, BoxLayout.Y_AXIS)); // Stack vertically
        bottomLeftPanel.add(bottomLeftLabel1);
        bottomLeftPanel.add(bottomLeftLabel2);

        // Align labels to the left
        bottomLeftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add panels to the board
        add(dealerPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(bottomLeftPanel, BorderLayout.WEST);


    }


    public void gameOver() {
        currentGame.getResult();
        //want to update our file
        currentGame.updateGameInfo();
        support.firePropertyChange("result", null, currentGame.printResult());

    }

    public void turnOffButtons() {
        hit.setVisible(false);
        stand.setVisible(false);
    }


}
