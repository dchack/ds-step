package com.hope.ds.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hopehack
 */
public class TTASLock {

    private AtomicReference<Thread> cas = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (true){
            // 判断未解锁 就在这里通过get自旋
            while(cas.get() != null){}
            // 使用CAS抢锁，抢到锁的推出执行加锁后逻辑
            if (cas.compareAndSet(null, current)) {
                // 抢到锁则退出外部循环
                break;
            }
        }

    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // 只需要设置回null，就释放锁了
        cas.compareAndSet(current, null);
    }
}
