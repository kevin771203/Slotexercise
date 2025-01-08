package org.example;

public class freeGamePayTable {

    public static int getOdd(Screen screen) {
        int odd = 0;

        int lines = screen.countStraightLines();
        if (lines == 3) {
            odd = 500;
        } else if (lines == 2) {
            odd = 300;
        } else if (lines == 1) {
            odd = 100;
        }
        return odd;
    }
}