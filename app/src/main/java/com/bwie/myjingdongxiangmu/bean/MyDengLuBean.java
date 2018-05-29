package com.bwie.myjingdongxiangmu.bean;

/**
 * Created by admin on 2018/1/6.
 */

public class MyDengLuBean {

    /**
     * code : 0
     * data : {"appkey":"a0b1410dc3bf2280","appsecret":"2218FDF3A121E79A66340783AEEDBE86","createtime":"2018-01-06T10:02:35","mobile":"15510744234","password":"499ACDBEF8CB57EE4B70CDDA3A7C5719","token":"AE762F9EAA5AB510E533D93F75B38D55","uid":10867,"username":"15510744234"}
     * msg : 登录成功
     */

    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * appkey : a0b1410dc3bf2280
         * appsecret : 2218FDF3A121E79A66340783AEEDBE86
         * createtime : 2018-01-06T10:02:35
         * mobile : 15510744234
         * password : 499ACDBEF8CB57EE4B70CDDA3A7C5719
         * token : AE762F9EAA5AB510E533D93F75B38D55
         * uid : 10867
         * username : 15510744234
         */

        private String appkey;
        private String appsecret;
        private String createtime;
        private String mobile;
        private String password;
        private String token;
        private int uid;
        private String username;

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
