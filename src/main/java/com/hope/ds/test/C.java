package com.hope.ds.test;

public class C extends B{

    @Override
    public void test() {
        System.out.printf("C");
    }

    public static void main(String[] args) {
        C c = new C();
        c.test();
    }
}
