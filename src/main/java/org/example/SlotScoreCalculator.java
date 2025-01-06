package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SlotScoreCalculator {

    private final List<List<String>> reels;

    private final Map<Integer, Integer> odds = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(0, 0),
            new AbstractMap.SimpleEntry<>(1, 10),
            new AbstractMap.SimpleEntry<>(2, 40),
            new AbstractMap.SimpleEntry<>(3, 100)
    );

    private final Random random;

    public SlotScoreCalculator(List<List<String>> reels, Random random) {
        this.reels = reels;
        this.random = random;
    }

    public int calculate(int bet) {

        List<List<String>> screen = getScreen();


        // ---------------

        int odd = getOdd(screen);

        return odd * bet;
    }

    private List<List<String>> getScreen() {
        List<List<String>> screen = reels.stream().map(
                reel -> {
                    int nextPosition = random.nextInt(reel.size());


                    return Stream.concat(reel.stream(), reel.stream()).toList().subList(
                            nextPosition, nextPosition + 3
                    );
                }
        ).toList();
        return screen;
    }

    private int getOdd(List<List<String>> screen) {
        int Lines = getLines(screen);

        return getOdd(Lines);
    }

    private int getOdd(int lines) {

        Integer odd = odds.get(lines);

        if (Objects.isNull(odd)) {
            throw new RuntimeException("Unsupported lines");
        }

        return odd;
    }

    private int getLines(List<List<String>> screen) {
        int sumOfSameLines = 0;
        for (int i =0; i<3; i++) {

            int finalI = i;
            Set<String> distinctSymbols = screen.stream().map(wheel -> wheel.get(finalI)).collect(Collectors.toSet());

            if (distinctSymbols.size() == 1) {
                sumOfSameLines++;
            }
        }
        return sumOfSameLines;
    }
}