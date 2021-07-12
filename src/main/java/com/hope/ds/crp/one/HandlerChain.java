package com.hope.ds.crp.one;

public class HandlerChain {

    private Handler head;
    private Handler tail;

    /**
     * 将handle组合成一个链表
     * @param handler
     */
    public void addHandler(Handler handler) {
        handler.setNext(null);
        // 先把head放好，然后把每个handler里的next设置成下一个handler，就可以组合成链表
        if(head == null){
            head = handler;
            tail = handler;
            return;
        }
        // tail作为中间存放的地方，每次放handler的时候，tail上就是它上一个handler
        // 所以就直接就向tail设置next，然后把tail改成最后放入的handler
        tail.setNext(handler);
        tail = handler;
    }

    public void handle(Request request){
        if(head != null){
            head.handle(request);
        }
    }

}
