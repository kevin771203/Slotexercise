package org.example;

import java.util.List;
import java.util.stream.Stream;

public class Reel {

    private final List<String> grids;
    private int nextPosition;

    public Reel(List<String> grids) {
        this.grids = grids;
    }

    public void roll(RandomNumberGenerator randomNumberGenerator) {

        //command
        nextPosition = randomNumberGenerator.nextInt(grids.size());
    }

    public List<String> getScreenColumn(int columnSize) {
        return Stream.concat(grids.stream(), grids.stream()).toList()
                .subList( nextPosition, nextPosition + columnSize);
    }
}
