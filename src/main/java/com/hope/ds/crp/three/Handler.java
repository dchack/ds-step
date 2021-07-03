package com.hope.ds.crp.three;

public abstract class Handler {

    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void handle(Request request){
        doHandle(request);
        // 请求会被所有的处理器都处理一遍
        if(this.successor != null){
            successor.handle(request);
        }
    }

    abstract void doHandle(Request request);
}
