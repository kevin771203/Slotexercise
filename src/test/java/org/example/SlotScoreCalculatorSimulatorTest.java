package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.assertj.core.api.Assertions;

class SlotScoreCalculatorSimulatorTest {
    private final SlotCalculatorFactory slotCalculatorFactory = new SlotCalculatorFactory();

    @Disabled
    @Test
    void simulate() throws WrongModeException {

        SlotScoreCalculator sut = slotCalculatorFactory.create("00001");

        int totalWin = 0;
        int totalBet = 0;

        for (int i = 0; i < 100_000; i++) {
            if (sut.isFreeGame()) {
                SpinResult spinResult = sut.spinFree();
                totalBet += 10;
                totalWin += spinResult.getWin();
            }else {
                SpinResult spinResult = sut.spinBase(10);
                totalBet += 10;
                totalWin += spinResult.getWin();
            }
        }

        double rtp = (totalWin + 0D) / totalBet;
        System.out.println(rtp);
        Assertions.assertThat(Math.abs(rtp - 80) < 1).isTrue();
    }

}
