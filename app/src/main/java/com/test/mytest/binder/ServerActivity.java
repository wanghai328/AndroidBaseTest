package com.test.mytest.binder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.mytest.R;

public class ServerActivity extends Activity implements View.OnClickListener {
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
                intent.putExtra("name", "ZhangSan");
                startService(intent);
                break;
            case R.id.btn2:
                startActivity(new Intent(ServerActivity.this, ClientActivity.class));
                break;
        }
    }
}
