package site.lrm7.adj.datastructure.priorityqueue;


public class PriorityQueue2<E extends Priority> implements Queue<E> {
    private final Priority[] array;
    private int size;

    public PriorityQueue2(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        insert(value);
        size++;
        return true;
    }

    private void insert(E e) {
        int i = size - 1;
        while (i >= 0 && array[i].priority() > e.priority()) {
            array[i+1] = array[i];
            i--;
        }
        array[i + 1] = e;
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E e = (E) array[size -1];
        array[--size] = null;
        return e;

    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[size-1];
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
