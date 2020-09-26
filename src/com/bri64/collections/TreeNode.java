package com.bri64.collections;

import java.util.Random;

class TreeNode<T extends Comparable<T>> {
    static Random RAND = new Random();

    private T value;
    private TreeNode<T> parent;
    private TreeNode<T> left = null;
    private TreeNode<T> right = null;
    private NodeColor color = NodeColor.RED;

    TreeNode(TreeNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;

        if (parent == null) {
            this.color = NodeColor.BLACK;
        }
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
                left = new TreeNode<>(this, value);
                left.repairTree();
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new TreeNode<>(this, value);
                right.repairTree();
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

    public enum NodeColor {
        RED, BLACK
    }

    NodeColor getColor() {
        return color;
    }

    private TreeNode<T> getParent() {
        return parent;
    }

    TreeNode<T> getRoot() {
        return (getParent() == null) ? this : getParent().getRoot();
    }

    private TreeNode<T> getSibling() {
        if (getParent() == null) {
            return null;
        }
        return (getParent().left == this) ? getParent().right : getParent().left;
    }

    private TreeNode<T> getUncle() {
        return (getParent() == null) ? null : getParent().getSibling();
    }

    private TreeNode<T> getGrandparent() {
        return (getParent() == null) ? null : getParent().getParent();
    }

    private void repairTree() {
        TreeNode<T> p = getParent();
        TreeNode<T> g = getGrandparent();
        TreeNode<T> u = getUncle();

        if (p == null) {
            color = NodeColor.BLACK;
        } else if (p.getColor() == NodeColor.BLACK) {
            color = getColor();
        } else if (u != null && u.getColor() == NodeColor.RED) {
            p.color = NodeColor.BLACK;
            u.color = NodeColor.BLACK;
            g.color = NodeColor.RED;
            g.repairTree();
        } else {
            TreeNode<T> n = this;
            if (n == p.right && p == g.left) {
                p.rotateLeft();
                n = n.left;
            } else if (n == p.left && p == g.right) {
                p.rotateRight();
                n = n.right;
            }
            n.repairTree2();
        }
    }

    private void repairTree2() {
        TreeNode<T> p = getParent();
        TreeNode<T> g = getGrandparent();

        if (this == p.left) {
            g.rotateRight();
        } else {
            g.rotateLeft();
        }

        p.color = NodeColor.BLACK;
        g.color = NodeColor.RED;
    }

    private void rotateLeft() {
        TreeNode<T> n = this;
        TreeNode<T> nnew = n.right;
        TreeNode<T> p = n.getParent();
        if (nnew == null) {
            return;
        }

        n.right = nnew.left;
        nnew.left = n;
        n.parent = nnew;

        if (n.right != null) {
            n.right.parent = n;
        }

        if (p != null) {
            if (n == p.left) {
                p.left = nnew;
            } else if (n == p.right) {
                p.right = nnew;
            }
        }
        nnew.parent = p;
    }

    private void rotateRight() {
        TreeNode<T> n = this;
        TreeNode<T> nnew = n.left;
        TreeNode<T> p = n.getParent();
        if (nnew == null) {
            return;
        }

        n.left = nnew.right;
        nnew.right = n;
        n.parent = nnew;

        if (n.left != null) {
            n.left.parent = n;
        }

        if (p != null) {
            if (n == p.left) {
                p.left = nnew;
            } else if (n == p.right) {
                p.right = nnew;
            }
        }
        nnew.parent = p;
    }
}
