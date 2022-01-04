package com.hope.ds.thread.cas.spinlock;

/**
 * @author hopehack
 * int累加不保证线程安全
 */
public class IntIncrForSpinLock implements Runnable{

    private static int x;
    private SpinLock spinLock;

    public IntIncrForSpinLock(SpinLock spinLock) {
        this.spinLock = spinLock;
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock spinLock = new SpinLock();
        for (int i = 0; i < 6; i++) {
            IntIncrForSpinLock intIncr = new IntIncrForSpinLock(spinLock);
            Thread t1 = new Thread(intIncr);
            t1.start();
            Thread t2 = new Thread(intIncr);
            t2.start();
            t1.join();
            t2.join();

            System.out.println("x:" + x);
            x = 0;
        }

    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            spinLock.lock();
            x++;
            spinLock.unlock();
        }
    }
}
