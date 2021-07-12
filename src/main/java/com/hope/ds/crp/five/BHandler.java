package com.hope.ds.crp.five;

public class BHandler implements IHandler{

    public void handle(Request request, Response response, TwoWayHandlerChain handlerChain) {
        System.out.println("do B handler1");
        handlerChain.doHandle(request, response);
        System.out.println("do B handler2");
    }
}
