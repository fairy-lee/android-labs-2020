package edu.hzuapp.androidlabs.net1814080903337.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, "SqliteTest1.db", null, 1);//向系统申请一个SqliteTest.db文件存这个数据库，其中1是数据库版本。
    }
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        String sql=
                "create table if not exists Storage_of_goods("+
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                        "name VARCHAR(255),"+
                        "location VARCHAR(255),"+
                        "opendate VARCHAR(255),"+
                        "overduedate VARCHAR(255),"+
                        "isDel INTEGER DEFAULT 0"+
                        ")";//如果初次运行，建立一张Storage_of_goods表，建表的时候注意，自增是AUTOINCREMENT，而不是mysql的AUTO_INCREMENT
        sqliteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        //这里是更新数据库版本时所触发的方法
    }
}
