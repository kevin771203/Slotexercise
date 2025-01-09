package org.example;

import java.util.List;

public class SlotScoreCalculator {

    private final Reels baseGameReels;
    private final PayTable baseGamePayTable;
    private final Reels freeGameReels;
    private final PayTable freeGamePayTable;
    private final GameFlow gameFlow;
    private int freeGameCount;
    private int freeGameBet;

    public SlotScoreCalculator(Reels baseGameReels, PayTable baseGamePayTable, Reels freeGameReels, PayTable freeGamePayTable) {
        this.baseGameReels = baseGameReels;
        this.baseGamePayTable = baseGamePayTable;
        this.freeGameReels = freeGameReels;
        this.freeGamePayTable = freeGamePayTable;
        gameFlow = new GameFlow();
    }

    public SpinResult spinBase(int bet) throws WrongMethodException {

        if (freeGameCount > 0) {
            throw new WrongMethodException("wrong mode:FREE_GAME");
        }

        SpinResult result = gameFlow.runGameFlow(bet, baseGameReels, baseGamePayTable);

        tryTriggerFreeGame(result.getScreen(), bet);

        return result;
    }


    private SpinResult runGameFlow(int bet, Reels reels, PayTable payTable) {

        return gameFlow.runGameFlow(bet, reels, payTable);
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
            return baseGameReels.getScreen();
        } else {
            return freeGameReels.getScreen();
        }

    }


    public SpinResult spinFree() throws WrongMethodException {

        if (freeGameCount <= 0) {
            throw new WrongMethodException("wrong mode:BASE_GAME");
        }


        SpinResult spinResult = gameFlow.runGameFlow(freeGameBet, freeGameReels, freeGamePayTable);

        tryDeactivateFreeGame();

        return spinResult;

    }

    private void tryDeactivateFreeGame() {
        freeGameCount--;
    }

}