package com.hope.ds.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hopehack
 */
public class MCSLock {

    // 链表尾部指向
    private final AtomicReference<Node> tail;
    // 每个线程维护的内部变量
    private final ThreadLocal<Node> myNode;

    public MCSLock() {
        // 队尾指向 使用AtomicReference保证原子操作
        tail = new AtomicReference<>();
        // 线程本地变量维护各自线程的Node
        myNode = ThreadLocal.withInitial(() -> new Node());
    }

    public void lock() {
        Node node = myNode.get();
        // 把队尾指向到自己 注意这里是有cas 原子操作，确保了多线程并发下依次执行，保证了并发场景下链表的形成
        Node pred = tail.getAndSet(node); // step1
        // 如果原先有队尾node存在，说明已经有线程占用了锁
        if (pred != null) {
            // 把自己的节点设置成锁状态
            node.locked = true;
            // 并且把前后两个node用next连接起来
            pred.next = node; // step2
            // 自旋自己node的locked字段
            while (node.locked) {
            }
        }

    }

    // 释放锁
    public void unLock() {
        // 操作本线程的本地变量维护的node
        Node node = myNode.get();
        // 判断自己后面还有没有等待的节点
        if (node.next == null) {
            // 如果自己是最后一个节点，那么就把尾部指向改成null，这里仍然是需要使用cas操作来进行
            if (tail.compareAndSet(node, null)) {
                // 操作成功就结束了
                return;
            }
            // 前面的compareAndSet执行失败，就是说去设置尾部指向的时候被别人抢先了
            // 只有一种可能那就是有其他线程执行lock了，这里需要在自旋等待一下node.next被lock操作设置
            while (node.next == null) {
            }
        }
        // 相当于通知自己后面的节点释放锁
        node.next.locked = false;
        // 把自己设置成null，释放内存
        node.next = null;
    }

    /**
     * 节点定义
     */
    class Node {
        volatile boolean locked = false;
        Node next = null;
    }

}
