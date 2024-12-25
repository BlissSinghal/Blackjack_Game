package Player;

import Card.*;

public class UserState extends PlayerState {

    public UserState(Deck deck) {
        setDeck(deck);

    }

    public boolean checkBlackJack() {
        return this.calcScore() == 21;
    }



}
