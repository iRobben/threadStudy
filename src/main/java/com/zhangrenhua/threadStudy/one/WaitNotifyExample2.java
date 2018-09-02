package com.zhangrenhua.threadStudy.one;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 描述：
 * </p>
 *
 * @author zhangrenhua
 * @since 2018/8/23
 */


public class WaitNotifyExample2 {
    
    private static volatile List<String> list = new ArrayList<String>();
    
    public void add(String s){
        list.add(s);
    }
    
    public int getSize(){
        return list.size();
    }
    
    
    public static void main(String[] args) {
        
        final WaitNotifyExample2 waitNotifyExample = new WaitNotifyExample2();
        
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        
        Thread threadA = new Thread(() -> {
            if(waitNotifyExample.getSize() != 5){
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程"+ Thread.currentThread().getName() + "收到通知结束...");
        },"threadA");
    
    
        Thread threadB= new Thread(() -> {
                for(int i = 1 ; i <= 10 ;i ++){
                    waitNotifyExample.add("元素"+ i);
                    System.out.println("线程" + Thread.currentThread().getName() + "添加元素"+ i);
                    if(waitNotifyExample.getSize() == 5){
                        System.out.println("线程"+ Thread.currentThread().getName() + "发出通知...");
                        countDownLatch.countDown();
                    }
                   
                }
            
        },"threadB");
        
        threadA.start();
        threadB.start();
    
    
    
    
    
    
    }
    
    
    
    
}
