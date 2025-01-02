package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SlotScoreCalculatorTest {

    @Test
    void one_line() {
        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("A", "2", "3")
                )
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(400);
    }

    @Test
    void lose() {
        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("A", "1", "2"),
                        List.of("1", "2", "3")
                )
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(0);
    }
}