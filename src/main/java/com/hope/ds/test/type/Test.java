package com.hope.ds.test.type;

import com.alibaba.fastjson.TypeReference;
import com.hope.ds.test.fastjson.Person;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 12:30 PM
 */
public class Test {
    static List<Person<String>> stringList = new ArrayList<Person<String>>(){};
    List<Integer> integerList = new ArrayList<Integer>();
    public static void main(String[] args) throws NoSuchFieldException {
        Person<String> person = new Person<String>(){};

        Type superClass = person.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType)superClass).getActualTypeArguments()[0];

        TypeCon<List<Person<String>>> typeCon = new TypeCon<List<Person<String>>>();
        Type superClass1 = typeCon.getClass().getGenericSuperclass();

        TypeReference<List<Person<String>>> typeReference = new TypeReference<List<Person<String>>>(){};

        Field stringListField = Test.class.getDeclaredField("stringList");

        ParameterizedType stringListType = (ParameterizedType) stringListField.getGenericType();
        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
        System.out.println(stringListClass); // class java.lang.String.

        Field integerListField = Test.class.getDeclaredField("integerList");
        ParameterizedType integerListType = (ParameterizedType) integerListField.getGenericType();
        Class<?> integerListClass = (Class<?>) integerListType.getActualTypeArguments()[0];
        System.out.println(integerListClass); // class java.lang.Integer.
    }
}
