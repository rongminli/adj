package site.lrm7.adj.datastructure.priorityqueue;

import java.util.Arrays;

public class MaxHeap {
    int[] array;
    int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MaxHeap(int[] array) {
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

    public boolean offer(int offered) {
        if (isFull()) return false;
        up(offered);
        size++;
        return true;
    }

    private void up(int offered) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && offered > array[parent]) {
            array[child] = array[parent];
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered;
    }

    public int peek() {
        if (size == 0) throw new RuntimeException();
        return array[0];
    }

    public void replace(int replaced) {
        array[0] = replaced;
        down(0);
    }

    public int poll(int index) {
        int deleted = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return deleted;
    }

    public int poll() {
        if (isEmpty()) return 0;
        swap(0, size - 1);
        size--;
        int result = array[size];
        array[size] = 0;
        down(0);
        return result;
    }

    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {
            swap(max, parent);
            down(max);
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


    public static void main(String[] args) {
        int[] array = {1, 5, 7, 8, 6, 3, 4, 5, 6, 9};
        MaxHeap heap = new MaxHeap(array);
        while (heap.size > 1) {
            heap.swap(0, heap.size - 1);
            heap.size--;
            heap.down(0);
        }
        System.out.println(Arrays.toString(array));
    }

    public int getSize() {
        return size;
    }
}
