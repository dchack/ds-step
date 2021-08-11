package com.hope.ds.tpool.async.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dongchao
 * @description: TODO
 * @date 2021/8/1 8:39 下午
 */
public class DivTask implements Runnable {
    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        throw new RuntimeException("22222");
//        double re=a/b;
//        System.out.println(re);
    }

    public static void main(String[] args) {
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            threadPoolExecutor.submit(new DivTask(100, i));
            threadPoolExecutor.execute(new DivTask(100, i));
        }
    }
}
