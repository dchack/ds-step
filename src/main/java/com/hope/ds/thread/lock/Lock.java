package com.hope.ds.thread.lock;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author hopehack
 */
public class Lock {

    /**
     * 开关 0代表开 1代表关
     */
    private AtomicInteger state = new AtomicInteger(0);

    /**
     * 线程阻塞时排队用的队列
     */
    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    public boolean lock(){
        while (!state.compareAndSet(0, 1)){
            queue.add(Thread.currentThread());
            LockSupport.park();
        }
        return true;
    }

    public void release(){
        state.set(0);
        if(!queue.isEmpty()){
            Thread thread1 = queue.remove();
            LockSupport.unpark(thread1);
        }
    }

}
