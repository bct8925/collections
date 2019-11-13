package com.bri64.collections;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class LinkedSet<T> extends LinkedList<T> {
    public LinkedSet() {
        super();
    }

    public LinkedSet(T head) {
        super(head);
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

    public Set<T> asSet() {
        return new HashSet<>(asList());
    }
}
