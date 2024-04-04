package site.lrm7.adj.datastructure.priorityqueue;


public class Heap {
    private int[] array;
    private int size;
    private    Boolean max;

    public Heap(int capacity, Boolean max) {
        this.array = new int[capacity];
        this.max = max;
    }

    public Heap(int[] array,Boolean max) {
        this.max = max;
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
        if (size == array.length) {
            grow();
        }
        up(offered);
        size++;
        return true;
    }

    private void grow() {
        int capacity = size + (size >> 1);
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0,
                        newArray, 0, size);
        this.array = newArray;
    }

    private Boolean compare(int v1, int v2) {
        return (max ? v1 > v2 : v1 < v2);
    }

    private void up(int offered) {
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && compare(offered, array[parent])) {
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
        if (left < size && compare(array[left], array[max])) {
            max = left;
        }
        if (right < size && compare(array[right], array[max])) {
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

    public int getSize() {
        return size;
    }
}
