package site.lrm7.adj.datastructure.queue;

import org.springframework.lang.NonNull;
import site.lrm7.adj.datastructure.priorityqueue.Queue;

import java.util.Iterator;

public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private final Node<E> head = new Node<>(null, null);
    private Node<E> tail = head;
    private int size;
    private int capacity = Integer.MAX_VALUE;

    {
        tail.next = head;
    }

    public LinkedListQueue() {
    }

    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }


    @Override
    @NonNull
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (size == capacity) return false;
        Node<E> newNode = new Node<>(value, head);
        tail.next = newNode;
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty())
            return null;
        Node<E> result = head.next;
        head.next = head.next.next;
        if (result == tail)
            tail = head;
        size--;
        return result.value;
    }

    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return tail == head;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
