package com.bri64.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsTests {

    @BeforeEach
    void setRandomMock() {
        BinarySearchTree.setRAND(new RandomMock());
    }

    @Test
    void test_LinkedList() {
        LinkedList<Integer> list = new LinkedList<>();

        assertTrue(list.isEmpty(), "List should be empty");
        assertEquals(0, list.size(), "List size should be 0");
        assertFalse(list.contains(7), "List should not contain 7");
        assertNull(list.pop(), "Popped element should be null");
        assertNull(list.popLast(), "Popped last element should be null");

        list.unshift(2);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        list.push(6);
        list.push(7);
        list.unshift(1);

        assertFalse(list.isEmpty(), "List should no longer be empty");
        assertEquals(8, list.size(), "List size should be 8");
        assertTrue(list.contains(5), "List should contain 5");
        assertEquals(1, list.peek(), "First should be 1");
        assertEquals(7, list.peekLast(), "Last should be 7");
        assertEquals(1, list.get(0), "Index 0 should be 1");
        assertEquals("[1, 2, 2, 3, 4, 5, 6, 7]", list.toString(), "List is not correct");

        assertEquals(2, list.removeFirst(2), "Removed element should be 2");
        assertTrue(list.contains(2), "List should still contain 2");

        assertEquals(1, list.pop(), "Popped element should be 1");
        assertEquals(7, list.popLast(), "Popped last element should be 7");
        assertFalse(list.contains(1), "List should no longer contain 1");
        assertFalse(list.contains(7), "List should no longer contain 7");

        assertEquals("[2, 3, 4, 5, 6]", list.toString(), "List is not correct");

        list = list.reverse();

        assertEquals("[6, 5, 4, 3, 2]", list.toString(), "List is not correct");

        list = list.stream().parallel().map((i) -> i+1).collect(Collectors.toList());

        assertEquals("[7, 6, 5, 4, 3]", list.toString(), "List is not correct");

        list = list.sort();

        assertEquals("[3, 4, 5, 6, 7]", list.toString(), "List is not correct");

        /*int sum = list.stream().reduce((i) -> i+1).collect(Collectors.toList());

        assertEquals("[7, 6, 5, 4, 3]", list.toString(), "List is not correct");*/

        int i = 0;
        for (Integer value : list) {
            assertEquals(list.get(i), value, "List iterator not in correct order");
            i++;
        }

        LinkedList<Integer> list2 = list.copy();

        assertEquals(list, list2, "Copied List should be equal");

        list.clear();

        assertEquals(0, list.size(), "List should have been cleared");
    }

    @Test
    void test_LinkedList_compat() {
        List<Integer> test = List.of(1,2,3);

        assertEquals(test, new LinkedList<>(test).asList(), "List created from Java List should be equal");
    }

    @Test
    void test_LinkedSet() {
        LinkedSet<Integer> set = new LinkedSet<>();

        assertTrue(set.isEmpty(), "Set should be empty");

        set.push(1);
        set.unshift(1);
        set.push(2);
        set.push(2);
        set.push(3);

        assertEquals("[1, 2, 3]", set.toString(), "Set is not correct");

        LinkedSet<Integer> set2 = set.copy();

        assertEquals(set, set2, "Copied Set should be equal");
    }

    @Test
    void test_LinkedSet_compat() {
        Set<Integer> test = Set.of(1,2,3);

        assertEquals(test, new LinkedSet<>(test).asSet(), "Set created from Java Set should be equal");
    }

    @Test
    void test_BST() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        assertTrue(bst.isEmpty(), "BST should be empty");
        assertNull(bst.getRoot(), "Root should be null");

        bst = new BinarySearchTree<>(4);
        bst.insert(2);
        bst.insert(3);
        bst.insert(1);
        bst.insert(6);
        bst.insert(5);
        bst.insert(7);

        assertFalse(bst.isEmpty(), "BST should no longer be empty");
        assertEquals(4, bst.getRoot(), "Root should be 4");
        assertEquals(4, bst.getRootNode().getValue(), "Root node's value should be 4");
        assertEquals(2, bst.getRootNode().getLeft().getValue(), "Left of root should be 2");
        assertEquals(6, bst.getRootNode().getRight().getValue(), "Left of root should be 6");
        assertTrue(bst.contains(5), "BST should contain 5");

        assertEquals("[1, 2, 3, 4, 5, 6, 7]", bst.inOrder().toString(), "InOrder is not correct");
        assertEquals("[4, 2, 1, 3, 6, 5, 7]", bst.preOrder().toString(), "PreOrder is not correct");
        assertEquals("[1, 3, 2, 5, 7, 6, 4]", bst.postOrder().toString(), "PostOrder is not correct");

        bst.remove(2);
        bst.remove(6);

        assertFalse(bst.contains(2), "BST should not contain 2");
        assertFalse(bst.contains(6), "BST should not contain 6");
        assertEquals("[1, 3, 4, 5, 7]", bst.toString(), "InOrder is not correct");

        BinarySearchTree<Integer> bst2 = bst.copy();

        assertEquals(bst, bst2, "Copied BST should be equal");

        bst.clear();

        assertTrue(bst.isEmpty(), "BST should have been cleared");
    }

    @Test
    void test_HashMap() {

    }
}
