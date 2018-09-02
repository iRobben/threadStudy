package com.zhangrenhua.threadStudy.one;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述：实现blockQueue
 * </p>
 *
 * @author zhangrenhua
 * @since 2018/8/24
 */


public class QueueExample {
    
    //1.准备一个容器
    private static LinkedList<Object> list = new LinkedList<>();
    
    //2.容器大小边界
    private final int minSize = 0 ;
    
    private final int maxSize;
    
    //3.构造函数
    public QueueExample(int size){
       this.maxSize = size;
    }
    
    //4.准备一把锁
    private final Object lock = new Object();
    
    //5.准备一个计数器
    private AtomicInteger count = new AtomicInteger();
    
    //5.添加元素
    public void put(Object o){
        synchronized (lock){
            while (count.get() == maxSize){
                try {
                    System.out.println("队列已满，请等待.....");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(o);
            System.out.println("添加元素"+ o.toString() + "到队列成功....");
            count.incrementAndGet();
            lock.notify();
        }
    }
    
    //6.取元素
    public Object get(){
        Object result;
        synchronized (lock){
            while (count.get() == 0 ){
                try {
                    System.out.println("队列为空，请等待.....");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result = list.removeFirst();
            System.out.println("取出元素"+ result.toString() + "到队列成功....");
            count.decrementAndGet();
            lock.notify();
        
        }
        return result;
    }
    
    public static void main(String[] args) {
        QueueExample queueExample = new QueueExample(4);
        queueExample.put("a");
        queueExample.put("b");
        queueExample.put("c");
        queueExample.put("d");
        
        Thread thread1 = new Thread(()->{
            queueExample.put("e");
            queueExample.put("f");
        
        },"t1");
    
        Thread thread2 = new Thread(()->{
            queueExample.get();
            queueExample.get();
        
        },"t2");
        
        thread1.start();
        
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    
    }
    
    
}
