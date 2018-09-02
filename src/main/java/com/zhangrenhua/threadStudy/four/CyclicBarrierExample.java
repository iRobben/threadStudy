package com.zhangrenhua.threadStudy.four;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/28
 */


public class CyclicBarrierExample {

    static class Runner implements Runnable{
        
        private CyclicBarrier cyclicBarrier;
        
        private String name ;
        
        public Runner(CyclicBarrier cyclicBarrier,String name){
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }
    
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * new Random().nextInt(5));
                System.out.println(name + "已经准备好。");
                cyclicBarrier.await();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + "，go。");
        }
    }
    
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runner(cyclicBarrier,"zhangsan"));
        executorService.submit(new Runner(cyclicBarrier,"lisi"));
        executorService.submit(new Runner(cyclicBarrier,"wangwu"));
        executorService.shutdown();
    }


}
