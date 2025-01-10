package org.example;

import java.util.List;

public class GongXiFaCaiTriggeringRules implements FreeGameTriggeringRules {

    public GongXiFaCaiTriggeringRules() {

    }

    @Override
    public int getCount() {return 3;}

    @Override
    public boolean check(Screen screen) {
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