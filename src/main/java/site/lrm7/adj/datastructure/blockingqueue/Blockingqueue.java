package site.lrm7.adj.datastructure.blockingqueue;

public interface Blockingqueue<E> {
    void offer(E e) throws  InterruptedException;
    boolean offer(E e, long timeout) throws  InterruptedException;
    E poll() throws InterruptedException;
}
