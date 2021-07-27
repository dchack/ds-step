package com.hope.ds.tpool.async;

import java.util.Collection;
import java.util.concurrent.*;

public class AsyncExecutor {

    /**
     * 默认阻塞时间 5s
     */
    private int DEFAULT_BLOCK_TIMEOUT = 5;

    private static ThreadPoolExecutor defaultThreadPoolExecutor;

    static {
        defaultThreadPoolExecutor = new ThreadPoolExecutorFactory().create("application", 10, 20);
    }

    /**
     * 异步提交一个任务，使用服务内默认线程池
     * @param task 任务
     */
    public void execute(Runnable task){
        defaultThreadPoolExecutor.submit(task);
        System.out.printf("active count:"+defaultThreadPoolExecutor.getActiveCount());
    }

    /**
     * 异步提交多个任务，使用服务内默认线程池
     * @param tasks
     */
    public void execute(Collection<Runnable> tasks){
        for(Runnable task : tasks){
            defaultThreadPoolExecutor.submit(task);
        }
    }

    /**
     * 阻塞异步执行
     * @param tasks
     * @param threadPoolExecutor
     */
    public void blockedExecute(Collection<Callable> tasks, ThreadPoolExecutor threadPoolExecutor, int duration){
        CompletionService completionService = new ExecutorCompletionService(threadPoolExecutor);
        for(Callable task : tasks){
            completionService.submit(task);
        }
        for (int i = 0; i < tasks.size(); i++) {
            try {
                completionService.poll(duration, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 阻塞异步执行
     * @param tasks
     * @param threadPoolExecutor
     */
    public void blockedExecute(Collection<Callable> tasks, ThreadPoolExecutor threadPoolExecutor){
        blockedExecute(tasks, threadPoolExecutor, DEFAULT_BLOCK_TIMEOUT);
    }

}
