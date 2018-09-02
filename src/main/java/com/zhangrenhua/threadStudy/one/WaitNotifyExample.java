package com.zhangrenhua.threadStudy.one;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述：
 * </p>
 *
 * @author zhangrenhua
 * @since 2018/8/23
 */


public class WaitNotifyExample {
    
    private static volatile List<String> list = new ArrayList<String>();
    
    public void add(String s){
        list.add(s);
    }
    
    public int getSize(){
        return list.size();
    }
    
    
    public static void main(String[] args) {
        
        final WaitNotifyExample waitNotifyExample = new WaitNotifyExample();
        
        final Object lock = new Object();
        
        Thread threadA = new Thread(() -> {
            synchronized (lock){
                if(waitNotifyExample.getSize() != 5){
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "进入...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程"+ Thread.currentThread().getName() + "执行完毕...");
            }
        },"threadA");
    
    
        Thread threadB= new Thread(() -> {
            synchronized (lock){
                for(int i = 1 ; i <= 10 ;i ++){
                    waitNotifyExample.add("元素"+ i);
                    System.out.println("线程" + Thread.currentThread().getName() + "添加元素"+ i);
                    if(waitNotifyExample.getSize() == 5){
                        lock.notify();
                    }
                }
            }
        },"threadB");
        
        threadA.start();
        threadB.start();
    
    
    
    
    
    
    }
    
    
    
    
}
