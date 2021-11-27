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

    /**
     * 关锁
     * @return
     */
    public boolean lock(){
        // 修改state值，修改成功就表示关锁成功，线程就进门执行代码
        // 修改失败，代表门已经锁住，线程就排队等待
        while (!state.compareAndSet(0, 1)){
            queue.add(Thread.currentThread());
            LockSupport.park();
        }
        return true;
    }

    /**
     * 开锁
     */
    public void release(){
        // 进门执行的线程结束后，修改state状态到打开状态
        state.set(0);
        // 然后看下门外面有没有排队的，如果有就唤醒排最前面的线程起来
        if(!queue.isEmpty()){
            Thread thread1 = queue.remove();
            LockSupport.unpark(thread1);
        }
    }

}
