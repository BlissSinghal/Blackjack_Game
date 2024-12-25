package Card;

public class Card implements Comparable<Card> {
    //saving the card type and suite
    private final int number;
    private final String suite;


    public Card(int number, String suite) {
        this.number = number;
        this.suite = suite;


    }

    public int getNumber() {
        return number;
    }

    public String getSuite() {
        return suite;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card otherCard)) {
            return false;
        }
        return number == otherCard.number && suite.equals(otherCard.suite);
    }

    @Override
    public int compareTo(Card o) {
        String placeholder1 = this.getSuite() + " " + this.getNumber();
        String placeholder2 = o.getSuite() + " " + o.getNumber();
        return placeholder1.compareTo(placeholder2);
    }
}
