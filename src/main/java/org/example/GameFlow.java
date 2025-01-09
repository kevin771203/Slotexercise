package org.example;

public class GameFlow {
    public GameFlow() {
    }

    SpinResult runGameFlow(int bet, Reels reels, PayTable payTable) {

        reels.spin();

        Screen screen = reels.getScreen();

        int odd = payTable.getOdd(screen);

        int win = odd * bet;

        return new SpinResult(win, screen);
    }
}