package site.lrm7.adj.datastructure.queue;

import org.junit.jupiter.api.Test;

class PriorityQueue2Test {

    @Test
    void poll() {
        PriorityQueue2<Entry> queue1 = new PriorityQueue2<>(5);
        queue1.offer(new Entry("tash1", 2));
        queue1.offer(new Entry("tash2", 3));
        queue1.offer(new Entry("tash3", 4));
        queue1.offer(new Entry("tash4", 5));
        queue1.offer(new Entry("tash5", 1));

        assertEquals(5, queue1.poll().priority());
        assertEquals(4, queue1.poll().priority());
        assertEquals(3, queue1.poll().priority());
        assertEquals(2, queue1.poll().priority());
        assertEquals(1, queue1.poll().priority());
    }
}