package com.cqs.wait.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cqs.wait.entity.UserInfo;

/**
 * Created by Administrator on 2015/6/4 0004.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    public static final String TB_NAME= "users";
    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE IF NOT EXISTS "+
                TB_NAME+ "("+
                UserInfo. ID+ " integer primary key,"+
                UserInfo. USERNAME+ " varchar,"+
                UserInfo. PASSWORD+ " varchar,"+
                UserInfo. USERICON+ " blob,"+
                "sign varchar DEFAULT NO,"+"votes varchar"+
                 ")";
                db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TB_NAME );
        onCreate(db);
    }
}
