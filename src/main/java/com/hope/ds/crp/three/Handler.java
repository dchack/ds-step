package com.hope.ds.crp.three;

public abstract class Handler {

    protected Handler next = null;

    public void setNext(Handler next) {
        this.next = next;
    }

    public void handle(Request request){
        doHandle(request);
        // 请求会被所有的处理器都处理一遍
        if(this.next != null){
            next.handle(request);
        }
    }

    abstract void doHandle(Request request);
}
