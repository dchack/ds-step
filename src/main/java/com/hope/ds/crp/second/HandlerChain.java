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
            // 如果链上某个处理器能够处理这个请求，那就不会继续往下传递请求
            if(handle){
                break;
            }
        }
    }

}
