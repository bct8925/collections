package com.bri64.collections;

import java.util.List;

public class ListNode<T> {
    private T value;
    private ListNode<T> next = null;

    ListNode(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    ListNode<T> getNext() {
        return next;
    }

    void setNext(ListNode<T> next) {
        this.next = next;
    }

    void push(T value) {
        if (next == null) {
            next = new ListNode<>(value);
        } else {
            next.push(value);
        }
    }

    ListNode<T> get(int index, int current_index) {
        if (current_index == index) return this;
        return (next != null) ? next.get(index, current_index + 1) : null;
    }

    int getIndex(T value, int current_index) {
        if (value.equals(this.value)) return current_index;
        return (next != null) ? next.getIndex(value, current_index + 1) : -1;
    }

    int size() {
        return (next != null) ? 1 + next.size() : 1;
    }

    ListNode<T> remove(int index, int current_index) {
        if (current_index == index) {
            return next;
        }
        if (next != null) next = next.remove(index, current_index + 1);
        return this;
    }

    ListNode<T> peekLast() {
        return (next != null) ? next.peekLast() : this;
    }

    List<ListNode<T>> nodeList(List<ListNode<T>> traversal) {
        traversal.add(this);
        if (next != null) next.nodeList(traversal);
        return traversal;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
