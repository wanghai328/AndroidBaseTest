package com.test.mytest.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.mytest.IBinderPool;
import com.test.mytest.R;



public class ClientActivity extends Activity implements View.OnClickListener {

    private IBinderPool iBinderPool;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_binder_server);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(this, BinderPoolService.class);
                intent.putExtra("name", "zhangSan");
                startService(intent);
                break;
            case R.id.btn2:
                startActivity(new Intent(ClientActivity.this, ClientActivity.class));
                break;
        }
    }
}
