package com.hope.ds.datastruct.binarytree;

/**
 * 完全二叉树
 *
 * @author dongchao
 * @Date 2022/10/25 6:02 PM
 */
public class CompleteBinaryTree {

    private int[] array = new int[100];

    public void put(int value) {
        int length = array.length;
        array[length + 1] = value;//todo
    }

    public boolean hasValue(int value) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            if(value == array[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args) {
//        PriorityQueue priorityQueue = new PriorityQueue();
//        priorityQueue.add(1);
//        priorityQueue.add(4);
//        priorityQueue.add(8);
//        priorityQueue.add(3);
//        priorityQueue.add(5);

        int i = Integer.MAX_VALUE+1;

    }




}
