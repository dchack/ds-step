package com.hope.ds.others.limit;

import java.util.LinkedList;

public class SlidingWindowRateLimiter {

    // 小窗口链表
    LinkedList<Room> linkedList = null;
    long stepInterval = 0;
    long subWindowCount = 10;
    long stepLimitCount = 0;
    int countLimit = 0;
    int count = 0;


    public SlidingWindowRateLimiter(int countLimit, int interval){
        // 每个小窗口的时间间距
        this.stepInterval = interval * 1000/ subWindowCount;
        // 请求总数限制
        this.countLimit = countLimit;
        // 每个小窗口的请求量限制数 设置为平均的2倍
        this.stepLimitCount = countLimit / subWindowCount * 2;
        // 时间窗口开始时间
        long start = System.currentTimeMillis();
        // 初始化连续的小窗口链表
        initWindow(start);
    }

    Room getAndRefreshWindows(long requestTime){
        Room firstRoom = linkedList.getFirst();
        Room lastRoom = linkedList.getLast();
        // 发起请求时间在主窗口内
        if(firstRoom.getStartTime() < requestTime && requestTime < lastRoom.getEndTime()){
            long distanceFromFirst = requestTime - firstRoom.getStartTime();
            int num = (int) (distanceFromFirst/stepInterval);
            return linkedList.get(num);
        }else{
            long distanceFromLast = requestTime - lastRoom.getEndTime();
            int num = (int)(distanceFromLast/stepInterval);
            // 请求时间超出主窗口一个窗口以上的身位
            if(num >= subWindowCount){
                initWindow(requestTime);
                return linkedList.getFirst();
            }else{
                moveWindow(num+1);
                return linkedList.getLast();
            }
        }
    }

    public boolean acquire(){
        synchronized (mutex()) {
            Room room = getAndRefreshWindows(System.currentTimeMillis());
            int subCount = room.getCount();
            if(subCount + 1 <= stepLimitCount && count + 1 <= countLimit){
                room.increase();
                count ++;
                return true;
            }
            return false;
        }
    }

    /**
     * 初始化窗口
     * @param start
     */
    private void initWindow(long start){
        linkedList = new LinkedList<Room>();
        for (int i = 0; i < subWindowCount; i++) {
            linkedList.add(new Room(start, start += stepInterval));
        }
        // 总记数清零
        count = 0;
    }

    /**
     * 移动窗口
     * @param stepNum
     */
    private void moveWindow(int stepNum){
        for (int i = 0; i < stepNum; i++) {
            Room removeRoom = linkedList.removeFirst();
            count = count - removeRoom.count;
        }
        Room lastRoom = linkedList.getLast();
        long start = lastRoom.endTime;
        for (int i = 0; i < stepNum; i++) {
            linkedList.add(new Room(start, start += stepInterval));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(20, 5);
        for (int i = 0; i < 26; i++) {
            System.out.println(slidingWindowRateLimiter.acquire());
            Thread.sleep(300);
        }
    }

    class Room{
         Room(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.count = 0;
        }
        private long startTime;
        private long endTime;
        private int count;
        long getStartTime() {
            return startTime;
        }
        long getEndTime() {
            return endTime;
        }

        int getCount() {
            return count;
        }

        int increase(){
            this.count++;
            return this.count;
        }
    }

    private volatile Object mutexDoNotUseDirectly;

    private Object mutex() {
        Object mutex = mutexDoNotUseDirectly;
        if (mutex == null) {
            synchronized (this) {
                mutex = mutexDoNotUseDirectly;
                if (mutex == null) {
                    mutexDoNotUseDirectly = mutex = new Object();
                }
            }
        }
        return mutex;
    }

}
