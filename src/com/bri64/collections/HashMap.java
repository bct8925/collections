package com.bri64.collections;

import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class HashMap<K,V> {
    protected LinkedList<Integer> keys = new LinkedList<>();
    protected LinkedList<MapNode<K,V>> values = new LinkedList<>();

    public HashMap() {}

    public HashMap(HashMap<K,V> hashMap) {
        for (MapNode<K,V> node : hashMap.values) {
            put(node.getKey(), node.getValue());
        }
    }

    public HashMap(Map<K,V> map) {
        for (K key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    public int size() {
        return keys.size();
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }

    public boolean containsKey(K key) {
        return keys.contains(key.hashCode());
    }

    public boolean containsValue(V value) {
        for (MapNode<K,V> node : values) {
            if (node.getValue().equals(value)) return true;
        }
        return false;
    }

    public V get(K key) {
        int hash = key.hashCode();
        for (MapNode<K,V> node : values) {
            if (node.hashCode() == hash) return node.getValue();
        }
        return null;
    }

    public void put(K key, V value) {
        int hash = key.hashCode();
        if (!keys.contains(hash)) {
            keys.push(hash);
            values.push(new MapNode<>(key, value));
        }
    }

    public V remove(K key) {
        int index = keys.indexOf(key.hashCode());
        if (index != -1) {
            keys.remove(index);
            values.remove(index);
        }
        return null;
    }

    public LinkedSet<K> keySet() {
        LinkedSet<K> keys = new LinkedSet<>();
        for (MapNode<K,V> node : values) {
            ((LinkedList<K>) keys).push(node.getKey());
        }
        return keys;
    }

    public LinkedList<V> values() {
        LinkedList<V> values = new LinkedList<>();
        for (MapNode<K,V> node : this.values) {
            values.push(node.getValue());
        }
        return values;
    }
}
