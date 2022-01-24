package com.hope.ds.thread.local;

import java.math.BigDecimal;

/**
 * @author hopehack
 */
public class ThreadLocalTest {


    public static ThreadLocalTest t1 = new ThreadLocalTest("1"){

    };

    public static Object t2 =  new Object(){

    };
//    private ThreadLocal<String> local = new ThreadLocalTest<>();


    public ThreadLocalTest(String t) {
        System.out.printf("test" + t);
    }

//    private void test(){
//        ThreadLocalTest t2 = new ThreadLocalTest("2");
//        t2.test();
//    }

//    private static ThreadLocalTest t;
//    static{
//         t = new ThreadLocalTest();
//    }



    static ThreadLocal<String> localVar = new ThreadLocal<>();
    static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }

    public static void main(String[] args) {

        double t = (Math.sqrt(5) - 1)/2;
        System.out.println(""+ BigDecimal.valueOf(Math.pow(2,32)* t).intValue());

//        System.out.println(""+Integer.toHexString(BigDecimal.valueOf(Math.pow(2,32)* t).intValue()));

//        ThreadLocalTest t2 = new ThreadLocalTest("2");
//        t2.test();
//        System.out.printf("test1");
//        Thread t1  = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //设置线程1中本地变量的值
//                localVar.set("localVar1");
//                //调用打印方法
//                print("thread1");
//                //打印本地变量
//                System.out.println("after remove : " + localVar.get());
//            }
//        });
//
//        Thread t2  = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //设置线程1中本地变量的值
//                localVar.set("localVar2");
//                //调用打印方法
//                print("thread2");
//                //打印本地变量
//                System.out.println("after remove : " + localVar.get());
//            }
//        });
//
//        t1.start();
//        t2.start();
    }
}
