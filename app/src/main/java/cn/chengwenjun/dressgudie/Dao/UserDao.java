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
    private MyHelper myHelper;
    private SQLiteDatabase db;
    private User user;

    public UserDao(Context context) {//todo:Context是什么意思
        myHelper = new MyHelper(context);
    }

    public long insert(User user) {
        db = myHelper.getWritableDatabase();
        ContentValues values = getContentValues(user);

        long rowId = db.insert("user_info", null, values);
        db.close();
        return rowId;
    }

    @NonNull
    private ContentValues getContentValues(User user) {
        ContentValues values = new ContentValues();

        values.put("userId",user.getUserId());
        values.put("email",user.getEmail());
        values.put("password", user.getPassword());
        values.put("name", user.getName());

        return values;
    }

    public List<User> queryAll() {
        db = myHelper.getReadableDatabase();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query("user_info", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Integer useId = cursor.getInt(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String name = cursor.getString(4);
            user = new User();
            userList.add(user);
        }
        cursor.close();
        db.close();
        return userList;
    }

    public User queryByEmail(String email) {
        db = myHelper.getReadableDatabase();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query("user_info", null, "email =?", new String[]{email}, null, null, null);
        if (cursor.moveToNext()) {
            Integer useId = cursor.getInt(1);
            //String email = cursor.getString(2);
            String password = cursor.getString(3);
            String name = cursor.getString(4);

            user = new User(useId,email,password,name);

        }
        cursor.close();
        db.close();
        return user;
    }
    public User queryById(int userId) {
        db = myHelper.getReadableDatabase();
        List<User> userList = new ArrayList<User>();
        Cursor cursor = db.query("user_info", null, "userId =?", new String[]{String.valueOf(userId)},null,null,null);
        if (cursor.moveToNext()) {
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String name = cursor.getString(4);
            user = new User(userId,email,password,name);
        }
        cursor.close();
        db.close();
        return user;
    }

    public int delete(String name) {
        db = myHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        int count = db.delete("user_info", "name = ?", new String[]{name});
        db.close();
        return count;
    }

    public int update(User user) {
        db = myHelper.getWritableDatabase();
        ContentValues values = getContentValues(user);
        int count = db.update("user_info", values, "name = ?", new String[]{user.getName()});
        db.close();
        return count;
    }
}