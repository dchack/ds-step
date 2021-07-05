package com.hope.ds.crp.six;

public interface Interceptor {

    boolean preHandle(Request request, Response response);

    void postHandle(Request request, Response response);

    void afterCompletion(Request request, Response response);
}
