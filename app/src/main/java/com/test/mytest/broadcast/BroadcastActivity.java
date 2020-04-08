package com.test.mytest.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.test.mytest.R;

public class BroadcastActivity extends Activity implements View.OnClickListener {

    private final String NORMAL_ACTION = "com.test.wh";
    Button btn;
    LocalBroadcastManager lm;
    MyReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        btn = findViewById(R.id.send_broadcast);

        initReceiver();

        btn.setOnClickListener(this);
    }

    private void initReceiver() {
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter(NORMAL_ACTION);
        lm = LocalBroadcastManager.getInstance(this);
        lm.registerReceiver(receiver, intentFilter);

//        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_broadcast:
                Intent intent = new Intent(NORMAL_ACTION);
                intent.putExtra("config", "123");
                lm.sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lm.unregisterReceiver(receiver);
    }
}
