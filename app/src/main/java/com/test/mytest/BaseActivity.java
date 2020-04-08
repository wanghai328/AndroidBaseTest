package com.test.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class BaseActivity extends Activity {
    String TAG = "hai";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"-----onCreate-----"+getClass());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"-----onStart-----"+getClass());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"-----onResume-----"+getClass());
        Log.d(TAG,"-----taskID-----"+getTaskId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"-----onPause-----"+getClass());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"-----onStop-----"+getClass());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"-----onRestart-----"+getClass());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"-----onDestroy-----"+getClass());
    }
     @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG,"---------onNewIntent--------"+getClass());
    }
}
