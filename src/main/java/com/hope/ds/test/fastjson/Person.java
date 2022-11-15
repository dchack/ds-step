package com.hope.ds.test.fastjson;

import java.util.List;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 10:27 AM
 */
public class Person <T>{

    List<T> list;

    private String username;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
