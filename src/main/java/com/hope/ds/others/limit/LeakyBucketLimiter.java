package com.hope.ds.others.limit;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LeakyBucketLimiter {

    BlockingQueue<String> leakyBucket;
    long rate;

    public LeakyBucketLimiter(int capacity, long rate) {
        this.rate = rate;
        this.leakyBucket = new ArrayBlockingQueue<String>(capacity);
    }

    public boolean offer(){
        return leakyBucket.offer("");
    }

    class Consumer implements Runnable{
        public void run() {
            try {
                while (true){
                    Thread.sleep(1000/rate);
                    leakyBucket.take();
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
