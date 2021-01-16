package edu.hzuapp.androidlabs.net1814080903337.dao;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import edu.hzuapp.androidlabs.net1814080903337.model.Storage;


public class StorageDao {
    private DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
    private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象

    public StorageDao(Context context)// 定义构造函数
    {
        dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
    }
    // 插入用户数据
    public void dbInsert(String name, String location,String opendate,String overduedate) {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
        String sql = "insert into Storage_of_goods(name,location,opendate,overduedate,isDel) values (?,?,?,?,0)";
        // 传递过来的name,location,opendate,overduedate分别按顺序替换上面sql语句的四个?，自动转换类型，下同，不再赘述
        Object bindArgs[] = new Object[] { name,location,opendate,overduedate};
        // 执行这条无返回值的sql语句
        sqliteDatabase.execSQL(sql, bindArgs);
    }
     // 求出表中有多少条数据
    public int dbGetUserSize() {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
        String sql = "select count(*) from Storage_of_goods where isDel=0";
        Cursor cursor = sqliteDatabase.rawQuery(sql, null);
        if (cursor.moveToNext())// 判断Cursor中是否有数据
        {
            return cursor.getInt(0);// 返回总记录数
        }
        return 0;// 如果没有数据，则返回0
    }
    //通过输入名称寻找数据
    public Storage dbQueryOneByUsername(String name) {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
        String sql = "select * from Storage_of_goods where name=? and isDel=0";
        String[] selectionArgs = new String[] { name };
        Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
            if (cursor.moveToNext())// 判断Cursor中是否有数据
            {
                // 如果有用户，则把查到的值填充这个用户实体
                Storage storage = new Storage();
                storage.setId(cursor.getInt(cursor.getColumnIndex("id")));
                storage.setName(cursor.getString(cursor.getColumnIndex("name")));
                storage.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                storage.setOpendate(cursor.getString(cursor.getColumnIndex("opendate")));
                storage.setOverduedate(cursor.getString(cursor.getColumnIndex("overduedate")));
                return storage;// 返回一个物品给前台
            }
        return null;// 没有返回null
    }

    // 查询所有物品
    public ArrayList<Storage> dbQueryAll() {
        ArrayList<Storage> storagesArrayList = new ArrayList<Storage>();
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
        String sql = "select * from Storage_of_goods where isDel=0";
        Cursor cursor = sqliteDatabase.rawQuery(sql, null);
        // 游标从头读到尾
        for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
            if (cursor.getInt(cursor.getColumnIndex("isDel")) != 1) {
                Storage storage = new Storage();
                storage.setId(cursor.getInt(cursor.getColumnIndex("id")));
                storage.setName(cursor.getString(cursor.getColumnIndex("name")));
                storage.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                storage.setOpendate(cursor.getString(cursor.getColumnIndex("opendate")));
                storage.setOverduedate(cursor.getString(cursor.getColumnIndex("overduedate")));
                storagesArrayList.add(storage);
            }
        }
        return storagesArrayList;
    }
    // 删除物品，其实是把相应的isDel值从0改1
    public void dbDeleteUser(int id) {
        sqliteDatabase = dbOpenHelper.getWritableDatabase();
        String sql = "update Storage_of_goods set isDel=1 where id=?";
        Object bindArgs[] = new Object[] { id };
        sqliteDatabase.execSQL(sql, bindArgs);
    }

}
