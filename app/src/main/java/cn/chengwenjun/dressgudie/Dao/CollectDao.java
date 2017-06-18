package cn.chengwenjun.dressgudie.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.chengwenjun.dressgudie.bean.Collect;
import cn.chengwenjun.dressgudie.bean.User;

/**
 * Created by yazawanico on 2017/6/16.
 */

public class CollectDao {
    public static final String TABLE_NAME = "collect_info";
    private MyCollectHelper myHelper;
    private SQLiteDatabase db;
    private Collect collect;
    

    public CollectDao(Context context) {
        myHelper = new MyCollectHelper(context);
    }
    private ContentValues getContentValues(Collect collect) {
        ContentValues values = new ContentValues();
        values.put("top",collect.getTop());
        values.put("middle",collect.getMiddle());
        values.put("bottom",collect.getBottom());
        return values;
    }
    public Collect queryByEmail(String email) {
        db = myHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, "email =?", new String[]{email}, null, null, null);
        if (cursor.moveToNext()) {
            Integer top = cursor.getInt(1);
            Integer middle = cursor.getInt(2);
            Integer bottom = cursor.getInt(3);
            collect =  new Collect(top,middle,bottom,email);
        }
        cursor.close();
        db.close();
        return collect;
    }
    public long insert(Collect collect){
        db = myHelper.getWritableDatabase();
        ContentValues values = getContentValues(collect);
        long rowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return rowId;
    }

}
