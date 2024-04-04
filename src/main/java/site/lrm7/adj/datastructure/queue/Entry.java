package site.lrm7.adj.datastructure.queue;

import site.lrm7.adj.datastructure.priorityqueue.Priority;

public class Entry<E> implements Priority {
    public final E value;
    private final int priority;

    public Entry(E value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }
}
