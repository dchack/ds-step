package com.hope.ds.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hopehack
 */
public class TicketLock {

    // 记录票的号数，初始化是0，记号从1号开始
    private AtomicInteger ticketer = new AtomicInteger(0);
    // 记录比对号从1号开始
    private AtomicInteger cas = new AtomicInteger(1);

    public void lock() {
        // 每次触发抢锁就原子操作记号器加1，隐含这排队的能力
        int num = ticketer.incrementAndGet();
        // 出号和比对号相同则获取到锁，否则自旋
        while (num != cas.get()){}
    }

    public void unlock() {
        // 释放锁就是把比对号加1
        cas.incrementAndGet();
    }
}
