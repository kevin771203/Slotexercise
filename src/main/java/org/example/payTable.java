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
    private final org.example.DBC DBC = new DBC();

    public int getOdd(Screen screen) {

        int Lines = screen.countStraightLines();

        return getOdd(Lines);
    }

    private Integer getOdd(int lines) {

        DBC.checkPreCondition(() -> odds.containsKey(lines), "Unsupported lines");

        return odds.get(lines);
    }


}