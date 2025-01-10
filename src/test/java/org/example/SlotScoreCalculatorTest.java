package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class SlotScoreCalculatorTest {
    private final CyclicRNG randomNumberGenerator = new CyclicRNG();
    private SlotScoreCalculator sut;
    private SpinResult spinResult;


    @Test
    void back_to_base_game() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);
        when_spin_free();
        when_spin_free();
        when_spin_free();

        Assertions.assertThatThrownBy(
                () -> when_spin_free()
        ).hasMessageContaining("wrong mode:BASE_GAME");

    }

    @Test
    void free_game_3_times() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);
        when_spin_free();
        when_spin_free();
        when_spin_free();

        then_returned_SpinResult_should_be(
                1_000,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                )
        );

    }

    @Test
    void cannot_play_base_game_in_free_game_mode() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);

        Assertions.assertThatThrownBy(
                () -> when_spin_base(10)
        ).hasMessageContaining("wrong mode:FREE_GAME");

    }
    @Test
    void recovery_in_free_game() throws WrongModeException {

        assume_RNG_generates(List.of(0, 0, 0, 0, 0, 1, 1, 1));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);
        when_spin_free();
        Memento memento = sut.toMemento();

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));

        sut.restore(memento);

        when_get_screen_then_should_get(
                List.of(
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("3", "4", "A")
                )
        );

    }

    @Test
    void get_screen_in_free_game() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);
        when_spin_free();

        when_get_screen_then_should_get(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                )
        );

    }

    @Test
    void free_game_1_lines() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                ));


        when_spin_base(10);
        when_spin_free();

        then_returned_SpinResult_should_be(
                1_000,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "3", "4")
                )
        );

    }

    @Test
    void free_game_2_lines() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "4")
                ));


        when_spin_base(10);
        when_spin_free();

        then_returned_SpinResult_should_be(
                3_000,
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "4")
                )
        );

    }
    @Test
    void free_game_3_lines() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(
                List.of(
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "3"),
                        List.of("A", "A", "4")
                ), List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")
                ));


        when_spin_base(10);
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

    private void when_spin_free() throws WrongModeException {
        spinResult = sut.spinFree();
    }


    @Test
    void three_lines() throws WrongModeException {


        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ));

        when_spin_base(10);

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

    private void given_sut(List<List<String>> baseGameRawReels) {
        given_sut(baseGameRawReels, List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ));
    }

    private void given_sut(List<List<String>> baseGameRawReels, List<List<String>> freeGameRawReels) {

        final Reels baseGameReels = new Reels(
                baseGameRawReels, randomNumberGenerator);
        final SlotKingPayTable slotKingPayTable = new SlotKingPayTable();
        final Reels freeGameReels = new Reels(
                freeGameRawReels, randomNumberGenerator
        );
        final MasterPiecePayTable masterPiecePayTable = new MasterPiecePayTable();
        sut = new SlotScoreCalculator(
                new GameFlow(baseGameReels, slotKingPayTable), new GameFlow(freeGameReels, masterPiecePayTable), new GongXiFaCaiTriggeringRules()
        );
    }

    private void when_spin_base(int bet) throws WrongModeException {
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
    void two_lines() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "4")

        ));

        when_spin_base(10);

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
    void one_line() throws WrongModeException {

        assume_RNG_generates(List.of(0));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "3", "4")

        ));

        when_spin_base(10);

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
    void base_game_recovery() throws WrongModeException {

        assume_RNG_generates(List.of(1, 1, 1, 1, 2));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ), List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")
        ));

        when_spin_base(10);

        Memento memento = sut.toMemento();

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")
        ), List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")
        ));

        sut.restore(memento);

        when_get_screen_then_should_get(
                List.of(
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("2", "3", "A"),
                        List.of("3", "A", "2")
                )
        );
    }

    @Test
    void spin_and_lose() throws WrongModeException {

        assume_RNG_generates(List.of(1, 1, 1, 1, 2));

        given_sut(List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")

        ), List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")
        ));

        when_spin_base(10);

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
        Screen screen = sut.getScreen();
        Assertions.assertThat(screen).isEqualTo(
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

        ), List.of(
                List.of("A", "2", "3"),
                List.of("A", "2", "3"),
                List.of("A", "2", "3")
        ));


        when_get_screen_then_should_get(
                List.of(
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3"),
                        List.of("A", "2", "3")
                ));

    }


}