package com.bri64.collections;

public class MapNode<V> {
    Integer hash;
    V value;

    public MapNode(V value) {
        this.value = value;
        this.hash = value.hashCode();
    }

    public V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
