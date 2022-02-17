package com.hope.ds.thread.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest implements Runnable{

    static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        AQSTest aqsTest = new AQSTest();
        Thread t1 = new Thread(aqsTest);
        t1.start();
        Thread t2 = new Thread(aqsTest);
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * 执行消耗5秒
     */
    @Override
    public void run() {
        reentrantLock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }
}
