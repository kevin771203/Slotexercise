package org.example;

import java.util.List;

public class FreeGameTriggeringRules {
    public static int getFreeGameCount() {
        return 3;
    }

    public static boolean checkTriggeringRules(Screen screen) {
        int count = 0;
        for (List<String> rawColumns : screen.rawScreen()) {
            for (String grid : rawColumns) {
                if (grid.equals("A")) {
                    count++;
                }
            }
        }

        return count >= 10;
    }
}