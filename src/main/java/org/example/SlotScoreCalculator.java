package org.example;

import java.util.List;

public class SlotScoreCalculator {

    private final GameFlow baseGameFlow;
    private final GameFlow freeGameFlow;
    private int freeGameCount;
    private int freeGameBet;

    public SlotScoreCalculator(GameFlow baseGameFlow, GameFlow freeGameFlow) {
        this.baseGameFlow = baseGameFlow;
        this.freeGameFlow = freeGameFlow;
    }

    public SpinResult spinBase(int bet) throws WrongMethodException {

        if (freeGameCount > 0) {
            throw new WrongMethodException("wrong mode:FREE_GAME");
        }

        SpinResult result = baseGameFlow.runGameFlow(bet);

        tryTriggerFreeGame(result.getScreen(), bet);

        return result;
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
            return baseGameFlow.getScreen();
        } else {
            return freeGameFlow.getScreen();
        }

    }




    public SpinResult spinFree() throws WrongMethodException {

        if (freeGameCount <= 0) {
            throw new WrongMethodException("wrong mode:BASE_GAME");
        }


        SpinResult spinResult = freeGameFlow.runGameFlow(freeGameBet);

        tryDeactivateFreeGame();

        return spinResult;

    }

    private void tryDeactivateFreeGame() {
        freeGameCount--;
    }

}