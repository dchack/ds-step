package com.hope.ds.crp.first;

public class BHandler extends Handler{

    void handle(Request request) {
        // 是否处理
        boolean handled = false;

        // do something
        if(successor != null){
            successor.handle(request);
        }
        // do something
    }
}
