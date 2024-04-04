package site.lrm7.adj.datastructure.priorityqueue;

import org.junit.jupiter.api.Test;
import site.lrm7.adj.datastructure.ListNode;

class MinHeapTest {

    @Test
    void poll() {
        MinHeap queue1 = new MinHeap(5);

        queue1.offer(new ListNode(1,null));
        queue1.offer(new ListNode(5,null));
        queue1.offer(new ListNode(4,null));
        queue1.offer(new ListNode(2,null));
        queue1.offer(new ListNode(1,null));
    }
}