package com.test.mytest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class TestSecondService extends Service {
    private final Random generator = new Random();
    @Override
    public void onCreate() {
        Log.d("123","TestSecondService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("123","TestSecondService onStartCommand "+intent.getStringExtra("from"));
        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("123","TestSecondService onUnbind");
        return false;
    }

    @Override
    public void onDestroy() {
        Log.d("123","TestSecondService onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("123","TestSecondService onBind:  "+intent.getStringExtra("from"));
        return binder;
    }


    public int getRandomNumber(){
        return generator.nextInt();
    }

    public class MyBinder extends Binder{
        public TestSecondService getService(){
            return TestSecondService.this;
        }
    }

    private MyBinder binder = new MyBinder();

}
