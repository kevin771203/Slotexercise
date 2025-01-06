package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

class SlotScoreCalculatorTest {

    private final Random random = Mockito.mock(Random.class);

    @Test
    void three_lines() {

        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")
                ), random, new payTable()
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(1_000);
    }

    @Test
    void two_lines() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "4")
                ), random, new payTable()
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(400);
    }

    @Test
    void one_line() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);

        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ), random, new payTable()
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(100);
    }

    @Test
    void lose() {
        Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(1);

        SlotScoreCalculator sut = new SlotScoreCalculator(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("2", "3", "4")
                ), random, new payTable()
        );

        int win = sut.calculate(10);

        Assertions.assertThat(win).isEqualTo(0);
    }
}