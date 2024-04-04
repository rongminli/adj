package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.priorityqueue.MinHeap;

public class Leetcode703 {

    private MinHeap heap;

    public Leetcode703(int k, int[] nums) {
        heap = new MinHeap(k);
        for (int num : nums) {
            this.add(num);
        }
    }

    public int add(int val) {
        if(heap.isFull()){
            if(val > heap.peek()) {
                heap.replace(val);
            }
        }else {
            heap.offer(val);
        }

        return heap.peek();
    }
}
