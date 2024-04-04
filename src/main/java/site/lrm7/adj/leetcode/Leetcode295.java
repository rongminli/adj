package site.lrm7.adj.leetcode;
import java.util.Comparator;

import site.lrm7.adj.datastructure.priorityqueue.Heap;

class Leetcode295 {
    Comparator<Integer> cmp = (a,b) -> Integer.compare(a,b);
    Comparator<Integer> cmp1 = (a,b) -> Integer.compare(b,a);
    Heap<Integer> left = new Heap(50, cmp);
    Heap<Integer> right = new Heap(50, cmp1);
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
