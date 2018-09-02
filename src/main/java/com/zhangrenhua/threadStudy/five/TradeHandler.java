package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/2
 */


public class TradeHandler implements EventHandler<MyEvent>,WorkHandler<MyEvent> {
    
    
    @Override
    public void onEvent(MyEvent myEvent, long l, boolean b) throws Exception {
        this.onEvent(myEvent);
    }
    
    @Override
    public void onEvent(MyEvent myEvent) throws Exception {
        System.out.println(UUID.randomUUID().toString());
    }
}
