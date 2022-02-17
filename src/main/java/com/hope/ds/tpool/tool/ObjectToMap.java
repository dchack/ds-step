package com.hope.ds.tpool.tool;

import org.springframework.cglib.beans.BeanMap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hopehack
 */
public class ObjectToMap {


    public static void main(String[] args) {
        User user =  new User("dc", new Date());
        Map map = beanToMap(user);
        System.out.printf(""+map);
    }


    public static <T> Map<String, ?> beanToMap(T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        Map<String, Object> map = new HashMap<>();

        beanMap.forEach((key, value) -> {
            map.put(String.valueOf(key), value);
        });
        return map;
    }


}
