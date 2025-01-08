package org.example;

import java.lang.invoke.WrongMethodTypeException;
import java.util.List;

public class SlotScoreCalculator {

    private final org.example.payTable payTable;
    private final Reels reels;
    private Reels freeGameReels;
    private int freeGameCount;

    public SlotScoreCalculator(payTable payTable, Reels reels, Reels freeGameReels) {
        this.payTable = payTable;
        this.reels = reels;
        this.freeGameReels = freeGameReels;
    }

    public SpinResult spinBase(int bet) throws WrongMethodException {


        if (freeGameCount > 0) {
            throw new WrongMethodException("wrong mode:FREE_GAME");
        }
        
        reels.spin();

        Screen screen = reels.getScreen();

        int odd = payTable.getOdd(screen);

        int win = odd * bet;

        tryTriggerFreeGame(screen);

        return new SpinResult(win,screen);
    }

    private void tryTriggerFreeGame(Screen screen) {
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

        int odd = getOddFreeGame(screen);


        int win = odd * 10;

        tryDeactiveFreeGame();

        return new SpinResult(win,screen);

    }

    private void tryDeactiveFreeGame() {
        freeGameCount--;
    }

    private static int getOddFreeGame(Screen screen) {
        int odd = 0;

        int lines = screen.countStraightLines();
        if(lines == 3) {
            odd = 500;
        } else if(lines == 2) {
            odd = 300;
        } else if(lines == 1) {
            odd = 100;
        }
        return odd;
    }
}