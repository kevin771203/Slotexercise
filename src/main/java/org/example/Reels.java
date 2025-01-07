package org.example;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public final class Reels {
    private final List<List<String>> rawReels;

    public Reels(List<List<String>> rawReels, Random random) {
        this.rawReels = rawReels;
        randomNumberGenerator = new RandomNumberGenerator(random);
    }

    private final RandomNumberGenerator randomNumberGenerator;

    Screen reelsToScreen() {
        List<List<String>> rawScreen = rawReels().stream().map(
                reel -> {
                    int nextPosition = randomNumberGenerator.nextInt(reel.size());

                    return Stream.concat(reel.stream(), reel.stream()).toList().subList(
                            nextPosition, nextPosition + 3
                    );
                }
        ).toList();

        return new Screen(rawScreen);
    }

    List<List<String>> rawReels() {
        return rawReels;
    }

    private int nextInt(int bound) {
        return randomNumberGenerator.nextInt(bound);
    }


}