package com.bwie.myjingdongxiangmu.shujuku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2017/12/29.
 */

public class KuShuJu extends SQLiteOpenHelper{
    public KuShuJu(Context context) {
        super(context, "shujuku.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(id int primary key,sousuo varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
