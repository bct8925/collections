package com.bri64.collections;

public class HashMap<K,V> {
    LinkedSet<K> keys = new LinkedSet<>();
    LinkedList<MapNode<V>> values = new LinkedList<>();

    public HashMap() {}

    public int size() {
        return keys.size();
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }

    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    public boolean containsValue(V value) {
        for (MapNode<V> node : values) {
            if (node.value.equals(value)) return true;
        }
        return false;
    }

    public V get(K key) {
        int hash = key.hashCode();
        for (MapNode<V> node : values) {
            if (node.hash == hash) return node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        if (!keys.contains(key)) {
            keys.push(key);
            values.push(new MapNode<>(value));
        }
    }

    public V remove(K key) {
        int index = keys.indexOf(key);
        if (index != -1) {
            keys.remove(index);
            values.remove(index);
        }
        return null;
    }

    public LinkedSet<K> keySet() {
        return keys;
    }

    public LinkedList<V> values() {
        LinkedList<V> vals = new LinkedList<>();
        for (MapNode<V> node : values) {
            vals.push(node.value);
        }
        return vals;
    }
}
