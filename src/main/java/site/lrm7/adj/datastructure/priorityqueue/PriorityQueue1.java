package site.lrm7.adj.datastructure.priorityqueue;


public class PriorityQueue1<E extends Priority> implements Queue<E> {
    Priority[] array;
    int size;

    public PriorityQueue1(int capacity) {
        this.array = new Priority[capacity];
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) return false;
        array[size++] = value;
        return true;
    }

    // 返回优先级最高的索引值
    private int selectMax() {
        int max = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority() > array[max].priority()) {
                max = i;
            }
        }
        return max;
    }

    private void remove(int index) {
        if (index < size - 1) {
            System.arraycopy(array, index + 1,
                    array, index, size - index - 1);
        }
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        int max = selectMax();
        E result = (E) array[max];
        remove(max);
        array[--size] = null;
        return result;

    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return (E) array[selectMax()];
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
