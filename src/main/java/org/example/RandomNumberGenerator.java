package org.example;

import java.util.Random;

public class RandomNumberGenerator {
    private final Random random;

    public RandomNumberGenerator(Random random) {

        this.random = random;
    }

    public int nextInt(int bound) {

        return random.nextInt(bound);
    }

    private Random random() {
        return random;
    }
}