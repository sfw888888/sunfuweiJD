package com.bwie.myjingdongxiangmu.util;

/**
 * Created by admin on 2018/1/6.
 */

public class ApiUtil {
    //https://www.zhaoapi.cn/product/getOrders?uid=4427&status=0
    public static String gongtong="https://www.zhaoapi.cn/";
    //
    public static String yonghuxinxi="user/getUserInfo";
    //订单列表?uid=71
    public static String dingdan2="product/getOrders";
    //修改订单?uid=71&status=1&orderId=1
    //uid 用户id字段 String类型 必传
    //orderId 订单id参数 String类型 必传
    //status  订单状态 String类型 必传
    public static String genxin2="product/updateOrder";


    public static String urlgrid=gongtong+"product/getCatagory";

    public static String urllist=gongtong+"ad/getAd";

    public static String zhuce=gongtong+"user/reg";

    public static String denglu=gongtong+"user/login";

    public static String sousuoliebiao=gongtong+"product/searchProducts";

    public static String jiarugouwuche=gongtong+"product/getProductDetail";

    //左边recyclerview
    public static String fenleizuo=gongtong+"product/getCatagory";

    //右边recyclerview
    public static String fenleiyou=gongtong+"product/getProductCatagory";

    public static String yonghu=gongtong+"user/getUserInfo";

    //uid  pid 添加
    public static String tianjia=gongtong+"product/addCart";
    //uid  查询
    public static String chaxun=gongtong+"product/getCarts";

    //更新购物车?uid=71&sellerid=1&pid=1&selected=0&num=10
    public static String gengxin=gongtong+"product/updateCarts";

    //删除购物车（新增）?uid=72&pid=1
    public static String shanchu=gongtong+"product/deleteCart";

    //创建订单?uid=71&price=99.99
    public static String create=gongtong+"product/createOrder";

    //订单列表?uid=71
    public static String dingdan=gongtong+"product/getOrders";
    //修改订单?uid=71&status=1&orderId=1
    //uid 用户id字段 String类型 必传
    //orderId 订单id参数 String类型 必传
    //status  订单状态 String类型 必传
    public static String genxin=gongtong+"product/updateOrder";




}
