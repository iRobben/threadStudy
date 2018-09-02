package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/1
 */


public class MyEventProducer {
    
    private RingBuffer<MyEvent> ringBuffer;
    
    public MyEventProducer(RingBuffer<MyEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    
    
    public void onData(ByteBuffer buffer){
    
        long nextSequence = ringBuffer.next();
        try {
            MyEvent myEvent = ringBuffer.get(nextSequence);
            myEvent.setValue(buffer.getLong(0));
        }finally {
            ringBuffer.publish(nextSequence);
        }
    }
}
