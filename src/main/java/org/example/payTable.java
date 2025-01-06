package org.example;

import java.util.*;
import java.util.function.Supplier;

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

        Supplier<Boolean> preCondition = () -> odds.containsKey(lines);
        checkPreCondition((Supplier<Boolean>) preCondition);

        return odds.get(lines);
    }

    private void checkPreCondition(Supplier<Boolean> preCondition) {
        if (!(boolean) preCondition.get()) {
            throw new RuntimeException("Unsupported lines");
        }
    }


}