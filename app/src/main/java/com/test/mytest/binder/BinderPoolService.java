package com.test.mytest.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.test.mytest.IBinderPool;

public class BinderPoolService extends Service {

    private String name;

    @Override
    public void onCreate() {
        Log.d("123","======BinderPoolService onCreate===== ");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name = intent.getStringExtra("name");
        Log.d("123","======onStartCommand=====name: "+name);
        return super.onStartCommand(intent, flags, startId);
    }

    private Binder binder = new IBinderPool.Stub() {
        @Override
        public void setName(String mName) throws RemoteException {
            name = mName;
        }

        @Override
        public String getName() throws RemoteException {
            return name;
        }
    };
}
