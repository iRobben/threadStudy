package com.zhangrenhua.threadStudy.four;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/30
 */


public class SemaPhoreExmaple {
    
    public static void main(String[] args) {
    
        ExecutorService executorService = Executors.newCachedThreadPool();
    
        //控制只有5个线程同时访问
        final  Semaphore semaphore = new Semaphore(5);
        //模拟20个
        for(int i = 0 ; i < 20 ; i++){
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
    
                    try {
                        //获取许可证
                        semaphore.acquire();
                        System.out.println("线程"+ no + "正在执行中");
                        //处理逻辑
                        Thread.sleep(5000);
                        //释放
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
    
                }
            };
            executorService.execute(runnable);
        }
    }
}
