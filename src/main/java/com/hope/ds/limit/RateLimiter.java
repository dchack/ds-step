package com.hope.ds.limit;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author hopehack
 */
public class RateLimiter implements IRateLimiter{

    /**
     * 桶容量
     */
    private final long capacity;

    /**
     * 速率
     */
    private final double rate;

    /**
     * 最近请求时间
     */
    private AtomicLong lastAcquireTime = new AtomicLong();

    /**
     * 同内令牌
     */
    private AtomicLong bucketToken  = new AtomicLong();

    public RateLimiter(double rate, long capacity) {
        this.rate = rate / 1000;
        this.capacity = capacity;
    }

    @Override
    public boolean acquire(int num) {

        // 当前请求时间
        long currentTime = System.currentTimeMillis();
        // 本次请求距上次请求间隔时间
        long interval = Math.abs(lastAcquireTime.addAndGet(-currentTime));
        // 更新最近请求时间
        lastAcquireTime = new AtomicLong(currentTime);
        // 间距时间内按速率产生令牌数
        long fillToken = (long) (rate * interval);
        // 剩余可用令牌
        long remainToken = Math.min(capacity, bucketToken.addAndGet(fillToken));

        long newToken = remainToken;
        boolean result = false;
        if(remainToken > num){
            newToken = newToken - 1;
            result = true;
        }
        bucketToken = new AtomicLong(newToken);
        return result;
    }
}
