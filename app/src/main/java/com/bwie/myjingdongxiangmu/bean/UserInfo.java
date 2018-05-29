package com.bwie.myjingdongxiangmu.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by admin on 2018/1/11.
 */

public class UserInfo implements Serializable {
    private Bitmap bitmap1;

    public UserInfo(Bitmap bitmap1) {
        this.bitmap1 = bitmap1;
    }

    public Bitmap getBitmap1() {
        return bitmap1;
    }

    public void setBitmap1(Bitmap bitmap1) {
        this.bitmap1 = bitmap1;
    }
}
