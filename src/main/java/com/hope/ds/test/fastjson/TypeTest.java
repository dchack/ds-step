package com.hope.ds.test.fastjson;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 10:19 AM
 */
public class TypeTest {

    public static void main(String[] args) {
        String str = "{\"list\":[{\"name\":\"haoshu\",\"num\":2}],\"username\":\"kele\"}";
        Person<Book<String>> person1 = new Person<>();
        person1.setUsername("kele");
        List<Book<String>> list = new ArrayList<>();
        Book<String> book = new Book<>();
        book.setName("haoshu");
        book.setNum(2);
        list.add(book);
        person1.setList(list);

        Ts<Person<Book<String>>> ts = new Ts<Person<Book<String>>>(){};

        Builder<Person<Book<String>>> builder =new Builder<Person<Book<String>>>(ts);

        Person<Book<String>> person = builder.get(str);


//        Person<Book<String>> person1 = new Person<>();
//        person1.setUsername("kele");
//        List<Book<String>> list = new ArrayList<>();
//        Book<String> book = new Book<>();
//        book.setName("haoshu");
//        book.setNum(2);
//        list.add(book);
//        person1.setList(list);
//
//        System.out.printf(JSON.toJSONString(person1));

    }

}
