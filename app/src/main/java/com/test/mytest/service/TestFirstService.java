package com.test.mytest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestFirstService extends Service {

    @Override
    public void onCreate() {
        Log.d("123","-----service onCreate-----");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("123","-----service onStartCommand-----startId: "+startId);
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("123","-----service onBind-----");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.d("123","-----service onDestroy-----");
        super.onDestroy();
    }
}
