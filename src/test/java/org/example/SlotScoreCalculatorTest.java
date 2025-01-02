package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SlotScoreCalculatorTest {
    @Test
    void name() {
        SlotScoreCalculator sut = new SlotScoreCalculator();

        int win = sut.calculate();

        Assertions.assertThat(win).isEqualTo(0);
    }
}