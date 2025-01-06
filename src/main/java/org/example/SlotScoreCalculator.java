package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class SlotScoreCalculator {

    private final List<List<String>> wheels;

    private final Map<Integer, Integer> odds = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(0, 0),
            new AbstractMap.SimpleEntry<>(1, 10),
            new AbstractMap.SimpleEntry<>(2, 40),
            new AbstractMap.SimpleEntry<>(3, 100)
    );

    private final Random random;

    public SlotScoreCalculator(List<List<String>> wheels, Random random) {
        this.wheels = wheels;
        this.random = random;
    }

    public int calculate(int bet) {


        List<List<String>> screen = new ArrayList<>();
        for (List<String> wheel : wheels) {
            int nextPosition = random.nextInt(wheel.size());
            List<String> column = wheel.subList(nextPosition, nextPosition + 3);
            screen.add(column);
        }




      // ---------------

        int odd = getOdd(screen);

        return odd * bet;
    }

    private Random getRandom() {
        Random random = new Random();
        return random;
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