package com.test.mytest.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.mytest.IBinderPool;
import com.test.mytest.R;


public class ClientActivity extends Activity implements View.OnClickListener {

    private IBinderPool iBinderPool;
    private TextView tv1, tv2;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);

            try {
                tv1.setText(iBinderPool.getName());
                iBinderPool.setName("lisi");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder_client);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        findViewById(R.id.btn).setOnClickListener(this);

        Intent intent = new Intent(ClientActivity.this, BinderPoolService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                try {
                    iBinderPool.setName("wangWu");
                    tv2.setText(iBinderPool.getName());
                } catch (RemoteException e) {
                    Log.e("123", "error: " + e.toString());
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
