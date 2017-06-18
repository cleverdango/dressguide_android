package cn.chengwenjun.dressgudie.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import cn.chengwenjun.dressgudie.bean.User;

/**
 * Created by yazawanico on 2017/6/13.
 */

public class UserDao {
    public static final String TABLE_NAME = "user_info";
    private MyUserHelper myHelper;
    private SQLiteDatabase db;
    private User user;

    public UserDao(Context context) {//todo:Context是什么意思
        myHelper = new MyUserHelper(context);
    }

    public long insert(User user) {
        db = myHelper.getWritableDatabase();
        ContentValues values = getContentValues(user);

        long rowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return rowId;
    }

    @NonNull
    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put("email",user.getEmail());
        values.put("password", user.getPassword());
        values.put("name", user.getName());
        return values;
    }

    public List<User> queryAll() {
        db = myHelper.getReadableDatabase();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Integer useId = cursor.getInt(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String name = cursor.getString(3);
            user = new User(useId,email,password,name);
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public User queryByEmail(String email) {
        db = myHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, "email =?", new String[]{email}, null, null, null);
        if (cursor.moveToNext()) {
            Integer useId = cursor.getInt(0);
            String password = cursor.getString(2);
            String name = cursor.getString(3);

            user = new User(useId,email,password,name);

        }
        cursor.close();
        db.close();
        return user;
    }
    public User queryById(int userId) {
        db = myHelper.getReadableDatabase();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query(TABLE_NAME, null, "userId =?", new String[]{String.valueOf(userId)},null,null,null);
        if (cursor.moveToNext()) {
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String name = cursor.getString(3);
            user = new User(userId,email,password,name);
        }
        cursor.close();
        db.close();
        return user;
    }

    public int delete(String email) {
        db = myHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        int count = db.delete(TABLE_NAME, "email = ?", new String[]{email});
        //todo:似乎有点问题
        db.close();
        return count;
    }

    public int update(User user) {
        db = myHelper.getWritableDatabase();
        ContentValues values = getContentValues(user);
        int count = db.update(TABLE_NAME, values, "email = ?", new String[]{user.getEmail()});
        db.close();
        return count;
    }
}