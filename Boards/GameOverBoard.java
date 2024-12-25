package Boards;

import CurrGame.CurrentGame;

import javax.swing.*;
import java.awt.*;

public class GameOverBoard extends Board {

    public GameOverBoard(CurrentGame currentGame) {


        // Set layout manager to BorderLayout
        setLayout(new BorderLayout());

        // Create the main panel to hold all components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the "You Won" label
        JLabel resultLabel = new JLabel(currentGame.printResult() + "!");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the "Your Balance" label
        JLabel balanceLabel = new JLabel("Your Current Balance: " + currentGame.getBalance());
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the "Your Wins" label
        JLabel winsLabel = new JLabel("Your Wins: " + currentGame.getUserWins());
        winsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        winsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the "Your Losses" label
        JLabel lossesLabel = new JLabel("Your Losses: " + currentGame.getDealerWins());
        lossesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        lossesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the "Num Games" label
        JLabel numGamesLabel =
                new JLabel("Number of Rounds: " + currentGame.getNumGames());
        numGamesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numGamesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //creating the reset button
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 18));
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.addActionListener(e -> currentGame.reset());

        // Add vertical spacing and add components to the main panel
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // Space between result and balance label
        mainPanel.add(balanceLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Space between balance and wins label
        mainPanel.add(winsLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // Space between wins and losses label
        mainPanel.add(lossesLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(numGamesLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(resetButton);
        mainPanel.add(Box.createVerticalGlue());

        // Add the main panel to the center of the GameOverBoard
        add(mainPanel, BorderLayout.CENTER);

    }

}
