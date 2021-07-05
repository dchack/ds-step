package com.hope.ds.crp.six;

public class InterceptorA implements Interceptor{

    public boolean preHandle(Request request, Response response) {
        return false;
    }

    public void postHandle(Request request, Response response) {

    }

    public void afterCompletion(Request request, Response response) {

    }
}
