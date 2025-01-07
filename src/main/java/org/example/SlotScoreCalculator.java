package org.example;

public class SlotScoreCalculator {

    private final org.example.payTable payTable;
    private final Reels reels;

    public SlotScoreCalculator(payTable payTable, Reels reels) {
        this.payTable = payTable;
        this.reels = reels;
    }

    public SpinResult calculate(int bet) {

        reels.spin();

        Screen screen = reels.getScreen();

        int odd = payTable.getOdd(screen);

        int win = odd * bet;

        return new SpinResult(win,screen);
    }


    public Screen getScreen() {
        return reels.getScreen();
    }
}