package com.hope.ds.test.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 12:56 PM
 */
public class TypeCon <T>{

    public TypeCon() {

        Type superClass = getClass().getGenericSuperclass();

        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];

    }
}
