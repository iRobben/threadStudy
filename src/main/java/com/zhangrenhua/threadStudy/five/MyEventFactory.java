package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.EventFactory;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/1
 */


public class MyEventFactory implements EventFactory<MyEvent> {
    
    @Override
    public MyEvent newInstance() {
        return new MyEvent();
    }
}
