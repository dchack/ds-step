package com.hope.ds.thread.aqs;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Task());
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire();
                Thread.sleep(6000);
                System.out.println(Thread.currentThread().getName()+" semaphore release");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
