package com.zhangrenhua.threadStudy.two;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */


public class FutureClient {
    
    public Data getRequest(){
        final FutureData futureData = new FutureData();
        Thread thread = new Thread(()->{
            RealData realData = new RealData("请求参数");
            futureData.setRealData(realData);
        });
        thread.start();
        return futureData;
    }
    
    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();
        Data data = futureClient.getRequest();
        System.out.println(data.getData());
    
    
    }
}
