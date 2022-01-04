package com.hope.ds.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hopehack
 */
public class SpinLock {
    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        // 使用CAS抢锁，抢到锁的推出执行加锁后逻辑
        // 未抢到锁的自旋在这行代码
        while (!cas.compareAndSet(null, current)) {
            // DO nothing
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // 只需要设置回null，就释放锁了
        cas.compareAndSet(current, null);
    }

}