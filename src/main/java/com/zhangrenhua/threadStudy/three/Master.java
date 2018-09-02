package com.zhangrenhua.threadStudy.three;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */


public class Master {
    
    //1.承接任务的队列
    private ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();
    
    //2.保存worker
    private HashMap<String,Thread> workers = new HashMap<>();
    
    //3.保存worker返回的结果
    private ConcurrentHashMap<String,Object> result = new ConcurrentHashMap<>();
    
    //4.构造函数
    public Master(Worker worker,int workerNum){
        worker.setTaskQueue(taskQueue);
        worker.setResult(result);
        for(int i = 1 ; i <= workerNum ;i++ ){
            workers.put("子节点"+ Integer.toString(i),new Thread(worker));
        }
    }
    
    //5.提交任务
    public void submit(Task task){
        taskQueue.add(task);
    }
    
    //6.执行任务
    public void execute(){
        for(Map.Entry<String,Thread> entry : workers.entrySet()){
            entry.getValue().start();
        }
    }
    
    //7.所有任务是否已经结束
    public boolean isFinish(){
        for(Map.Entry<String,Thread> entry : workers.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }
    
    //8.汇总结果
    public int total(){
        int total = 0;
        for(Map.Entry<String,Object> entry : result.entrySet()){
            total += (Integer) entry.getValue();
        }
        return total;
    }
    
    public static void main(String[] args) {
        Master master = new Master(new Worker(),Runtime.getRuntime().availableProcessors());
        for(int i = 1 ; i <= 100 ; i++){
            master.submit(new Task(i,"name_"+ i,i));
        }
        long time = System.currentTimeMillis();
        master.execute();
        while (true){
            if(master.isFinish()){
                System.out.println("结果：" + master.total() + ",耗时" + (System.currentTimeMillis() - time) + "ms");
                break;
            }
        }
    }
}
