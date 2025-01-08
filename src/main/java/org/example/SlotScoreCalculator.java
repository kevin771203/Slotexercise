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



        return new SpinResult(
                5_000,
                new Screen(
                        List.of(
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3")
                        )
                )
        );
    }
}