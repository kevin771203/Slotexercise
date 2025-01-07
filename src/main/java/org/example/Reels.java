package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public final class Reels {

//    private final List<List<String>> rawReels;
    private final List<Reel> reelList = new ArrayList<>();
    private final RandomNumberGenerator randomNumberGenerator;
//    private Screen screen;

    public Reels(List<List<String>> rawReels, RandomNumberGenerator randomNumberGenerator) {
//        this.rawReels = rawReels;
        this.randomNumberGenerator = randomNumberGenerator;

        for (List<String> rawReel : rawReels) {
            Reel reel = new Reel(rawReel);
            reelList.add(reel);
        }

//        List<List<String>> rawScreen = rawReels().stream().map(
//                reel -> {
//                    int nextPosition = 0;
//
//                    return Stream.concat(reel.stream(), reel.stream()).toList().subList(
//                            nextPosition, nextPosition + 3
//                    );
//                }
//        ).toList();

//        this.screen = new Screen(rawScreen);

    }

    public void spin() {

        for (Reel reel : reelList) {
            reel.roll(randomNumberGenerator);
        }
//        List<List<String>> rawScreen = rawReels().stream().map(
//                reel -> {
//                    int nextPosition = randomNumberGenerator.nextInt(reel.size()); //command
//
//                    return Stream.concat(reel.stream(), reel.stream()).toList().subList( // mid
//                            nextPosition, nextPosition + 3
//                    );
//                }
//        ).toList();

//        this.screen = new Screen(rawScreen); // query
    }

//    List<List<String>> rawReels() {
//        return rawReels;
//    }


    public Screen getScreen() {

        List<List<String>> rawScreen = reelList.stream().map(
                reel -> reel.getScreenColumn(3)
        ).toList();

        return new Screen(rawScreen);
    }
}

