package com.hope.dp.java8;

import java.io.File;
import java.util.function.Predicate;

/**
 * 方法引用
 * :: 语法
 *
 * @FunctionalInterface
 *
 * @author hopehack
 * @Date 2023/2/5 5:06 PM
 */
public class MethodReference {

    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        for (File file : hiddenFiles) {
            System.out.printf(" " +file.getName());
        }

        // get lamdbda
        predicateTest("", (s) -> {
            return s.equals("1");
        });
    }

    private static void predicateTest(String s, Predicate<String> predicate) {
        if (predicate.test(s)) {
            System.out.printf("" + "test ok");
        }
    }
}
