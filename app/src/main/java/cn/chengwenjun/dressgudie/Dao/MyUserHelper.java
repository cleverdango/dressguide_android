package cn.chengwenjun.dressgudie.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yazawanico on 2017/6/13.
 */

public class MyUserHelper extends SQLiteOpenHelper {
    public MyUserHelper(Context context){
        super(context,"user.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user_info(userId integer primary key autoincrement,"+
                "email text,"+
                "password text,"+
                "name text)"
               );


        Log.i("66","onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("alter table user_info add sex text;");
        Log.i("66","onUpgrade");
    }


}