package com.hope.ds.crp.one;

public class AHandler extends Handler{

    void handle(Request request) {

        // 是否处理
        boolean handled = false;

        // do something
        if(!handled && next != null){
            // 执行下一个
            next.handle(request);
        }
        // do something
    }
}
