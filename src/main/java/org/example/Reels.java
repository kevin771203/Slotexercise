package org.example;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public record Reels(List<List<String>> rawReels) {
    Screen reelsToScreen(Random random) {
        List<List<String>> rawScreen = rawReels().stream().map(
                reel -> {
                    int nextPosition = random.nextInt(reel.size());


                    return Stream.concat(reel.stream(), reel.stream()).toList().subList(
                            nextPosition, nextPosition + 3
                    );
                }
        ).toList();

        return new Screen(rawScreen);
    }
}