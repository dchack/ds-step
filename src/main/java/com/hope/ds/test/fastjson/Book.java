package com.hope.ds.test.fastjson;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 10:27 AM
 */
public class Book <T>{

    private T name;

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
}
