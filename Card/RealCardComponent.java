package Card;

import javax.swing.*;
import java.awt.*;

public class RealCardComponent extends CardComponent {
    private int number;
    private String suite;
    public RealCardComponent(String suite, int number) {
        super(Color.white);
        this.number = number;
        this.suite = suite;

        setText(genLabel());

    }

    public void updateCard(String suite, int number) {
        this.number = number;
        this.suite = suite;
        setText(genLabel());
    }

    public String genLabel() {
        String val = "" + number;
        //Ks Aces, Queens, and Jacks
        if (number == 1) {
            val = "Ace";
        }
        if (number == 11) {
            val = "J";
        }
        if (number == 12) {
            val = "Q";
        }
        if (number == 13) {
            val = "K";
        }
        return val + "  "  + suite;
    }



}
