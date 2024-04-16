package site.lrm7.adj.datastructure;

import org.junit.Test;
import site.lrm7.adj.datastructure.queue.LinkedListQueue;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class LinkedListQueueTest {

    @Test
    public void iterator() {
    }

    private LinkedListQueue<Integer> createQueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>(6);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);

        return queue;
    }
    @Test
    public void offer() {
        LinkedListQueue<Integer> queue = createQueue();
        assertIterableEquals(List.of(1,2,3,4,5), queue);
        
    }

    private void assertIterableEquals(List<Integer> integers, LinkedListQueue<Integer> queue) {
    }

    @Test
    public void isEmpty() {
        LinkedListQueue<Integer> queue = new LinkedListQueue();
        assertEquals(true, queue.isEmpty());
    }
}