package com.bri64.collections;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class LinkedList<T> implements Iterable<T> {
    protected ListNode<T> head = null;

    ListNode<T> getHead() {
        return head;
    }

    public LinkedList() {}

    public LinkedList(T head) {
        push(head);
    }

    public LinkedList(Iterable<T> c) {
        pushAll(c);
    }
    
    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return (!isEmpty()) ? head.size() : 0;
    }

    public int indexOf(T value) {
        return (!isEmpty()) ? head.getIndex(value, 0) : -1;
    }
    
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public T peek() {
        return (!isEmpty()) ? head.getValue() : null;
    }

    public T peekLast() {
        return (!isEmpty()) ? head.peekLast().getValue() : null;
    }

    public T get(int index) {
        return (!isEmpty() && index >= 0) ? head.get(index, 0).getValue() : null;
    }

    public void push(T value) {
        if (isEmpty()) {
            head = new ListNode<>(value);
        } else {
            head.push(value);
        }
    }

    public void pushAll(Iterable<T> c) {
        for (T value : c) {
            push(value);
        }
    }

    public void unshift(T value) {
        if (isEmpty()) {
            head = new ListNode<>(value);
        } else {
            ListNode<T> node = new ListNode<>(value);
            node.setNext(head);
            head = node;
        }
    }

    public T pop() {
        if (!isEmpty()) {
            T value = peek();
            remove(0);
            return value;
        }
        return null;
    }

    public T popLast() {
        if (!isEmpty()) {
            T value = peekLast();
            remove(size() - 1);
            return value;
        }
        return null;
    }

    public void clear() {
        head = null;
    }

    public T remove(int index) {
        T value = get(index);
        if (value != null) head = head.remove(index, 0);
        return value;
    }

    public T removeFirst(T value) {
        int index = indexOf(value);
        return remove(index);
    }

    List<ListNode<T>> asNodeList() {
        return (!isEmpty()) ? head.nodeList(new java.util.LinkedList<>()) : new java.util.LinkedList<>();
    }

    public List<T> asList() {
        return (!isEmpty()) ? asNodeList().stream().map(ListNode::getValue).collect(Collectors.toList()) : new java.util.LinkedList<>();
    }

    @Override
    public String toString() {
        return "[" + asNodeList().stream()
                .map(ListNode::getValue)
                .map(T::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }
}
