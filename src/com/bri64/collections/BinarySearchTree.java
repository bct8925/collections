package com.bri64.collections;

import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root = null;

    public BinarySearchTree() {}

    public BinarySearchTree(T root) {
        insert(root);
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

    public boolean contains(T value) {
        return !isEmpty() && root.contains(value);
    }

    public void remove(T value) {
        if (!isEmpty()) root = root.remove(value);
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

    @Override
    public String toString() {
        return "[" + inOrder().asList().stream()
                .map(T::toString)
                .collect(Collectors.joining(", ")) + "]";
    }
}
