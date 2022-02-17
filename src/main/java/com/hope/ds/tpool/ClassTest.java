package com.hope.ds.tpool;

import java.lang.reflect.Field;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hopehack
 */
public class ClassTest {

    public static void main(String[] args) {
//        for (Field field : User.class.getDeclaredFields()){
//            System.out.println(""+field.getName());
//        }


        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{
            System.out.printf("22222");
        }, 1, 10, TimeUnit.SECONDS);

    }
}
