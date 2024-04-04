package site.lrm7.adj.leetcode;

import site.lrm7.adj.datastructure.priorityqueue.Heap;

class Leetcode295 {

    Heap left = new Heap(50, true);
    Heap right = new Heap(50, false);
    public Leetcode295() {
    }
    public void addNum(int num) {
        if(left.getSize() == right.getSize()) {
            right.offer(num);
            left.offer(right.poll());
        }else {
            left.offer(num);
            right.offer(left.poll());
        }
    }
    public double findMedian() {
        if(left.getSize() == right.getSize()) {
            return (double) (left.peek() + right.peek()) /2.00000;
        }else if(left.getSize() < right.getSize()) {
            return right.peek();
        }else  {
            return left.peek();
        }
    }
}
