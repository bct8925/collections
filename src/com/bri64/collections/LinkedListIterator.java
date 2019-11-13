package com.bri64.collections;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {
    private ListNode<T> cursor;

    LinkedListIterator(LinkedList<T> list) {
        this.cursor = (list != null) ? list.getHead() : null;
    }

    @Override
    public boolean hasNext() {
        return cursor != null;
    }

    @Override
    public T next() {
        T value = cursor.getValue();
        cursor = cursor.getNext();
        return value;
    }
}
