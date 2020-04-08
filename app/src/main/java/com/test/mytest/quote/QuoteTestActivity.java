package com.test.mytest.quote;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.mytest.R;
import com.test.mytest.data.Book;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class QuoteTestActivity extends Activity implements View.OnClickListener {
    Book book1, book2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_test);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        SoftReference<Book> studentSoftReference = new SoftReference<>(new Book());
        book1 = studentSoftReference.get();

        WeakReference<Book> weakReference = new WeakReference<>(new Book());
        book2 = weakReference.get();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:


                break;
            case R.id.btn2:
                WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1]);
                System.out.println(weakReference.get());
                System.gc();
                System.out.println(weakReference.get());
                Log.d("123",""+weakReference.get());
                break;
            case R.id.btn3:
                ReferenceQueue queue = new ReferenceQueue();
                PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);

                break;
        }
    }
}
