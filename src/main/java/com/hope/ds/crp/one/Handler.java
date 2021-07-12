package com.hope.ds.crp.one;

public abstract class Handler {

    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    abstract void handle(Request request);

}
