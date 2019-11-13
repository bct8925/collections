package com.bri64.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

class CollectionsTests {

    @BeforeEach
    void setRandomMock() {
        Utils.setRAND(new RandomMock());
    }

    @Test
    void test_LinkedList() {
        LinkedList<Integer> list = new LinkedList<>(7);

        assertEquals(1, list.size(), "List size should be 1");

        list.clear();

        assertEquals(0, list.size(), "List should have been cleared");

        list = new LinkedList<>();

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

        list.removeFirst(2);
        assertTrue(list.contains(2), "List should still contain 2");

        assertEquals(1, list.pop(), "Popped element should be 1");
        assertEquals(7, list.popLast(), "Popped last element should be 7");
        assertFalse(list.contains(1), "List should no longer contain 1");
        assertFalse(list.contains(7), "List should no longer contain 7");

        assertEquals("[2, 3, 4, 5, 6]", list.toString(), "List is not correct");

        int i = 0;
        for (Integer value : list) {
            assertEquals(list.get(i), value, "List iterator not in correct order");
            i++;
        }

        List<Integer> test = List.of(1,2,3);

        assertEquals(test, new LinkedList<>(test).asList(), "List created from Java List should be equal");

        assertEquals("2", list.asNodeList().get(0).toString(), "First node string should be \"2\"");
    }

    @Test
    void test_LinkedSet() {
        LinkedList<Integer> list = new LinkedSet<>();

        assertTrue(list.isEmpty(), "Set should be empty");

        list = new LinkedSet<>(1);

        list.unshift(1);
        list.push(2);
        list.push(2);
        list.push(3);

        assertEquals("[1, 2, 3]", list.toString(), "Set is not correct");

        Set<Integer> test = Set.of(1,2,3);

        assertEquals(test, new LinkedSet<>(test).asSet(), "Set created from Java Set should be equal");

        assertEquals("1", list.asNodeList().get(0).toString(), "First node string should be \"1\"");
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

        assertEquals("4", bst.getRootNode().toString(), "Root node string should be \"4\"");
    }

    @Test
    void test_HashMap() {

    }
}