package com.hope.ds.tpool.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
     * 阻塞异步执行，多个任务并行执行，等待全部任务完成后再继续
     * 任务最耗时任务超过duration，就会进行全部任务的cancel操作，但不保证任务能被cancel
     *
     * @param tasks              任务集
     * @param threadPoolExecutor 自定义线程池，线程池隔离
     * @param duration           秒单位，总耗时超时时间
     */
    public void blockedExecute(Collection<Callable> tasks, ThreadPoolExecutor threadPoolExecutor, long duration) {
        CompletionService completionService = new ExecutorCompletionService(threadPoolExecutor);
        List<Future> list = new ArrayList<>(tasks.size());
        for (Callable task : tasks) {
            list.add(completionService.submit(task));
        }
        long start = System.nanoTime();
        long timeout = duration * 1000 * 1000 * 1000;
        for (int i = 0; i < tasks.size(); i++) {
            try {
                if (completionService.poll(timeout, TimeUnit.NANOSECONDS) == null) {
                    // log
                    cancelTasks(list);
                    break;
                }
            } catch (InterruptedException e) {
                // log
                cancelTasks(list);
            } catch (Exception e) {
                // log
            } finally {
                long expend = System.nanoTime() - start;
                start = System.nanoTime();
                timeout = timeout - expend;
            }
        }
    }

    /**
     * 逐个尝试取消任务
     *
     * @param list
     */
    private static void cancelTasks(List<Future> list) {
        for (Future future : list) {
            future.cancel(true);
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
