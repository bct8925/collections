package com.bri64.collections;

import java.util.Random;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class BinarySearchTree<T extends Comparable<T>> {
    protected TreeNode<T> root = null;

    public BinarySearchTree() {}

    public BinarySearchTree(T root) {
        insert(root);
    }

    public BinarySearchTree(BinarySearchTree<T> bst) {
        this(bst.inOrder());
    }

    public BinarySearchTree(Iterable<T> c) {
        insertAll(c);
    }
    
    public boolean isEmpty() {
        return root == null;
    }

    public T getRoot() {
        return (!isEmpty()) ? root.getValue() : null;
    }

    TreeNode<T> getRootNode() {
        return root;
    }

    public void insert(T value) {
        if (isEmpty()) {
            root = new TreeNode<>(value);
        } else {
            root.insert(value);
        }
    }

    public void insertAll(Iterable<T> c) {
        for (T value : c) {
            insert(value);
        }
    }

    public boolean contains(T value) {
        return !isEmpty() && root.contains(value);
    }

    public void remove(T value) {
        if (!isEmpty()) root = root.remove(value);
    }

    public void clear() {
        root = null;
    }

    public LinkedList<T> inOrder() {
        return (!isEmpty()) ? root.inOrder(new LinkedList<>()) : new LinkedList<>();
    }

    public LinkedList<T> preOrder() {
        return (!isEmpty()) ? root.preOrder(new LinkedList<>()) : new LinkedList<>();
    }

    public LinkedList<T> postOrder() {
        return (!isEmpty()) ? root.postOrder(new LinkedList<>()) : new LinkedList<>();
    }

    public BinarySearchTree<T> copy() {
        return new BinarySearchTree<>(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinarySearchTree<?> that = (BinarySearchTree<?>) o;
        return this.inOrder().equals(that.inOrder());
    }

    @Override
    public String toString() {
        return "[" + inOrder().asList().stream()
                .map(T::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

    static void setRAND(Random rand) {
        TreeNode.RAND = rand;
    }
}
