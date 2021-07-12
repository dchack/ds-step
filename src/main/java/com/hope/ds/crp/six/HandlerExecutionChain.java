package com.hope.ds.crp.six;

public class HandlerExecutionChain {

    // 省去数组扩容逻辑
    private Interceptor[] interceptors = new Interceptor[100];

    private int index = 0;

    private int interceptorIndex = -1;

    private Controller controller = new Controller();

    public void addInterceptor(Interceptor interceptor){
        interceptors[index++] = interceptor;
    }

    void doHandle(Request request, Response response){
        if(!applyPreHandle(request, response)){
            return;
        }
        try{
            controller.process(request, response);
            applyPostHandle(request, response);
        }catch (Exception e){

        }
        triggerAfterCompletion(request, response);
    }

    private boolean applyPreHandle(Request request, Response response){
        for (int i = 0; i < interceptors.length; i++) {
            Interceptor interceptor = interceptors[i];
            // 以此顺序执行，这里pre执行的时候是可以中断执行链的，中断后执行triggerAfterCompletion
            if (!interceptor.preHandle(request, response)) {
                triggerAfterCompletion(request, response);
                return false;
            }
            this.interceptorIndex = i;
        }
        return true;
    }

    private void applyPostHandle(Request request, Response response){
        // 而post执行是依次倒序执行全部interceptor中的post
        for (int i = interceptors.length - 1; i >= 0; i--) {
            Interceptor interceptor = interceptors[i];
            interceptor.postHandle(request, response);
        }
    }

    private void triggerAfterCompletion(Request request, Response response){
        // 从pre跳出的那个interceptor 开始往回倒序执行
        for (int i = this.interceptorIndex; i >= 0; i--) {
            Interceptor interceptor = interceptors[i];
            try {
                interceptor.afterCompletion(request, response);
            }
            catch (Throwable ex2) {
                // print some logs and do nothing
            }
        }
    }
}
