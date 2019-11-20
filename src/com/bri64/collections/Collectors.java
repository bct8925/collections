package com.bri64.collections;

import java.util.stream.Collector;

@SuppressWarnings("WeakerAccess")
public class Collectors {
    public static <T> Collector<T, ?, LinkedList<T>> toList() {
        return Collector.of(
                LinkedList::new,
                LinkedList::push,
                (left, right) -> { left.pushAll(right); return left; });
    }
}
