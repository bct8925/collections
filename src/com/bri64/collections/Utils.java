package com.bri64.collections;

import java.util.Random;

public class Utils {
    private static Random _RAND = new Random();

    public static Random RAND() {
        return _RAND;
    }

    static void setRAND(Random rand) {
        _RAND = rand;
    }
}
