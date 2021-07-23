package com.hope.ds.tpool.async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorFactory {

    private int keepAliveSeconds = 60;

    private Map<String, ExecutorService> hashMap = new HashMap<>();

    private void put(String name, ExecutorService executorService){
        if(hashMap.containsKey(name)){
            // 异常
        }
        hashMap.put(name, executorService);
    }

    public ExecutorService get(String name){
        if(!hashMap.containsKey(name)){
            // 异常
        }
        return hashMap.get(name);
    }

    ThreadPoolExecutor create(String name, int corePoolSize, int maxPoolSize){
        return create(name, corePoolSize, maxPoolSize,
                keepAliveSeconds, new LinkedBlockingQueue<>(), new DefaultRejectedExecutionHandler());
    }


    private ThreadPoolExecutor create(String name, int corePoolSize, int maxPoolSize, int keepAliveSeconds,
                                      BlockingQueue<Runnable> queue, RejectedExecutionHandler rejectedExecutionHandler){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveSeconds, TimeUnit.SECONDS, queue, new DefaultThreadFactory(name), rejectedExecutionHandler);
        this.put(name, threadPoolExecutor);
        return threadPoolExecutor;
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + name + "-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    static class DefaultRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    }


}
