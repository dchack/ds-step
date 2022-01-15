package com.hope.ds.thread.cas.spinlock;

/**
 * @author hopehack
 * int累加不保证线程安全
 */
public class IntIncrForTicketLock implements Runnable{

    private static int x;
    private TicketLock spinLock;

    public IntIncrForTicketLock(TicketLock spinLock) {
        this.spinLock = spinLock;
    }

    public static void main(String[] args) throws InterruptedException {
        TicketLock spinLock = new TicketLock();
        for (int i = 0; i < 6; i++) {
            IntIncrForTicketLock intIncr = new IntIncrForTicketLock(spinLock);
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
