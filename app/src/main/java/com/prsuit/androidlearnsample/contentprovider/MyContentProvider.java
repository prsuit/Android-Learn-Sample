package com.prsuit.androidlearnsample.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Description: 自定义content provider
 * @Author: sh
 * @Date: 2020/8/4
 */
public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";

    private Context mContext;
    private DBHelper dbHelper = null;
    private SQLiteDatabase mDatabase = null;
    private String matchTableName = null;

    //ContentProvider的唯一标识
    private static final String AUTHORITY = "com.prsuit.myprovider";
    private static final String USER_PATH = "user";
    private static final String BOOK_PATH = "book";
    private static final int USER_CODE = 1;
    private static final int BOOK_CODE = 2;
    private static UriMatcher matcher;

    //在ContentProvider 中注册URI
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 若URI资源路径 = content://com.prsuit.myprovider/user ，则返回注册码USER_CODE
        // 若URI资源路径 = content://com.prsuit.myprovider/book ，则返回注册码BOOK_CODE
        matcher.addURI(AUTHORITY,USER_PATH,USER_CODE);
        matcher.addURI(AUTHORITY,BOOK_PATH,BOOK_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        dbHelper = new DBHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        matchTableName = getMatchTableName(uri);
        long row = -1;//返回值是插入数据所在的行号
        row = mDatabase.insert(matchTableName,null,values);
        if (row > -1){
            mContext.getContentResolver().notifyChange(uri,null);
            return ContentUris.withAppendedId(uri,row);
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        matchTableName = getMatchTableName(uri);
        Cursor cursor = mDatabase.query(matchTableName,projection,selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        matchTableName = getMatchTableName(uri);
        //返回值代表此次操作影响到的行数
        int deleteRow = mDatabase.delete(matchTableName, selection, selectionArgs);
        Log.e(TAG, "delete: --->"+deleteRow);
        if (deleteRow > 0){
            mContext.getContentResolver().notifyChange(uri,null);
        }
        return deleteRow;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        matchTableName = getMatchTableName(uri);
        //返回值代表此次操作影响到的行数
        int updateRow = mDatabase.update(matchTableName, values, selection, selectionArgs);
        Log.e(TAG, "update: --->"+updateRow);
        if (updateRow > 0){
            mContext.getContentResolver().notifyChange(uri,null);
        }
        return updateRow;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     * @param uri
     * @return
     */
    public String getMatchTableName(Uri uri){
        String tableName = null;
        int uriCode = matcher.match(uri);
        switch (uriCode){
            case USER_CODE:
                tableName = DBHelper.USER_TABLE_NAME;
                break;
            case BOOK_CODE:
                tableName = DBHelper.BOOK_TABLE_NAME;
                break;
        }
        return tableName;
    }
}
