package com.bri64.collections;

import java.util.Random;

public class RandomMock extends Random {
    private Boolean current = null;

    @Override
    public boolean nextBoolean() {
        return (current == null) ? (current = super.nextBoolean()) : (current = !current);
    }
}
