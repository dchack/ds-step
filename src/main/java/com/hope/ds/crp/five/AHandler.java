package com.hope.ds.crp.five;

public class AHandler implements IHandler{

    public void handle(Request request, Response response, TwoWayHandlerChain handlerChain) {

        System.out.println("do A handler1");
        handlerChain.doHandle(request, response);
        System.out.println("do A handler2");
    }
}
