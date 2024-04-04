package site.lrm7.adj.datastructure.priorityqueue;

public class PriorityQueue3<E extends Priority> implements Queue<E> {
    private final Priority[] array;
    private int size;

    public PriorityQueue3(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E offered) {
        if (isFull()) return false;
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered.priority() > array[parent].priority()) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }

    private void swap(int i, int j) {
        Priority t = array[i];
        array[i] = array[j];
        array[j] = t;

    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        swap(0, size - 1);
        size--;
        Priority result = array[size];
        array[size] = null;
        down(0);
        return (E) result;
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 2;
        int max = parent;
        if (left < size && array[left].priority() > array[parent].priority()) {
            max = left;
        }
        if (right < size && array[right].priority() > array[parent].priority()) {
            max = right;
        }
        if(max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[0];
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

