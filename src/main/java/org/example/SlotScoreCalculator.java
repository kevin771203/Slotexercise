package org.example;

import java.util.*;

public class SlotScoreCalculator {

//    private final List<List<String>> rawReels;

    private final Random random;
    private final org.example.payTable payTable;
    private final Reels reels1;

    public SlotScoreCalculator(List<List<String>> rawReels, Random random, payTable payTable) {
//        this.rawReels = rawReels;
        this.random = random;
        this.payTable = payTable;
        reels1 = new Reels(rawReels);
    }

    public int calculate(int bet) {

        Screen screen = reels1.reelsToScreen(this.random);

        int odd = payTable.getOdd(screen);

        return odd * bet;
    }


}