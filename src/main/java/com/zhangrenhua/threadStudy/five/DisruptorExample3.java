package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/2
 */


public class DisruptorExample3  {
    
    public static void main(String[] args) {
    
    
        //1.event工厂
        MyEventFactory myEventFactory = new MyEventFactory();
        //2.定义ringBufferSize缓冲区大小
        int ringBufferSie = 1024 * 1024;
        //3.定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
    
        //4.初始化disruptor
        Disruptor<MyEvent> disruptor = new Disruptor<MyEvent>(myEventFactory,ringBufferSie,executorService, ProducerType.SINGLE,new YieldingWaitStrategy());
        
        //p1 生产数据  c1 c2 并行消费 完成后交给 c3
        disruptor.handleEventsWith(new MyEventHandler1(),new MyEventHandler2()).then(new MyEventHandler3());
        
        disruptor.start();
    
        CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.submit(new TradeProducer(disruptor,countDownLatch));
        
        disruptor.shutdown();
        executorService.shutdown();
    }
}
