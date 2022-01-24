package com.hope.ds.thread.local;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class HashIncrementTest {
    private static AtomicInteger nextHashCode =
            new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            int hash = nextHashCode() & (64 - 1);
            System.out.printf(""+ hash + ", ");
        }
    }
}
