package site.lrm7.adj.datastructure.stack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {

    @Test
    void push() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertIterableEquals(List.of(1,2,3,4).reversed(), stack);
    }

    @Test
    void pop() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.pop());
        assertIterableEquals(List.of(1,2,3).reversed(), stack);
        stack.pop();
        stack.pop();
        assertEquals(1, stack.pop());
    }

    @Test
    void peek() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.peek());
        assertEquals(4, stack.peek());
        assertIterableEquals(List.of(1,2,3,4).reversed(), stack);
        assertEquals(4, stack.pop());
    }

    @Test
    void isEmpty() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(4);
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void isFull() {
        LinkedListStack<Integer> stack = new LinkedListStack<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertTrue(stack.isFull());
        assertFalse(stack.push(0));
    }
}