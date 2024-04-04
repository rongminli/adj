package site.lrm7.adj.datastructure.queue;

import site.lrm7.adj.datastructure.priorityqueue.Queue;

import java.util.Iterator;

public class ArrayQueue3<E> implements Queue<E>, Iterable<E> {
    private final E[] array;
    private int head = 0;
    private int tail = 0;

    @SuppressWarnings("")
    public ArrayQueue3(int capacity) {
        if ((capacity & capacity -1) != 0) {
            int c = 1;
            capacity --;
            while ((capacity = capacity >>> 1) > 0) {
                c = c << 1;
            }
            capacity = c << 1;
        }
        array = (E[]) new Object[capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p < tail;
            }

            @Override
            public E next() {
                E vlaue = array[p & array.length -1];
                p++;
                return vlaue;
            }
        };
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        array[tail & array.length -1] = value;
        tail++;
        if (tail == Integer.MAX_VALUE) {
            int val = head % array.length;
            tail = tail - head + val;
            head = val;
        }
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;

        E value = array[head & array.length -1];
        head++;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return array[head & array.length -1];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == array.length;
    }

    public static void main(String[] args) {
        int a = 8;
        int c = 1;
        a = a -1;
        while ((a = a >>> 1) > 0) {
            c = c << 1;
        }
        a = c << 1;
        System.out.println(a);

    }
}
