package com.zhangrenhua.threadStudy.four;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/28
 */


public class CountDownLatchExample {
    
    public static void main(String[] args) {
    
        CountDownLatch countDownLatch = new CountDownLatch(2);
        
        Thread thread1 = new Thread(()->{
    
            try {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完");
            countDownLatch.countDown();
            
        },"t1");
    
    
        Thread thread2 = new Thread(()->{
        
            try {
                System.out.println(Thread.currentThread().getName() + "开始执行");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行完");
            countDownLatch.countDown();
        
        },"t2");
    
        thread1.start();
        thread2.start();
    
        //主线程等待两个线程执行完毕
        try {
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + "结束。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
      
    
    }
}
