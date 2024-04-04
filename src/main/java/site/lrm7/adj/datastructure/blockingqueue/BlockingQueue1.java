package site.lrm7.adj.datastructure.blockingqueue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue1<E> implements Blockingqueue<E>{
    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue1(int capacity) {
        this.array = (E[]) new Object[capacity];
    }
    private final ReentrantLock tailLock = new ReentrantLock();
    private final Condition tailWaits = tailLock.newCondition();
    private final ReentrantLock headLock = new ReentrantLock();
    private final Condition headWaits = headLock.newCondition();
    private boolean isFull() {
        return size.get() == array.length;
    }
    private boolean isEmpty() {
        return size.get() == 0;
    }
    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c; // 添加前的元素个数
        try {
            while(isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if(++tail == array.length) {
                tail = 0;
            }
            c = size.getAndIncrement();
            if(c< array.length) {
                tailWaits.signal();
            }
        }finally {
            tailLock.unlock();
        }

        if(c == 0) {
            headLock.lock();
            try{
                headWaits.signal();
            }finally {
                headLock.lock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c;
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while(isFull()) {
                if (t <= 0) {
                    return false;
                }
                t = tailWaits.awaitNanos(t);
            }
            array[tail] = e;
            if(++tail == array.length) {
                tail = 0;
            }
            c = size.getAndIncrement();
            if(c < array.length) {
                tailWaits.signal();
            }
        }finally {
            tailLock.unlock();
        }

        if(c == 0) {
            headLock.lock();
            try{
                headWaits.signal();
            }finally {
                headLock.lock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        headLock.lockInterruptibly();
        int c;
        E e;
        try{
            while(isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null;
            if(++head == array.length) {
                head = 0;
            }
            c = size.getAndDecrement();
            if (c  > 1) {
                headWaits.signal();
            }

        }finally {
            headLock.unlock();
        }

        if(c == array.length) {
            tailLock.lock();
            try{
                tailWaits.signal();
            }finally {
                tailLock.lock();
            }
        }
        return e;
    }
}
