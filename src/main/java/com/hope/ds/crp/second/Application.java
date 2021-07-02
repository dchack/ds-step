package com.hope.ds.crp.second;

public class Application {

    public static void main(String[] args) {
        HandlerChain handlerChain = new HandlerChain();
        handlerChain.addHandler(new AHandler());

        handlerChain.handle(new Request());
    }
}
