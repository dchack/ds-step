package com.hope.ds.crp.four;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain {

    List<Handler> handlerList = new ArrayList<Handler>();

    /**
     * list 数据结构存放
     * @param handler
     */
    public void addHandler(Handler handler) {
        handlerList.add(handler);
    }

    public void handle(Request request){
        for (Handler handler : handlerList) {
            handler.handle(request);
        }
    }
}
