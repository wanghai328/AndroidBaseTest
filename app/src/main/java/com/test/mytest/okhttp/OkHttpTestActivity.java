package com.test.mytest.okhttp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.mytest.R;
import com.test.mytest.utils.network.OkHttpEngine;
import com.test.mytest.utils.network.ResultCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class OkHttpTestActivity extends Activity implements View.OnClickListener {
    String TAG = "hai";
    OkHttpClient okHttpClient;
    Request request;
    Call call;

    Button btn;
    LinearLayout ll;
    String url = "https://wwww.baidu.com";
     TextView tv ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        btn = findViewById(R.id.btn_ok);
        ll = findViewById(R.id.ll);

        btn.setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_test).setOnClickListener(this);


        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(url)
                .get()//默认就是GET请求，可以不写
                .build();
        call = okHttpClient.newCall(request);

        Log.e(TAG, "====THREAD111====: " + Thread.currentThread());
        tv = new TextView(OkHttpTestActivity.this);
                        tv.setText("新增的text");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:

                new Thread(new Runnable() {
                    @Override
                    public void run() {

//                        ll.addView(tv);
//                        btn.setText("--thread----");
                        btn.setTextColor(Color.parseColor("#ff0000"));
                        btn.setBackgroundColor(Color.parseColor("#ff00ff"));
                    }
                }).start();

                break;
            case R.id.btn_test:
                OkHttpEngine.getInstance(this).getAsynHttp(url, new ResultCallBack() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.e(TAG, "-----onError-----");
                    }

                    @Override
                    public void onSuccess(Response response) throws IOException {
                        Log.e(TAG, "-----onSuccess-----");
                        Log.e(TAG, "THREAD222: " + Thread.currentThread());
                    }
                });
                break;
            case R.id.btn_ok:

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "THREAD333: " + Thread.currentThread());
//                        Log.d(TAG, "onFailure: ");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.d(TAG, "onResponse: " + response.body().string());
                        Log.e(TAG, "THREAD: " + Thread.currentThread());

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        btn.setText("111111");
                    }
                });
                break;
        }
    }
}
