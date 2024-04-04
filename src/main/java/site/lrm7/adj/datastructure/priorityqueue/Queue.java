package site.lrm7.adj.datastructure.priorityqueue;

public interface Queue<E> {
    boolean offer(E value);

    E poll();

    E peek();

    boolean isEmpty();

    boolean isFull();
}
