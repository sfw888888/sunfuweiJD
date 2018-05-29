package com.bwie.myjingdongxiangmu.bean;

/**
 * Created by admin on 2018/1/12.
 */

public class MyPriceHeNumBean {
    private String price;
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public MyPriceHeNumBean(String price, int num) {
        this.price = price;
        this.num = num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
