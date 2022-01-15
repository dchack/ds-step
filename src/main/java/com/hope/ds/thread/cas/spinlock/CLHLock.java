package com.hope.ds.thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author hopehack
 */
public class CLHLock {
    // 尾部指向
    private final AtomicReference<Node> tail;
    // 当前线程持有节点
    private final ThreadLocal<Node> myNode;
    // 当前线程的前面节点
    private final ThreadLocal<Node> myPred;

    /**
     * 节点定义
     */
    static class Node {
        volatile boolean locked = false;
    }

    public CLHLock() {
        // 初始化尾部指向一个Node对象
        tail = new AtomicReference<>(new Node());
        myNode = ThreadLocal.withInitial(() -> new Node());
        // 初始化前面节点为空
        myPred = ThreadLocal.withInitial(() -> null);
    }

    public void lock(){
        Node node = myNode.get();
        // 设置成获得锁状态
        node.locked = true;
        // 尾部指向自己 刚开始的第一个执行的返回pred就是前面CLHLock初始化时产生的Node对象
        Node pred = tail.getAndSet(node);
        // 自己维护前面节点
        myPred.set(pred);
        // 自旋前面节点的锁状态
        while (pred.locked){}
    }

    public void unLock(){
        Node node = myNode.get();
        // 释放锁
        node.locked=false;
        // 把自己设置成那个CLHLock初始化时产生的Node对象
        myNode.set(myPred.get());
    }
}

