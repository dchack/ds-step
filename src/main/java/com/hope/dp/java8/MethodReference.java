package com.hope.dp.java8;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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


    private void streamTest() {
        // Optional
        String[] menu = new String[]{"fish", "bread", "beef"};
        Optional<String> optional = Arrays.stream(menu).filter("fish" :: equals).findAny();
        String dish = optional.orElse("default");

        List<Dish> menus = new ArrayList<>();
        menus.add(new Dish("fish", 1));
        menus.add(new Dish("bread", 2));
        menus.add(new Dish("beef", 4));
        Integer sum = menus.stream().map(Dish :: getPrice).reduce(0, Integer::sum);
    }

    class Dish {
        public Dish(String name, Integer price) {
            this.name = name;
            this.price = price;
        }

        String name;
        Integer price;

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }
    }
}
