package com.test.mytest.leak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import com.squareup.leakcanary.RefWatcher;
import com.test.mytest.BaseApplication;
import com.test.mytest.R;

public class LeakTestActivity extends Activity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leak_test);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000);

          LeakThread leakThread = new LeakThread();
        leakThread.start();
    }


    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getRefWatcher(this);//1
        refWatcher.watch(this);
    }
}
