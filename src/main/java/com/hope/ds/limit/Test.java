package com.hope.ds.limit;

/**
 * @author dongchao
 */
public class Test {

    public static void main(String[] args) {
        IRateLimiter rateLimiter = new RateLimiter(1, 10);

        rateLimiter.acquire(1);


        rateLimiter.acquire(1);
        rateLimiter.acquire(1);
        rateLimiter.acquire(1);
        rateLimiter.acquire(1);

    }
}
