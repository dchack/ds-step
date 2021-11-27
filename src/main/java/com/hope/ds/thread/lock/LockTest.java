package com.hope.ds.thread.lock;

/**
 * @author hopehack
 */
public class LockTest {

    private static Lock LOCK = new Lock();

    public static void main(String[] args) {

        Thread t = new Thread(LockTest::executeLong);
        t.start();

        Thread t1 = new Thread(LockTest::executeLong);
        t1.start();

        Thread t2 = new Thread(LockTest::executeShort);
        t2.start();

    }
    private static void executeLong()  {
        LOCK.lock();
        System.out.println(Thread.currentThread().getName() + "sleep long" );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LOCK.release();
        }
    }

    private static void executeShort()  {
        LOCK.lock();
        System.out.println(Thread.currentThread().getName() + "sleep short" );
        LOCK.release();
    }
}
