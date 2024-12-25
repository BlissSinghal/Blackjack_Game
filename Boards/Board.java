package Boards;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private int frameWidth;
    private int frameHeight;

    public Board() {
        super();
        setSize(frameWidth, frameHeight);
        setBackground(new Color(79, 210, 243));
    }

}
