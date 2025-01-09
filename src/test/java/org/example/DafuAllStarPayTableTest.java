package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class DafuAllStarPayTableTest {

    @Test
    void A_4_200(){

        PayTable sut =  new DafuAllStarPayTable();

        Screen screen = new Screen(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "4", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "4", "3"),
                        List.of("2", "8", "4")
                )
        );
        int actual = sut.getOdd(screen);

        Assertions.assertThat(actual).isEqualTo(200);
    }

    @Test
    void A_5_400(){

        PayTable sut =  new DafuAllStarPayTable();

        Screen screen = new Screen(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "4", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "4", "3"),
                        List.of("A", "8", "4")
                )
        );
        int actual = sut.getOdd(screen);

        Assertions.assertThat(actual).isEqualTo(400);
    }
}
