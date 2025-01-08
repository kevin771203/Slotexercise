package org.example;

import java.util.List;

public class SlotScoreCalculator {

    private final org.example.payTable baseGamePayTable;
    private final org.example.freeGamePayTable freeGamePayTable;
    private final Reels reels;
    private Reels freeGameReels;
    private int freeGameCount;
    private int freeGameBet;

    public SlotScoreCalculator(payTable baseGamePayTable, Reels reels, Reels freeGameReels, freeGamePayTable freeGamePayTable) {
        this.baseGamePayTable = baseGamePayTable;
        this.reels = reels;
        this.freeGameReels = freeGameReels;
        this.freeGamePayTable = freeGamePayTable;
    }

    public SpinResult spinBase(int bet) throws WrongMethodException {


        if (freeGameCount > 0) {
            throw new WrongMethodException("wrong mode:FREE_GAME");
        }

        reels.spin();

        Screen screen = reels.getScreen();

        int odd = baseGamePayTable.getOdd(screen);

        int win = odd * bet;

        tryTriggerFreeGame(screen, bet);

        return new SpinResult(win,screen);
    }

    private void tryTriggerFreeGame(Screen screen, int bet) {
        int count = 0;
        for (List<String> rawColumns : screen.rawScreen()) {
            for (String grid : rawColumns) {
                if(grid.equals("A")) {
                    count++;
                }
            }
        }

        if (count >= 10) {
            freeGameCount +=3;
            freeGameBet = bet;
        }
    }


    public Screen getScreen() {
        if (freeGameCount <= 0) {
            return reels.getScreen();
        } else {
            return freeGameReels.getScreen();
        }

    }



    public SpinResult spinFree() throws WrongMethodException {

        if (freeGameCount <= 0) {
            throw new WrongMethodException("wrong mode:BASE_GAME");
        }

        freeGameReels.spin();

        Screen screen = freeGameReels.getScreen();

        int odd = freeGamePayTable.getOddFreeGame(screen);


        int win = odd * freeGameBet;

        tryDeactiveFreeGame();

        return new SpinResult(win,screen);

    }

    private void tryDeactiveFreeGame() {
        freeGameCount--;
    }

//    private int getOddFreeGame(Screen screen) {
//        return freeGamePayTable.getOddFreeGame(screen);
//    }
}