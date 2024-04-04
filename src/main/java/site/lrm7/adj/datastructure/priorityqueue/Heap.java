package site.lrm7.adj.datastructure.priorityqueue;

import java.util.Comparator;

public class Heap<E> {
    private E[] array;
    private int size;
    private Comparator<E>  cmp;

    @SuppressWarnings("unchecked")
    public Heap(int capacity, Comparator<E> comparator) {
        this.array = (E[]) new Object[capacity];
        this.cmp = comparator;
    }

    public Heap(E[] array, Comparator<E> comparator) {
        this.cmp = comparator;
        this.array = array;
        this.size = this.array.length;
        headify();
    }

    //建堆
    private void headify() {
        // 找到最后的非叶子节点 size / 2 -1
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(i);
        }
    }

    public boolean offer(E offered) {
        if (size == array.length) {
            grow();
        }
        up(offered);
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int capacity = size + (size >> 1);
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(array, 0,
                        newArray, 0, size);
        this.array = newArray;
    }

    private void up(E offered) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && cmp.compare(offered, array[parent]) > 0) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
    }

    public E peek() {
        if (size == 0) throw new RuntimeException();
        return array[0];
    }

    public void replace(E replaced) {
        array[0] = replaced;
        down(0);
    }

    public E poll(int index) {
        E deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    public E poll() {
        if (isEmpty()) return null;
        swap(0, size - 1);
        size--;
        E result = array[size];
        array[size] = null;
        down(0);
        return result;
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && cmp.compare(array[left], array[max]) > 0) {
            max = left;
        }
        if (right < size && cmp.compare(array[left], array[max]) > 0) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
        }
    }

    private void swap(int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int getSize() {
        return size;
    }
}
