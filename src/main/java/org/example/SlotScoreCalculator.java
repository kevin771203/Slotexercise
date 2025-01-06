package org.example;

import java.util.*;

public class SlotScoreCalculator {

    private final Random random;
    private final org.example.payTable payTable;
    private final Reels reels;

    public SlotScoreCalculator(List<List<String>> rawReels, Random random, payTable payTable) {
        this.random = random;
        this.payTable = payTable;
        this.reels = new Reels(rawReels, random);
    }

    public int calculate(int bet) {

        Screen screen = reels.reelsToScreen();

        int odd = payTable.getOdd(screen);

        return odd * bet;
    }


}