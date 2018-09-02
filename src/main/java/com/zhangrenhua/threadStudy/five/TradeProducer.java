package com.zhangrenhua.threadStudy.five;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/9/2
 */


public class TradeProducer implements Runnable {
    
    
    private Disruptor<MyEvent> disruptor;
    
    private CountDownLatch countDownLatch ;
    
    public TradeProducer(Disruptor<MyEvent> disruptor, CountDownLatch countDownLatch) {
        this.disruptor = disruptor;
        this.countDownLatch = countDownLatch;
    }
    
    private static  final int  LOOP = 1;
    
    @Override
    public void run() {
        for(int i = 0 ; i < LOOP ;i++){
            TradeTranslator tradeTranslator = new TradeTranslator();
            disruptor.publishEvent(tradeTranslator);
        }
        countDownLatch.countDown();
    }
    
}

class TradeTranslator implements EventTranslator<MyEvent>{
    
    @Override
    public void translateTo(MyEvent myEvent, long l) {
        myEvent.setName("aaaa");
        myEvent.setValue(1000);
    
    }
}
