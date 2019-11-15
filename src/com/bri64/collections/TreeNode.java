package com.bri64.collections;

import java.util.Random;

class TreeNode<T extends Comparable<T>> {
    static Random RAND = new Random();

    private T value;
    private TreeNode<T> left = null;
    private TreeNode<T> right = null;

    TreeNode(T value) {
        this.value = value;
    }

    T getValue() {
        return value;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void insert(T value) {
        if (value.compareTo(this.value) <= 0) {
            if (left == null) {
                left = new TreeNode<>(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeNode<>(value);
            } else {
                right.insert(value);
            }
        }
    }

    boolean contains(T value) {
        if (value.compareTo(this.value) == 0) return true;
        if (left != null && value.compareTo(this.value) < 0) return left.contains(value);
        if (right != null && value.compareTo(this.value) > 0) return right.contains(value);
        return false;
    }

    TreeNode<T> remove(T value) {
        if (value.compareTo(this.value) < 0) {
            if (left != null) left = left.remove(value);
        }
        else if (value.compareTo(this.value) > 0) {
            if (right != null) right = right.remove(value);
        }
        else {
            if (left == null) return right;
            if (right == null) return left;

            if (RAND.nextBoolean()) {
                T rightMin = right.minValue();
                right = right.remove(rightMin);
                this.value = rightMin;
            } else {
                T leftMax = left.maxValue();
                left = left.remove(leftMax);
                this.value = leftMax;
            }
        }
        return this;
    }

    private T minValue() {
        return (left != null) ? left.minValue() : value;
    }

    private T maxValue() {
        return (right != null) ? right.maxValue() : value;
    }

    LinkedList<T> inOrder(LinkedList<T> traversal) {
        if (left != null) left.inOrder(traversal);
        traversal.push(value);
        if (right != null) right.inOrder(traversal);
        return traversal;
    }

    LinkedList<T> preOrder(LinkedList<T> traversal) {
        traversal.push(value);
        if (left != null) left.preOrder(traversal);
        if (right != null) right.preOrder(traversal);
        return traversal;
    }

    LinkedList<T> postOrder(LinkedList<T> traversal) {
        if (left != null) left.postOrder(traversal);
        if (right != null) right.postOrder(traversal);
        traversal.push(value);
        return traversal;
    }
}
