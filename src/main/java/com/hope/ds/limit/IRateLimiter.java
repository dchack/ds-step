package com.hope.ds.limit;

public interface IRateLimiter {

    boolean acquire(int num);
}
