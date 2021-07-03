package com.hope.ds.crp.five;

public class Application {
    /**
     * 打印信息：
     * do A handler1
     * do B handler1
     * do Servlet
     * do B handler2
     * do A handler2
     */
    public static void main(String[] args) {
        TwoWayHandlerChain handlerChain = new TwoWayHandlerChain();
        handlerChain.addHandler(new AHandler());
        handlerChain.addHandler(new BHandler());
        handlerChain.doHandle(new Request(), new Response());
    }
}
