package com.hope.ds.crp.first;

public class AHandler extends Handler{

    void handle(Request request) {
        // do somethings
        if(successor != null){
            successor.handle(request);
        }
        // do somethings
    }
}
