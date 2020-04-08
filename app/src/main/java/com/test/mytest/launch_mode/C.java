package com.test.mytest.launch_mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.mytest.BaseActivity;
import com.test.mytest.BaseApplication;
import com.test.mytest.R;

public class C extends BaseActivity {
    private TextView tv ;
    private String TAG = "hai";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"---------C onCreate--------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_test);

        tv = findViewById(R.id.mode);
        tv.setText("C singleTop");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(C.this, D.class));
//                ((BaseApplication)getApplication()).startActivity();

                Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(C.this, D.class);
        startActivity(intent);
            }
        });
    }
}
