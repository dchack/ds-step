package com.hope.ds.crp.five;

public interface IHandler {

    void handle(Request request, Response response, TwoWayHandlerChain handlerChain);
}
