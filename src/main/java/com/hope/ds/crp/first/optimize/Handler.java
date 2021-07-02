package com.hope.ds.crp.first.optimize;

import com.hope.ds.crp.first.Request;

/**
 * 优化点：每个Handler不需要调用handle方法来链接整个链表
 */
public abstract class Handler {

    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public void handle(Request request){
        boolean handled = doHandle(request);
        if(handled && this.successor != null){
            successor.handle(request);
        }
    }

    abstract boolean doHandle(Request request);
}
