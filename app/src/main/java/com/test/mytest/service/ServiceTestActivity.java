package com.test.mytest.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.mytest.R;

public class ServiceTestActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);


        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //连续启动Service
                Intent intentOne = new Intent(this, TestFirstService.class);
                startService(intentOne);
                Intent intentTwo = new Intent(this, TestFirstService.class);
                startService(intentTwo);
                Intent intentThree = new Intent(this, TestFirstService.class);
                startService(intentThree);

                break;
            case R.id.btn2:
                //停止Service
                Intent intentFour = new Intent(this, TestFirstService.class);
                stopService(intentFour);
                break;
        }
    }
}
