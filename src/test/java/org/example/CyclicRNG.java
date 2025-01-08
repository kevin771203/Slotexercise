package org.example;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CyclicRNG implements RandomNumberGenerator {

    private Queue<Integer> expecteValues = new ArrayDeque<>();

    public void resetExpectedValues(List<Integer> expecteds) {

        this.expecteValues.clear();

        this.expecteValues.addAll(expecteds);

    }

    @Override
    public int nextInt(int bound) {
        Integer polled = expecteValues.remove();

        expecteValues.add(polled);

        return polled;

    }
}
