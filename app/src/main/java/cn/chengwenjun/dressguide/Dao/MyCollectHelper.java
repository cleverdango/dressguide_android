package cn.chengwenjun.dressguide.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yazawanico on 2017/6/16.
 */

public class MyCollectHelper extends SQLiteOpenHelper {
    public MyCollectHelper(Context context) {
        super(context, "collect.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table collect_info( collectId integer primary key autoincrement, " +
                "email text ," +
                "top text ," +
                "middle text ," +
                "bottom text )"
        );


        Log.i("66", "onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i("66", "onUpgrade");
    }
}
