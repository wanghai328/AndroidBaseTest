package com.test.mytest.launch_mode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.mytest.BaseActivity;
import com.test.mytest.R;

public class D extends BaseActivity {
    private TextView tv ;
    private String TAG = "hai";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         Log.i(TAG,"---------D onCreate--------");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_test);

        tv = findViewById(R.id.mode);
        tv.setText("D standard");

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(D.this, A.class));
            }
        });
    }

}
