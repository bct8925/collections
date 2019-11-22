package com.bri64.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("WeakerAccess")
public class ArrayList<T> implements List<T> {
    protected ListNode<T> first = null;

    public ArrayList() {}

    public ArrayList(Collection<T> c) {
        addAll(c);
    }

    @Override
    public int size() {
        return isEmpty() ? 0 : first.size();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(Object o) {
        return !isEmpty() && indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return new java.util.ArrayList<>(this).toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return new java.util.ArrayList<>(this).toArray(a);
    }

    @Override
    public boolean add(T t) {
        if (isEmpty()) {
            first = new ListNode<>(t);
        } else {
            first.push(t);
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object value : c) {
            if (!contains(value)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T value : c) {
            add(value);
        }
        return true;
    }

    @Override // TODO
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object value : c) {
            remove(value);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for (T value : new ArrayList<>(this)) {
            if (!c.contains(value)) {
                remove(value);
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) return null;
        ListNode<T> node = first.get(index, 0);
        return (node == null) ? null : node.getValue();
    }

    @Override // TODO
    public T set(int index, T element) {
        return null;
    }

    @Override // TODO
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        T value = get(index);
        if (value != null) first = first.remove(index, 0);
        return value;
    }

    @Override
    public int indexOf(Object o) {
        T casted = cast(o);
        if (isEmpty() || casted == null) return -1;
        return first.getIndex(casted, 0);
    }

    @Override // TODO
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new java.util.ArrayList<>(this).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new java.util.ArrayList<>(this).listIterator(index);
    }

    @Override // TODO
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private T cast(Object o) {
        try {
            return (T) o;
        } catch (Exception e) {
            return null;
        }
    }

    private static class ArrayListIterator<T> implements Iterator<T> {
        private ListNode<T> cursor;

        ArrayListIterator(ArrayList<T> list) {
            this.cursor = (list != null) ? list.first : null;
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
