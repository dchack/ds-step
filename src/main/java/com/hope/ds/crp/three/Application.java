package com.hope.ds.crp.three;


public class Application {

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new AHandler());
        handlerChain.addHandler(new BHandler());
        handlerChain.handle(new Request());
    }
}
