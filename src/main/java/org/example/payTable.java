package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class payTable {
    private final Map<Integer, Integer> odds = Map.ofEntries(
            new AbstractMap.SimpleEntry<Integer, Integer>(0, 0),
            new AbstractMap.SimpleEntry<Integer, Integer>(1, 10),
            new AbstractMap.SimpleEntry<Integer, Integer>(2, 40),
            new AbstractMap.SimpleEntry<Integer, Integer>(3, 100)
    );

    public payTable() {
    }

    public int getOdd(List<List<String>> screen) {
        int Lines = getLines(new Screen(screen));

        return getOdd(Lines);
    }

    private int getLines(Screen screen) {
        int Lines = 0;
        for (int i = 0; i < 3; i++) {

            int finalI = i;
            Set<String> distinctSymbols = screen.rawScreen().stream().map(wheel -> wheel.get(finalI)).collect(Collectors.toSet());

            if (distinctSymbols.size() == 1) {
                Lines++;
            }
        }
        return Lines;
    }

    private int getOdd(int lines) {

        Integer odd = odds.get(lines);

        if (Objects.isNull(odd)) {
            throw new RuntimeException("Unsupported lines");
        }

        return odd;
    }
}