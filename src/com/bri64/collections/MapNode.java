package com.bri64.collections;

class MapNode<K,V> {
    private K key;
    private V value;
    private Integer hash;

    MapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
