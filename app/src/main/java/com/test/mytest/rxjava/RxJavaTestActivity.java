package com.test.mytest.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.test.mytest.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


//  Publisher
//  Subscriber
//  Subscription
//  Processor

/**
 * RxJava测试activity
 * create
 */
public class RxJavaTestActivity extends Activity implements View.OnClickListener {
    private String TAG = "123";

    private TextView tv;
    private StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rxjava);
        tv = findViewById(R.id.tv);

        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_map).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                creatTest();
                break;
            case R.id.btn_map:
                mapTest();
                break;
        }
    }

    private void mapTest() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                sb.append("accept " + s + "\n");
                Log.e(TAG, "accept: " + s);
            }
        });
    }

    private void creatTest() {
        //create
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                sb.append("Observable emit 1" + "\n");
                Log.i(TAG, "Observable emit 1");
                e.onNext(1);
                sb.append("Observable emit 2" + "\n");
                Log.i(TAG, "Observable emit 2");
                e.onNext(2);
                sb.append("Observable emit 3" + "\n");
                Log.i(TAG, "Observable emit 3");
                e.onNext(3);
                e.onComplete();
                sb.append("Observable emit 4" + "\n");
                Log.i(TAG, "Observable emit 4");
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                sb.append("onSubscribe: " + d.isDisposed() + "\n");
                Log.i(TAG, "onSubscribe: " + d.isDisposed());
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "OnNext :value: " + mDisposable.isDisposed());
                Log.e(TAG, "i: " + i);
                sb.append("OnNext :value: " + integer + "\n");
                i++;
                if (i == 1) {
                    mDisposable.dispose();
                    sb.append("OnNext :value: " + mDisposable.isDisposed() + "\n");
                }

            }

            @Override
            public void onError(Throwable e) {
                sb.append("onError : value : " + e.getMessage() + "\n");
                Log.e(TAG, "onError : value : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                sb.append("onComplete" + "\n");
                Log.d(TAG, "onComplete");

                tv.setText(sb.toString());
            }
        });
    }
}
