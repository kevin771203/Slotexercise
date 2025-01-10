package org.example;

public class SlotScoreCalculator {

    private final GameFlow baseGameFlow;
    private final GameFlow freeGameFlow;
    private int freeGameCount;
    private int freeGameBet;
    private final FreeGameTriggeringRules freeGameTriggeringRules;
    public SlotScoreCalculator(GameFlow baseGameFlow, GameFlow freeGameFlow, FreeGameTriggeringRules freeGameTriggeringRules) {
        this.baseGameFlow = baseGameFlow;
        this.freeGameFlow = freeGameFlow;
        this.freeGameTriggeringRules = freeGameTriggeringRules;
    }

    public SpinResult spinBase(int bet) throws WrongModeException {

        if (isFreeGame()) {
            throw new WrongModeException("wrong mode:FREE_GAME");
        }

        SpinResult result = baseGameFlow.runGameFlow(bet);

        tryTriggerFreeGame(result.getScreen(), bet);

        return result;
    }
    
    private void tryTriggerFreeGame(Screen screen, int bet) {
        boolean shouldTriggerFreeGame = freeGameTriggeringRules.checkTriggeringRules(screen);

        if (shouldTriggerFreeGame) {
            freeGameCount += freeGameTriggeringRules.getFreeGameCount();
            freeGameBet = bet;
        }
    }


    public Screen getScreen() {
        if (!isFreeGame()) {
            return baseGameFlow.getScreen();
        } else {
            return freeGameFlow.getScreen();
        }

    }




    public SpinResult spinFree() throws WrongModeException {

        if (!isFreeGame()) {
            throw new WrongModeException("wrong mode:BASE_GAME");
        }


        SpinResult spinResult = freeGameFlow.runGameFlow(freeGameBet);

        tryDeactivateFreeGame();

        return spinResult;

    }

    private void tryDeactivateFreeGame() {
        freeGameCount--;
    }

    public boolean isFreeGame() {return freeGameCount > 0;}

    public Memento toMemento() {

        return new Memento(
                baseGameFlow.getPositions(),
                freeGameFlow.getPositions(),
                freeGameCount
        );
    }

    public void restore(Memento memento) {

        baseGameFlow.restore(memento.getBaseGamePositions());
        freeGameFlow.restore(memento.getFreeGamePositions());
        freeGameCount = memento.getFreeGameCount();

    }
}