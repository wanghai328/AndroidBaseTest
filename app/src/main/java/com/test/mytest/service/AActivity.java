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

public class AActivity extends Activity implements View.OnClickListener {
    private TestSecondService service;
    private boolean isBind = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            TestSecondService.MyBinder myBinder = (TestSecondService.MyBinder) binder;
            service = myBinder.getService();
            int num = service.getRandomNumber();
            Log.d("123", "----onServiceConnected-----");
            Log.e("123", "---name---" + name);
//            Log.d("123", "---num---" + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("123", "----onServiceDisconnected-----");
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
            // bind
            case R.id.btn1:
                Intent intent = new Intent(this,TestSecondService.class);
                intent.putExtra("from","Activity_A");
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
                // unbind
            case R.id.btn2:
                Log.e("123","isBind: "+isBind);
                if(isBind){
                    unbindService(connection);
                }
                break;
            case R.id.btn3:
                startActivity(new Intent(this,BActivity.class));
                break;
            case R.id.btn4:
                finish();
                break;
        }
    }
}
