package Boards;

import javax.swing.*;
import java.awt.*;

public class InstructionsBoard extends Board {
    public InstructionsBoard() {
        super();
        setLayout(new BorderLayout());

        // Create a scroll pane to allow for scrolling in case the instructions are too long
        JPanel instructionsPanel = new JPanel();
        instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));

        // Objective section
        String objective = "Objective: Your hand value needs to be closer to 21 than the dealer's.";
        JLabel objectiveLabel = new JLabel(objective);
        objectiveLabel.setFont(new Font("Arial", Font.BOLD, 14));
        objectiveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Rules section
        String rule1 = "1. If your hand exceeds 21, you lose.";
        String rule2 =
                "2. The dealer hits if their hand is <= 16 otherwise stand";
        String rule3 = "3. A hand of 21 is a Blackjack, get 150% of bet.";
        JLabel ruleLabel1 = new JLabel(rule1);
        JLabel ruleLabel2 = new JLabel(rule2);
        JLabel ruleLabel3 = new JLabel(rule3);
        ruleLabel1.setFont(new Font("Arial", Font.PLAIN, 12));
        ruleLabel2.setFont(new Font("Arial", Font.PLAIN, 12));
        ruleLabel3.setFont(new Font("Arial", Font.PLAIN, 12));
        ruleLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        ruleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        ruleLabel3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Actions section
        String action1 = "Hit: Request another card to improve your hand.";
        String action2 = "Stand: End your turn and keep your current hand.";

        JLabel actionLabel1 = new JLabel(action1);
        JLabel actionLabel2 = new JLabel(action2);

        actionLabel1.setFont(new Font("Arial", Font.PLAIN, 12));
        actionLabel2.setFont(new Font("Arial", Font.PLAIN, 12));

        actionLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding all sections to the instructions panel
        instructionsPanel.add(objectiveLabel);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Adding some space
        instructionsPanel.add(ruleLabel1);
        instructionsPanel.add(ruleLabel2);
        instructionsPanel.add(ruleLabel3);
        instructionsPanel.add(Box.createRigidArea(new Dimension(0, 10)));  // Adding some space
        instructionsPanel.add(actionLabel1);
        instructionsPanel.add(actionLabel2);

        // Make the instructions scrollable
        JScrollPane scrollPane = new JScrollPane(instructionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Adding the scroll pane to the main panel
        add(instructionsPanel, BorderLayout.CENTER);
    }
}
