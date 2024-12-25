package org.cis1200;

import Boards.Board;
import Boards.GameBoard;
import Boards.GameOverBoard;
import Boards.InstructionsBoard;
import CurrGame.CurrentGame;

import javax.swing.*;
import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;


public class RunGame implements Runnable {

    public void run() {
        //making our frame
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());

        //creating our instructions panel
        Board instructionsBoard = new InstructionsBoard();

        //creating a PropertyChangeSupport
        SwingPropertyChangeSupport support = new SwingPropertyChangeSupport(frame);

        //creating our game state
        CurrentGame currentGame = new CurrentGame();

        //creating our GameBoard
        GameBoard gameBoard = new GameBoard(currentGame, support);


        //adding listener that listens for whether the game has ended
        support.addPropertyChangeListener("result", evt -> {
            //wanna immediately clear our buttons
            gameBoard.turnOffButtons();
            //wanna delay a bit
            Timer timer =  new Timer(1000, e -> {
                //we want to change our board to game over board
                frame.getContentPane().removeAll();
                frame.getContentPane().add(
                        new GameOverBoard(currentGame), BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            });
            timer.start();
        });



        //making a nextButton that listens for when the user is ready
        //to move on
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            //refreshing the frame
            frame.getContentPane().removeAll();
            //adding our game board panel
            frame.add(gameBoard, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
        });

        //adding these components to our frame
        frame.add(instructionsBoard, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new RunGame());
    }



}
