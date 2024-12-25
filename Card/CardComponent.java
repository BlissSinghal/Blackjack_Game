package Card;

import javax.swing.*;
import java.awt.*;

public class CardComponent extends JPanel {

    private final JLabel label;

    public CardComponent(Color color) {
        setBackground(color);
        Dimension fixedSize = new Dimension(100, 200); // Adjust as needed

        setPreferredSize(fixedSize);
        setMinimumSize(fixedSize);
        setMaximumSize(fixedSize);

        setLayout(new GridBagLayout());// Center components

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));

        // Create a JLabel for the text
        label = new JLabel("");
        label.setForeground(Color.black); // Set text color
        label.setFont(new Font("Arial", Font.BOLD, 10));

        add(label);
    }

    public void setText(String text) {
        label.setText(text);
    }

    /*
    @Override
    public void paintComponent(Graphics g) {
        //drawing a black border around the card

        g.setColor(Color.black);
        g.drawRect(0, 0, getWidth(), getHeight());

        //filling the inside of the card to be white
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }*/
}
