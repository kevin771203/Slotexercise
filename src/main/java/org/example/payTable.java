package org.example;

import java.util.*;

public class payTable {
    private final Map<Integer, Integer> odds = Map.ofEntries(
            new AbstractMap.SimpleEntry<Integer, Integer>(0, 0),
            new AbstractMap.SimpleEntry<Integer, Integer>(1, 10),
            new AbstractMap.SimpleEntry<Integer, Integer>(2, 40),
            new AbstractMap.SimpleEntry<Integer, Integer>(3, 100)
    );

    public int getOdd(Screen screen) {

        int Lines = screen.countStraightLines();

        return getOdd(Lines);
    }

    private Integer getOdd(int lines) {

        if (!odds.containsKey(lines)) {
            throw new RuntimeException("Unsupported lines");
        }

        return odds.get(lines);
    }


}