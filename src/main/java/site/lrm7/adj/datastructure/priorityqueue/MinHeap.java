package site.lrm7.adj.datastructure.priorityqueue;

public class MinHeap {
    int[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    public boolean offer(int offered) {
        if (isFull()) return false;
        int child = size++;
        int parent = (child - 1) / 2;
        while (child > 0 && offered < array[parent]) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
        return true;
    }

    public int poll() {
        if (isEmpty()) return 0;
        swap(0, size - 1);
        size--;
        int result = array[size];
        down(0);
        return result;
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int min = parent;
        if (left < size && array[left] < array[min]) {
            min = left;
        }
        if (right < size && array[right] < array[min]) {
            min = right;
        }
        if (min != parent) {
            swap(min, parent);
            down(min);
        }
    }

    private void swap(int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public int peek() {
        return array[0];
    }
    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    public int getSize() {
        return size;
    }
}
