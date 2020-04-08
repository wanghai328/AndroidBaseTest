package com.test.mytest.test;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import androidx.annotation.Nullable;

import com.test.mytest.R;

public class ViewStubTestActivity extends Activity {

    ViewStub viewStub;
    View viewStub1;
    boolean tag = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stub);


        viewStub = findViewById(R.id.view_stub);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag){
//                    viewStub1 = viewStub.inflate();
                    viewStub.setVisibility(View.INVISIBLE);
                    Log.d("123","----viewStub1----"+viewStub1);
                    Log.d("123","----viewStub----"+viewStub);
                }else {
//                    viewStub1.setVisibility(View.GONE);
                    viewStub.setVisibility(View.VISIBLE);
                }
                tag = !tag;
            }
        });
    }
}
