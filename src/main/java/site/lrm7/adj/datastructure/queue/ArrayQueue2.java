package site.lrm7.adj.datastructure.queue;

import site.lrm7.adj.datastructure.priorityqueue.Queue;

import java.util.Iterator;

public class ArrayQueue2<E> implements Queue<E>, Iterable<E> {
    private E[] array;
    private int head = 0;
    private int size = 0;

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private int tail = 0;

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E vlaue = array[p];
                p = (p + 1) % array.length;
                count++;
                return vlaue;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        array[tail] = value;
        tail = (tail + 1) % array.length;
        size ++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;

        E value = array[head];
        head = (head + 1) % 5;
        size --;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
