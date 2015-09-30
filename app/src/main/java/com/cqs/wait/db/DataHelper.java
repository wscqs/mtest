package com.cqs.wait.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.cqs.wait.entity.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/5 0005.
 */
public class DataHelper {
    // 数据库名称
    private static String DB_NAME = "user.db";
    // 数据库版本
    private static int DB_VERSION = 1;
    private SQLiteDatabase db;
    private SqliteHelper dbHelper;

    public DataHelper(Context context) {
        dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION );
        db = dbHelper.getWritableDatabase();
    }

    public void Close() {
        db.close();
        dbHelper.close();
    }


//    public List<UserInfo> GetUserList() {
//        List<UserInfo> userList = new ArrayList<UserInfo>();
//        Cursor cursor = db.query(SqliteHelper. TB_NAME, null, null , null, null,
//                null, UserInfo. ID + " DESC");
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast() && (cursor.getString(1) != null )) {
//            UserInfo user = new UserInfo();
//            user.setId(cursor.getString(0));
//            user.setUserName(cursor.getString(1));
//            user.setPassword(cursor.getString(2));
//            ByteArrayInputStream stream = new ByteArrayInputStream(cursor.getBlob(3));
//            Drawable icon = Drawable.createFromStream(stream, "image");
//            user.setUserIcon(icon);
//            userList.add(user);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return userList;
//    }

//    // 判断users表中的是否包含某个UserID的记录
//    public Boolean HaveUserInfo(String UserId) {
//        Boolean b = false;
//        Cursor cursor = db.query(SqliteHelper. TB_NAME, null, UserInfo.ID
//                + "=?", new String[]{UserId}, null, null, null );
//        b = cursor.moveToFirst();
//        Log. e("HaveUserInfo", b.toString());
//        cursor.close();
//        return b;
//    }

//    // 更新users表的记录，根据UserId更新用户昵称和用户图标
//    public int UpdateUserInfo(String userName, Bitmap userIcon, String UserId) {
//        ContentValues values = new ContentValues();
//        values.put(UserInfo. USERNAME, userName);
//        // BLOB类型
//        final ByteArrayOutputStream os = new ByteArrayOutputStream();
//        // 将Bitmap压缩成PNG编码，质量为100%存储
//        userIcon.compress(Bitmap.CompressFormat. PNG, 100, os);
//        // 构造SQLite的Content对象，这里也可以使用raw
//        values.put(UserInfo. USERICON, os.toByteArray());
//        int id = db.update(SqliteHelper. TB_NAME, values, UserInfo.USERID + "=?" , new String[]{UserId});
//        Log. e("UpdateUserInfo2", id + "");
//        return id;
//    }

//    // 更新users表的记录
//    public int UpdateUserInfo(UserInfo user) {
//        ContentValues values = new ContentValues();
//        values.put(UserInfo. USERID, user.getUserId());
//        values.put(UserInfo. TOKEN, user.getToken());
//        values.put(UserInfo. TOKENSECRET, user.getTokenSecret());
//        int id = db.update(SqliteHelper. TB_NAME, values, UserInfo.USERID + "="
//                + user.getUserId(), null);
//        Log. e("UpdateUserInfo", id + "");
//        return id;
//    }

    // 添加users表的记录
    public Long SaveUserInfo(UserInfo user) {
        ContentValues values = new ContentValues();

        values.put(UserInfo.USERNAME,user.getUserName());
        values.put(UserInfo.PASSWORD,user.getPassword());
        values.put(UserInfo.USERICON,user.getUserIcon());

        Long uid = db.insert(SqliteHelper.TB_NAME, UserInfo.ID, values);
        Log. e("SaveUserInfo", uid + "");
        return uid;
    }

    public UserInfo select(UserInfo ui) {
        UserInfo userInfo=new UserInfo();
        Cursor cursor = db.query(SqliteHelper.TB_NAME, null, UserInfo.USERNAME + "=?" + " AND " + UserInfo.PASSWORD + "=?", new String[]{ui.getUserName(), ui.getPassword()}
                , null, null, null);
        while (cursor.moveToNext()){
            ui.setUserIcon(cursor.getString(3));
            return ui;

        }
        cursor.close();
        return null;

    }

    public void vote(String userName, String s) {
        ContentValues values=new ContentValues();
        values.put("votes", s);
        int update = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERNAME+"="+userName,null);
    }

    public void sign(String userName, String yes) {
        ContentValues values=new ContentValues();
        values.put("sign", yes);
        int update = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERNAME+"="+userName,null);
    }
    public List<UserInfo> GetUserList() {
        List<UserInfo> userList = new ArrayList<UserInfo>();
        Cursor cursor = db.query(SqliteHelper. TB_NAME, null, null , null, null,
                null, UserInfo. ID + " DESC");
        while (cursor.moveToNext()) {
            UserInfo user = new UserInfo();
            user.setId(cursor.getString(0));
//            user.setId(cursor.getString(cursor.getColumnIndex("_id")));
            user.setUserName(cursor.getString(1));
            user.setSign(cursor.getString(4));
            user.setVotes(cursor.getString(5));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }

//    // 添加users表的记录
//    public Long SaveUserInfo(UserInfo user, byte[] icon) {
//        ContentValues values = new ContentValues();
//        values.put(UserInfo. USERID, user.getUserId());
//        values.put(UserInfo. USERNAME, user.getUserName());
//        values.put(UserInfo. TOKEN, user.getToken());
//        values.put(UserInfo. TOKENSECRET, user.getTokenSecret());
//        if(icon!= null){
//            values.put(UserInfo. USERICON, icon);
//        }
//        Long uid = db.insert(SqliteHelper. TB_NAME, UserInfo.ID, values);
//        Log. e("SaveUserInfo", uid + "");
//        return uid;
//    }

//    // 删除users表的记录
//    public int DelUserInfo(String UserId) {
//        int id = db.delete(SqliteHelper. TB_NAME,
//                UserInfo. USERID + "=?", new String[]{UserId});
//        Log. e("DelUserInfo", id + "");
//        return id;
//    }


}
