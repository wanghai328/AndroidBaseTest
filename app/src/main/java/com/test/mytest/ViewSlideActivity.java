package com.test.mytest;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class ViewSlideActivity extends Activity {
    private CustomView customView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        customView = findViewById(R.id.custom_view);

        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customView.smoothScrollTo(100, 0);


                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(customView, "translationX",200);
                objectAnimator.setDuration(3000);
                objectAnimator.start();
            }
        });
    }
}
