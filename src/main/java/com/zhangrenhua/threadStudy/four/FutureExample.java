package com.zhangrenhua.threadStudy.four;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/30
 */


public class FutureExample implements Callable<String> {
    
    private String queryStr;
    
    public FutureExample(String queryStr){
        this.queryStr = queryStr;
    }
    
    @Override
    public String call() throws Exception {
        //表示真实处理耗时
        Thread.sleep(3000);
        return "real result:"+queryStr;
    }
    
    
    public static void main(String[] args) {
    
        FutureTask<String> futureTask = new FutureTask<String>(new FutureExample("aaaa"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        
        
        Future future = executorService.submit(futureTask);
    
        System.out.println("开始处理其他逻辑。。。");
        try {
            Thread.sleep(2500);
            //System.out.println(future.get()); 需要判断返回是不是null
            System.out.println(futureTask.get()); //等待有结果才会返回
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
