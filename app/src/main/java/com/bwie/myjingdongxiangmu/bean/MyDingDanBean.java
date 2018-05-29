package com.bwie.myjingdongxiangmu.bean;

import java.util.List;

/**
 * Created by admin on 2018/1/15.
 */

public class MyDingDanBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2018-01-15T09:14:57","orderid":6917,"price":5599,"status":0,"title":"订单测试标题4427","uid":4427},{"createtime":"2018-01-15T09:18:16","orderid":6918,"price":5599,"status":0,"title":"订单测试标题4427","uid":4427}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-01-15T09:14:57
         * orderid : 6917
         * price : 5599.0
         * status : 0
         * title : 订单测试标题4427
         * uid : 4427
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
