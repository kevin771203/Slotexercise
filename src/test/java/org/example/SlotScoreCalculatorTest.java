package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

class SlotScoreCalculatorTest {
    private final CyclicRNG randomNumberGenerator = new CyclicRNG();
    private final Random random = Mockito.mock(Random.class);
    private SlotScoreCalculator sut;
    private SpinResult spinResult;

    @Test
    void free_game() {

        //Mockito.when(random.nextInt(Mockito.anyInt())).thenReturn(0);
        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "A", "3"),
                List.of("A", "A", "3"),
                List.of("A", "A", "3"),
                List.of("A", "A", "3"),
                List.of("A", "A", "4")

        ));

        Reels freeGameReels = new Reels(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")

                ), randomNumberGenerator
        );
        sut.setFreeGameReels(freeGameReels);

        when_spin(10);
        when_spin_free();

        then_returned_SpinResult_should_be(
                5_000,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")
                )
        );

    }

    private void when_spin_free() {
        spinResult = sut.spinFree();
    }


    @Test
    void three_lines() {


        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ));

        when_spin(10);

        then_returned_SpinResult_should_be(
                1_000,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")
                )
        );
    }

    private void assume_RNG_generates(List<Integer> expecteds) {
        randomNumberGenerator.resetExpectedValues(expecteds);
    }

    private void given_sut(List<List<String>> rawReels) {
        Reels freeGameReels = new Reels(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")

                ), randomNumberGenerator
        );
        sut = new SlotScoreCalculator(
                new payTable(),
                new Reels(
                        rawReels, randomNumberGenerator),
                freeGameReels
        );
    }

    private void when_spin(int bet) {
        spinResult = sut.spinBase(bet);
    }

    private void then_returned_SpinResult_should_be(int win, List<List<String>> rawScreen) {
        Assertions.assertThat(spinResult.getWin()).isEqualTo(win);
        Assertions.assertThat(spinResult.getScreen()).isEqualTo(
                new Screen(
                        rawScreen
                )
        );
    }


    @Test
    void two_lines() {

        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "4")

        ));

        when_spin(10);

        then_returned_SpinResult_should_be(
                400,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "4")
                )
        );
    }

    @Test
    void one_line() {

        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "3", "4")

        ));

        when_spin(10);

        then_returned_SpinResult_should_be(
                100,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                )
        );
    }

    @Test
    void spin_and_lose() {

        assume_RNG_generates(List.of(1, 1, 1, 1, 2));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ));

        when_spin(10);

        then_returned_SpinResult_should_be(
                0,
                List.of(
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("3", "A", "2")
                ));

        when_get_screen_then_should_get(
                List.of(
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("3", "A", "2")
                ));
    }

    private void when_get_screen_then_should_get(List<List<String>> rawScreen) {
        Assertions.assertThat(spinResult.getScreen()).isEqualTo(
                new Screen(
                        rawScreen
                )
        );
    }

    @Test
    void init() {

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ));

        Screen screen = sut.getScreen();
        Assertions.assertThat(screen).isEqualTo(
                new Screen(
                        List.of(
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3"),
                                List.of("A", "2", "3")
                        )
                )
        );

    }


}