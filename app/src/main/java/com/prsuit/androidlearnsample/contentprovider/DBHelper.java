package com.prsuit.androidlearnsample.contentprovider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Description:
 * @Author: sh
 * @Date: 2020/8/4
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "com_sample_provider.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String BOOK_TABLE_NAME = "book";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS "
            + USER_TABLE_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(64))";
    private static final String CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS "
            + BOOK_TABLE_NAME
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(64))";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据表格:用户表和图书表
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void clearTable(Context context){
        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();
        database.execSQL("delete from user");
    }
}
