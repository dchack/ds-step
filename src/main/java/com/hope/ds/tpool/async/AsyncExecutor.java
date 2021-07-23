package com.hope.ds.tpool.async;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class AsyncExecutor {


    private static ThreadPoolExecutor defaultThreadPoolExecutor;
    static {
        defaultThreadPoolExecutor = new ThreadPoolExecutorFactory().create("application", 10, 20);
    }

    /**
     * 异步执行一个任务，使用服务内默认线程池
     * @param task 任务
     */
    public void execute(Runnable task){
        Future future = defaultThreadPoolExecutor.submit(task);
        System.out.printf("active count:"+defaultThreadPoolExecutor.getActiveCount());
    }


}
