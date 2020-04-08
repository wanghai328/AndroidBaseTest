package com.test.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import rx.Observer;
import rx.Subscriber;

public class RxJavaTestActivity extends Activity {
    private String TAG = "123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Subscriber subscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "-----onCompleted----");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "-----onError----");
            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "-----onNext----");
            }

            @Override
            public void onStart() {
                Log.e(TAG, "-----onStart----");
            }
        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "-----onCompleted----");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "-----onError----");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "-----onNext----");
            }
        };
    }
}
