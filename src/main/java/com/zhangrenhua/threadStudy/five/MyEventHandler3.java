package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.EventHandler;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/1
 */


public class MyEventHandler3 implements EventHandler<MyEvent> {
    
    @Override
    public void onEvent(MyEvent myEvent, long l, boolean b) throws Exception {
        System.out.println(myEvent.toString());
    }
}
