package com.hope.ds.crp.five;

import java.util.ArrayList;
import java.util.List;

public class TwoWayHandlerChain {

    private int pos = 0;
    private List<IHandler> handlers = new ArrayList<IHandler>();
    private Servlet servlet = new Servlet();

    void addHandler(IHandler handler){
        handlers.add(handler);
    }

    void doHandle(Request request, Response response){
        if(handlers.size() > pos){
            IHandler handler = handlers.get(pos++);
            handler.handle(request, response, this);
        }else{
            servlet.service(request, response);
        }
    }

}
