package org.example;

public class SlotScoreCalculator {

    private final org.example.payTable payTable;
    private final Reels reels;

    public SlotScoreCalculator(payTable payTable, Reels reels1) {
        this.payTable = payTable;
        this.reels = reels1;
    }

    public int calculate(int bet) {

        Screen screen = reels.reelsToScreen();

        int odd = payTable.getOdd(screen);

        return odd * bet;
    }


}