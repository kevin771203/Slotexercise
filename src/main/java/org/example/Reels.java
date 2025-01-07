package org.example;

import java.util.ArrayList;
import java.util.List;


public final class Reels {

    private final List<Reel> reelList = new ArrayList<>();
    private final RandomNumberGenerator randomNumberGenerator;


    public Reels(List<List<String>> rawReels, RandomNumberGenerator randomNumberGenerator) {

        this.randomNumberGenerator = randomNumberGenerator;

        for (List<String> rawReel : rawReels) {
            Reel reel = new Reel(rawReel);
            reelList.add(reel);
        }

    }

    public void spin() {

        for (Reel reel : reelList) {
            reel.roll(randomNumberGenerator);
        }

    }

    public Screen getScreen() {

        List<List<String>> rawScreen = reelList.stream().map(
                reel -> reel.getScreenColumn(3)
        ).toList();

        return new Screen(rawScreen);
    }
}

