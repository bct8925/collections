package com.bri64.collections;

import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class LinkedSet<T> extends LinkedList<T> {
    public LinkedSet() {
        super();
    }

    public LinkedSet(Iterable<T> c) {
        super(c);
    }

    @Override
    public void push(T value) {
        if (!contains(value)) super.push(value);
    }

    @Override
    public void unshift(T value) {
        if (!contains(value)) super.unshift(value);
    }

    public LinkedSet<T> copy() {
        return new LinkedSet<>(this);
    }

    public Set<T> asSet() {
        return stream().collect(Collectors.toSet());
    }
}
