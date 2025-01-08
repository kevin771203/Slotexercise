package org.example;

public class SlotScoreCalculator {

    private final org.example.payTable payTable;
    private final Reels reels;
    private Reels freeGameReels;

    public SlotScoreCalculator(payTable payTable, Reels reels, Reels freeGameReels) {
        this.payTable = payTable;
        this.reels = reels;
        this.freeGameReels = freeGameReels;
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

        int odd = getOddFreeGame(screen);


        int win = odd * 10;

        return new SpinResult(win,screen);

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