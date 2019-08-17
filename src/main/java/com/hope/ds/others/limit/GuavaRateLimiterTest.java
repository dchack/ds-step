package com.hope.ds.others.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class GuavaRateLimiterTest {


    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5);
//        for(int i = 0; i < 5;i++) {
//            System.out.println(limiter.acquire());
//        }
//        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
//        for (int i = 1; i <= 5;i++) {
//            System.out.println(limiter.acquire());
//        }
//        Thread.sleep(1000L);
//        for(int i = 1; i <= 5;i++) {
//            System.out.println(limiter.acquire());
//        }
//        Thread.sleep(1000L);
//        for(int i = 1; i <= 10;i++) {
//            System.out.println(limiter.acquire());
//        }

//        for(int i = 1; i <= 10;i++) {
//            System.out.println(limiter.acquire());
//        }
        // 这个效果就是当请求一个开销较大的，机制是马上成功，但是后续的请求会进行推后
        System.out.println(limiter.acquire(30));//0.0
        System.out.println(limiter.acquire(1));//6.495448

    }
}
