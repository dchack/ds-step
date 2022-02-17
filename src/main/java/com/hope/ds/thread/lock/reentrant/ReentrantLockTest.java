package com.hope.ds.thread.lock.reentrant;

import com.hope.ds.thread.lock.LockTest;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dongchao
 */
public class ReentrantLockTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();


    public static void main(String[] args) {
        reentrantLock.lock();

        Thread t = new Thread(ReentrantLockTest::executeLong);
        t.start();
    }


    private static void executeLong()  {
        reentrantLock.lock();
    }
}
