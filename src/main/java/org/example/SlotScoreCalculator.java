package org.example;

import java.util.List;

public class SlotScoreCalculator {

    private final org.example.payTable payTable;
    private final Reels reels;
    private Reels freeGameReels;

    public SlotScoreCalculator(payTable payTable, Reels reels) {
        this.payTable = payTable;
        this.reels = reels;
    }

    public SpinResult spinBase(int bet) {

        reels.spin();

        Screen screen = reels.getScreen();

        int odd = payTable.getOdd(screen);

        int win = odd * bet;

        return new SpinResult(win,screen);
    }


    public Screen getScreen() {
        return reels.getScreen();
    }

    public void setFreeGameReels(Reels freeGameReels) {
        this.freeGameReels = freeGameReels;
    }

    public SpinResult spinFree() {


        freeGameReels.spin();

        Screen screen = freeGameReels.getScreen();

        int odd = 500;

        int win = odd * 10;

        return new SpinResult(win,screen);
        
    }
}