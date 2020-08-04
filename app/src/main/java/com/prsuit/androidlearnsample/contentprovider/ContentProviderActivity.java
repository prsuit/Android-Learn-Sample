package com.prsuit.androidlearnsample.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.prsuit.androidlearnsample.R;

public class ContentProviderActivity extends AppCompatActivity {
    private static final String AUTHORITY = "com.prsuit.myprovider";
    private static final Uri USER_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, ContentProviderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }

    public void insertValue(View view){
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("id",0);
        contentValues1.put("name","kobe");
        Uri insert1 = getContentResolver().insert(USER_URI, contentValues1);
        System.out.println("--insertValue1-->"+insert1.toString());

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("id",1);
        contentValues2.put("name","sh1");
        Uri insert2 = getContentResolver().insert(USER_URI,contentValues2);
        System.out.println("--insertValue2-->"+insert2.toString());
    }

    public void updateValue(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",1);
        contentValues.put("name","sh2");
        int row = getContentResolver().update(USER_URI,contentValues,"id = ?", new String[]{"1"});
        System.out.println("--updateValue-->"+row);
    }

    public void deleteValue(View view){
        int row = getContentResolver().delete(USER_URI,"name = ?",new String[]{"sh2"});
        System.out.println("--deleteValue-->"+row);
    }

    public void queryValue(View view){
        Cursor cursor = getContentResolver().query(USER_URI, new String[]{"id", "name"}, null, null, null);
        while (cursor.moveToNext()){
            System.out.println("query user："+cursor.getInt(0)+" "+cursor.getString(cursor.getColumnIndex("name")));
        }
        cursor.close();
    }

    public void clearTableData(View view){
        DBHelper.clearTable(this);
    }
}
