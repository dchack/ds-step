package com.hope.dp.java8;

import java.io.File;

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
    }
}
