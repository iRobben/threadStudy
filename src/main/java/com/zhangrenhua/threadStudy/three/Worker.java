package com.zhangrenhua.threadStudy.three;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */


public class Worker implements Runnable {
    
    private ConcurrentLinkedQueue<Task> taskQueue;
    
    private ConcurrentHashMap<String, Object> result;
    
    public void setTaskQueue(ConcurrentLinkedQueue<Task> taskQueue) {
        this.taskQueue = taskQueue;
    }
    
    public void setResult(ConcurrentHashMap<String, Object> result) {
        this.result = result;
    }
    
    @Override
    public void run() {
        while (true){
            Task task = this.taskQueue.poll();
            if(task == null){
                break;
            }
            try {
                //做计算
                Thread.sleep(500);
                this.result.put(String.valueOf(task.getId()), task.getPrice() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
