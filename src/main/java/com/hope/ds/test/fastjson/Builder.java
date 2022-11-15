package com.hope.ds.test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO
 *
 * @author dongchao
 * @Date 2022/7/31 10:31 AM
 */
public class Builder <T> {

    TypeReference<T> typeReference;

    Ts<T> ts;

    Type type;
    public Builder(Ts<T> ts) {
        typeReference = new TypeReference<T>() {};
//        this.type = parseType(ts.getClass(), Ts.class);
        this.ts = ts;
    }

    private T user;

    public T get(String str) {
        return JSON.parseObject(str, typeReference);
    }

    public static Type parseType(Class<?> cls, Class<?> iCls) {
        Type[] genTypes = cls.getGenericInterfaces();
        if (genTypes.length == 0) {
            return null;
        }

        ParameterizedType genType = null;
        for (Type type : genTypes) {
            if (!(type instanceof ParameterizedType)) {
                continue;
            }
            ParameterizedType tmpType = (ParameterizedType) type;
            if (iCls.isAssignableFrom(((Class<?>) tmpType.getRawType()))) {
                genType = tmpType;
                break;
            }
        }
        if (genType == null) {
            return null;
        }
        Type[] typeParams = genType.getActualTypeArguments();
        if (typeParams.length != 1) {
            return null;
        }
        return typeParams[0];
    }
}
