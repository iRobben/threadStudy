package com.zhangrenhua.threadStudy.two;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */
public class RealData  implements Data {
    
    private String result;
    
    public RealData(String requestParam){
    
        try {
            System.out.println("开始加载数据。。。");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.result = "真实请求结果";
    }
    
    @Override
    public String getData() {
        return result;
    }
}
