package com.test.mytest.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.test.mytest.IBinderPool;

public class BinderPoolService extends Service {

    private String name;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        name = intent.getStringExtra("name");
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
