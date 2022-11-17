package com.hope.ds.work;

import java.util.PriorityQueue;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/11/16 1:29 PM
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        // 2,3,4,5,6,7,8,9,10,
        extracted();
        System.out.println();
        // 2,3,5,4,8,9,6,10,7,
        extracted1();
    }

    private static void extracted() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10);
        for (int i = 10; i >=2; i--) {
            priorityQueue.add(i);
        }

        Integer t;
        while ((t = priorityQueue.poll()) != null) {
            System.out.print(t + ",");
        }
    }

    private static void extracted1() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10);
        for (int i = 10; i >=2; i--) {
            priorityQueue.add(i);
        }

        for (Integer e : priorityQueue) {
            System.out.print(e + ",");
        }
    }
}
