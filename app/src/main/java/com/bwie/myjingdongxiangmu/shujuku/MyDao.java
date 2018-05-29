package com.bwie.myjingdongxiangmu.shujuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/12/29.
 */

public class MyDao {
    private KuShuJu kuShuJu;

    public MyDao(Context context){
        kuShuJu = new KuShuJu(context);
    }
    public int addtian(String sousuo){
        Log.d("+++++",sousuo);
        SQLiteDatabase db = kuShuJu.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sousuo",sousuo);
        db.insert("student",null,values);
        db.close();

        return 1;
    }
    public List<String> select(){
        SQLiteDatabase db = kuShuJu.getWritableDatabase();
        List<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from student", null);
        while (cursor.moveToNext()){
            String string = cursor.getString(1);
            list.add(string);
        }
        return list;
    }
    public int delet(String i){
        SQLiteDatabase db = kuShuJu.getWritableDatabase();
        db.execSQL("delete from student where sousuo=?",new String[]{i});

        db.close();

        return 1;
    }
    public void delete(){
        SQLiteDatabase db = kuShuJu.getWritableDatabase();
        db.execSQL("delete from student");
        db.close();
    }
}
