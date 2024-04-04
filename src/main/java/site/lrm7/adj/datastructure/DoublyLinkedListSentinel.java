package site.lrm7.adj.datastructure;

import java.util.Iterator;

public class DoublyLinkedListSentinel implements Iterable<Integer> {

    static class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    private final Node head;
    private final Node tail;

    public  DoublyLinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException("错误的索引位置： " + index);
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw illegalIndex(index);
        }
        Node next = prev.next;
        prev.next = new Node(prev, value, next);
        next.prev = prev.next;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void remove(int index) {
        Node removed = findNode(index);
        if (removed == null) throw  illegalIndex(index);
        if (removed == tail) throw illegalIndex(index);
        if (removed == head) throw illegalIndex(index);
        Node prev = removed.prev;
        Node next = removed.next;
        prev.next = next;
        next.prev = prev;
    }

    public void removeFirst() {
        remove(0);
    }

    public void addLast(int value) {
        Node prev = tail.prev;
        tail.prev = new Node(prev, value, tail);
        prev.next = tail.prev;
    }
    public void removeLast() {
        Node last = tail.prev;
        if (last == head) throw illegalIndex(0);
        Node prev = last.prev;
        prev.next = tail;
        tail.prev = prev;
    }
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = head.next;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                Node result = p;
                p = p.next;
                return result.value;
            }
        };
    }
}
