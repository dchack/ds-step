package com.hope.ds.thread.cas.atomic;

/**
 * @author hopehack
 * int累加不保证线程安全
 */
public class IntIncr implements Runnable{

    private static int x;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            IntIncr intIncr = new IntIncr();
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
            x++;
        }
    }
}
