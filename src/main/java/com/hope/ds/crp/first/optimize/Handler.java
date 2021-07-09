package com.hope.ds.crp.first.optimize;

import com.hope.ds.crp.first.Request;

/**
 * 优化点：每个Handler不需要调用handle方法来链接整个链表
 */
public abstract class Handler {

    protected Handler next = null;

    public void setNext(Handler next) {
        this.next = next;
    }

    public void handle(Request request){
        boolean handled = doHandle(request);
        // 如果链上某个处理器能够处理这个请求，那就不会继续往下传递请求
        if(!handled && this.next != null){
            next.handle(request);
        }
    }

    abstract boolean doHandle(Request request);
}
