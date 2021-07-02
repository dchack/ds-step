package com.hope.ds.crp.second;

import java.util.List;

public class HandlerChain {

    List<Handler> handlerList = null;

    /**
     * list 数据结构存放
     * @param handler
     */
    public void addHandler(Handler handler) {
        handlerList.add(handler);
    }

    public void handle(Request request){
        for (Handler handler : handlerList) {
            boolean handle = handler.handle(request);
            if(handle){
                break;
            }
        }
    }

}
