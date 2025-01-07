package org.example;

import java.util.List;
import java.util.stream.Stream;


public final class Reels {
    private final List<List<String>> rawReels;
    private final RandomNumberGenerator randomNumberGenerator;
    public Reels(List<List<String>> rawReels, RandomNumberGenerator randomNumberGenerator) {
        this.rawReels = rawReels;
        this.randomNumberGenerator = randomNumberGenerator;
    }


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


}