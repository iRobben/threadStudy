package com.zhangrenhua.threadStudy.three;

/**
 * @author zhangrenhua
 * @title
 * @desc
 * @date 2018/8/25
 */


public class Task {
    
    private int id;
    
    private String name ;
    
    private int price;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public Task(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
