package com.test.mytest.glide;

import android.app.Activity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.mytest.R;

public class GlideTestActivity extends Activity implements View.OnClickListener {
    ImageView imgView;

    String imgUrl = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    String gifUrl = "http://p1.pstatp.com/large/166200019850062839d3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        imgView = findViewById(R.id.img);
        findViewById(R.id.btn_img).setOnClickListener(this);
        findViewById(R.id.btn_gif).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_img:
                Glide.with(GlideTestActivity.this)
                        .load(imgUrl)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imgView);
                break;
            case R.id.btn_gif:
                Glide.with(GlideTestActivity.this)
                        .load(gifUrl)
//                .placeholder(R.drawable.loading)
//                .error(R.drawable.error)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imgView);
                break;
        }
    }
}
