package com.zhangrenhua.threadStudy.two;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */


public class FutureData implements Data {
    
    private RealData realData;
    
    private boolean isFinish = false;
    
    public synchronized void setRealData(RealData realData){
        if(isFinish){
            return;
        }
        this.realData = realData;
        isFinish = true;
        notify();
        System.out.println("数据加载完毕。。");
    }
    
    @Override
    public synchronized String getData() {
        while (!isFinish){
            try {
                System.out.println("数据未加载完，请等待。。。");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getData();
    }
}
