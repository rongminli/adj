package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.priorityqueue.MinHeap;

public class Leetcode215 {
    public int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > heap.peek()) {
                heap.replace(nums[i]);
            }
        }
        return heap.peek();
    }
}
