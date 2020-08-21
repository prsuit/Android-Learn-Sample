package com.prsuit.androidlearnsample.ipc.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.prsuit.androidlearnsample.Book;
import com.prsuit.androidlearnsample.R;
import com.prsuit.androidlearnsample.ipc.server.BookManager;
import com.prsuit.androidlearnsample.ipc.server.RemoteService;
import com.prsuit.androidlearnsample.ipc.server.Stub;

import java.util.List;

/**
 * @Description: 客户端
 * @Author: sh
 * @Date: 2020/8/21
 */
public class ClientActivity extends AppCompatActivity {

    private BookManager bookManager;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isBound){
            toBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isBound){
            unbindService(connection);
        }
    }

    public void addBook(View view){
        if (!isBound){
            toBindService();
            return;
        }
        Book book = new Book();
        book.setName("addBook");
        book.setPrice(20);
        try {
            bookManager.addBook(book);
            Log.e("TAG", "addBook: "+bookManager.getBooks().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void toBindService() {
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction("");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = Stub.asInterface(service);
            isBound = true;
            if (bookManager != null){
                try {
                    List<Book> books = bookManager.getBooks();
                    Log.e("TAG", "onServiceConnected: "+books.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, ClientActivity.class));
    }
}
