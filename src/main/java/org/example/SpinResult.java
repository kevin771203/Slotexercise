package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpinResult {
    private final int value;

    private final Screen screen;
}
