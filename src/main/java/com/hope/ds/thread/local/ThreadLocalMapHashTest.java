package com.hope.ds.thread.local;

/**
 * @author hopehack
 */
public class ThreadLocalMapHashTest {

    private static final  ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal3 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal4 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal5 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal6 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal7 = new ThreadLocal<>();
    private static final  ThreadLocal<String> threadLocal8 = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("0");
                threadLocal1.set("1");
                threadLocal2.set("2");
                threadLocal3.set("3");
                threadLocal4.set("4");
                threadLocal5.set("5");
                threadLocal6.set("6");
                threadLocal7.set("7");
                threadLocal8.set("8");
            }
        });
        t1.start();


    }
}
