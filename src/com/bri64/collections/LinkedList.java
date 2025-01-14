package com.bri64.collections;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("WeakerAccess")
public class LinkedList<T> implements Iterable<T> {
    protected ListNode<T> head = null;

    public LinkedList() {}

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

    public boolean containsAll(Iterable<T> values) {
        for (T value : values) {
            if (!contains(value)) return false;
        }
        return true;
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
        return remove(indexOf(value));
    }

    public LinkedList<T> reverse() {
        LinkedList<T> reversed = new LinkedList<>();
        for (T value : this) {
            reversed.unshift(value);
        }
        return reversed;
    }

    public LinkedList<T> copy() {
        return new LinkedList<>(this);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    public List<T> asList() {
        return stream().collect(java.util.stream.Collectors.toList());
    }

    // May throw exception if T does not implement Comparable<T>
    public LinkedList<T> sort() {
        return stream().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        LinkedList<?> that = (LinkedList<?>) o;
        return this.asList().equals(that.asList());
    }

    @Override
    public String toString() {
        return "[" + stream()
                .map(T::toString)
                .collect(java.util.stream.Collectors.joining(", ")) + "]";
    }

    private static class LinkedListIterator<T> implements Iterator<T> {
        private ListNode<T> cursor;

        LinkedListIterator(LinkedList<T> list) {
            this.cursor = (list != null) ? list.head : null;
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
}
