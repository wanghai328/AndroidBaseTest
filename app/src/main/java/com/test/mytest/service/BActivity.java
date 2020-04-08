package com.test.mytest.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.mytest.R;
import com.test.mytest.launch_mode.C;

public class BActivity extends Activity implements View.OnClickListener {

    private TestSecondService service = null;
    private boolean isBind = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestSecondService.MyBinder myBinder = (TestSecondService.MyBinder) binder;
            service = myBinder.getService();
            int num = service.getRandomNumber();
            Log.d("123", "----B onServiceConnected-----");
            Log.e("123", "---B name---" + name);
            Log.d("123", "---B num---" + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("123", "----B onServiceDisconnected-----");
            isBind = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_a);


        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(this, TestSecondService.class);
                intent.putExtra("from", "Activity_B");
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn2:
                if (isBind) {
                    Log.i("123", "----------------------------------------------------------------------");
                    Log.i("123", "ActivityB 执行 unbindService");
                    unbindService(connection);
                }
                break;
            case R.id.btn4:
                finish();
                break;
        }
    }
}
