package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.YieldingWaitStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/2
 */


public class DisruptorExample2 {
    
    public static void main(String[] args) {
    
        EventFactory<MyEvent> eventFactory = new EventFactory<MyEvent>() {
            @Override
            public MyEvent newInstance() {
                return new MyEvent();
            }
        };
        int ringBufferSize = 1024 * 1024;
        
        RingBuffer<MyEvent> ringBuffer = RingBuffer.createSingleProducer(eventFactory, ringBufferSize, new YieldingWaitStrategy());
    
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
    
        ExecutorService executors = Executors.newFixedThreadPool(10);
    
        WorkHandler<MyEvent> workHandler = new TradeHandler();
    
        WorkerPool<MyEvent> workerPool = new WorkerPool<MyEvent>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),workHandler);
    
        workerPool.start(executors);
    
        for(int i = 0 ;i < 10 ; i++ ){
            long next = ringBuffer.next();
            ringBuffer.get(next).setValue(i);
            ringBuffer.publish(next);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerPool.halt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executors.shutdown();
    }
}
