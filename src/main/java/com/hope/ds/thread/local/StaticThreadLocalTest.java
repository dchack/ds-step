package com.hope.ds.thread.local;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hopehack
 */
public class StaticThreadLocalTest {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(nextId::get);


    private static  Integer num = null;

    public static void main(String[] args) {

        MyTask task = new MyTask();
        new Thread( task ).start();
        new Thread( task ).start();
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            // 每个线程随机生产一个数
            Integer count = new Random().nextInt(100);
            System.out.println(Thread.currentThread().getName() + ", count: " + count );

            // 模拟业务耗时
            try{
                Thread.sleep(1000);
            }catch (Exception e) {
            }

            // 存储数据
            num = count;
            threadLocal.set(count);

            // 获取数据
            System.out.println( Thread.currentThread().getName() + ", num: " + num + ", threadLocal: " +threadLocal.get() );

            // 模拟业务耗时
            try{
                Thread.sleep(100);
            }catch (Exception e) {
            }
            // 移除当前线程所存的数据
            threadLocal.remove();
        }
    }
}
