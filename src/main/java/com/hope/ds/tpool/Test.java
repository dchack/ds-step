package com.hope.ds.tpool;

import com.hope.ds.tpool.async.AsyncExecutor;


public class Test {

    public static void main(String[] args) {
        AsyncExecutor asyncExecutor = new AsyncExecutor();
        asyncExecutor.execute(() ->  get((Long) new Object()));



//        asyncExecutor.execute(() -> {
//            System.out.println("test");
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        asyncExecutor.execute(() -> {
//            System.out.println("test");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

    }

    public static void get(long t){
        System.out.printf("---"+t);
    }
}
