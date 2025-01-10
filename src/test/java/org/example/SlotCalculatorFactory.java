package org.example;

import java.util.List;
import java.util.Random;

public class SlotCalculatorFactory {

    public SlotCalculatorFactory() {

    }
    SlotScoreCalculator create(String slotGameId) {

        if (slotGameId.equals("00001")) {


            RandomNumberGenerator randomNumberGenerator = new NativeRandomNumberGenerator(new Random());

            SlotScoreCalculator sut = new SlotScoreCalculator(
                    new GameFlow(new Reels(
                            List.of(
                                    List.of("A", "A", "3", "A"),
                                    List.of("A", "A", "3", "A"),
                                    List.of("A", "A", "3"),
                                    List.of("A", "A", "3", "A", "A"),
                                    List.of("A", "A", "4", "A", "A")
                            ), randomNumberGenerator), new SlotKingPayTable()),
                    new GameFlow(new Reels(
                            List.of(
                                    List.of("A", "2", "3", "3", "3"),
                                    List.of("A", "2", "3", "A", "A", "A", "A"),
                                    List.of("A", "3", "4", "A", "A", "A", "A")
                            ), randomNumberGenerator
                    ), new MasterPiecePayTable()), new GongXiFaCaiTriggeringRules()

            );
            return sut;
        }

        throw new RuntimeException("unknown slot game id");
    }
}