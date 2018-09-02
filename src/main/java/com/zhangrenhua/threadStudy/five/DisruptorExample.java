package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/1
 */


public class DisruptorExample {
    
    public static void main(String[] args) {
        
        //1.event工厂
        MyEventFactory myEventFactory = new MyEventFactory();
        //2.定义ringBufferSize缓冲区大小
        int ringBufferSie = 1024 * 1024;
        //3.定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        //4.初始化disruptor
        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(myEventFactory,ringBufferSie,executorService, ProducerType.SINGLE,new YieldingWaitStrategy());
        
        //5.设置handler
        disruptor.handleEventsWith(new MyEventHandler());
        
        //6 启动
        disruptor.start();
    
        
        //生产数据
        RingBuffer<MyEvent> ringBuffer = disruptor.getRingBuffer();
        MyEventProducer myEventProducer = new MyEventProducer(ringBuffer);
    
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for(long i = 0 ; i< 100 ; i++){
            byteBuffer.putLong(0, i);
            myEventProducer.onData(byteBuffer);
        }
        
        //关闭disruptor
        disruptor.shutdown();
        //关闭线程池
        executorService.shutdown();
        
        
        
        
    
    
    }
}
