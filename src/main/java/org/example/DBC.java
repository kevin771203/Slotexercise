package org.example;

import java.util.function.Supplier;

public class DBC {

    public static void checkPreCondition(Supplier<Boolean> preCondition, String message) {
        if (!preCondition.get()) {
            throw new RuntimeException(message);
        }
    }
}