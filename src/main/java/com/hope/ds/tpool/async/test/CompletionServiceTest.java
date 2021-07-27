package com.hope.ds.tpool.async.test;

import com.hope.ds.tpool.async.AsyncExecutor;
import com.hope.ds.tpool.async.ThreadPoolExecutorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;

public class CompletionServiceTest {

    private static ThreadPoolExecutor defaultThreadPoolExecutor;

    static {
        defaultThreadPoolExecutor = new ThreadPoolExecutorFactory().create("application", 10, 20);
    }

    public static void main(String[] args) {
//        extracted1();

        Callable task1 = () ->  {
            System.out.println("task1");
            Thread.sleep(5000);
            return null;
        };

        Callable task2 = () ->  {
            System.out.println("task2");
            Thread.sleep(3000);
            return null;
        };

        List<Callable> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        AsyncExecutor asyncExecutor = new AsyncExecutor();

        asyncExecutor.blockedExecute(tasks, defaultThreadPoolExecutor, 4);
        System.out.println("task5");
    }

    private static void extracted1() {
        CompletionService completionService = new ExecutorCompletionService(defaultThreadPoolExecutor);

        Callable task1 = () ->  {
            System.out.println("task1");
            Thread.sleep(1000);
            return null;
        };

        Callable task2 = () ->  {
            System.out.println("task2");
            Thread.sleep(2000);
            return null;
        };
        List<Callable> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        for(Callable task : tasks){
            completionService.submit(task);
        }

        System.out.println("task3");
    }
}
