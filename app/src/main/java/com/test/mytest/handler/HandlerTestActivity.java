package com.test.mytest.handler;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.test.mytest.R;
import com.test.mytest.data.Book;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class HandlerTestActivity extends Activity implements View.OnClickListener {
    Book book1, book2;
    Looper mainLooper;
    Thread thread;
    Handler handler;
    Message message, message0,message1, message2, message3, message4;

    private MessageQueue.IdleHandler idleHandler;
    private static final String TAG = "123";
    private int token; // 同步屏障对应的token值

     TextView tv;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"-------onCreate--------");
        setContentView(R.layout.quote_test);

        tv = findViewById(R.id.txv);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);

        SoftReference<Book> studentSoftReference = new SoftReference<>(new Book());
        book1 = studentSoftReference.get();

        WeakReference<Book> weakReference = new WeakReference<>(new Book());
        book2 = weakReference.get();
        mainLooper = getMainLooper();

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler(mainLooper) {
                    @Override
                    public void handleMessage(Message msg) {
                        Log.d("123", "----------handleMessage--------");
                    }
                };
                handler.sendEmptyMessage(0);
                Looper.loop();
            }
        });
        thread.start();


        message0 = Message.obtain(handler, new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "========message0=======");
            }
        });
        message1 = Message.obtain(handler, new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "========message1=======");
            }
        });
        message2 = Message.obtain(handler, new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                Log.d(TAG, "========message2=======");
                try {
                    removeSyncBarrier(); // 移除同步屏障
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        message3 = Message.obtain(handler, new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                Log.d(TAG, "========message3  异步=======");
                try {
                    removeSyncBarrier(); // 移除同步屏障
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        message4 = Message.obtain(handler, new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                Log.d(TAG, "========message4  异步=======");
                try {
                    removeSyncBarrier(); // 移除同步屏障
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





        idleHandler = new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                Log.e("123","======queueIdle=======");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getMainLooper().getQueue().addIdleHandler(idleHandler);
        }
    }

    // 反射执行投递同步屏障，省略try..catch
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void postSyncBarrier() throws Exception {
        Log.d("123","-------同步屏障-------");
        Method method = MessageQueue.class.getDeclaredMethod("postSyncBarrier");
        token = (int) method.invoke(Looper.getMainLooper().getQueue());
    }

    // 反射执行移除同步屏障，省略try..catch
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void removeSyncBarrier() throws Exception {
        Method method = MessageQueue.class.getDeclaredMethod("removeSyncBarrier", int.class);
        method.invoke(Looper.getMainLooper().getQueue(), token);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"-----onStart------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"-----onResume------");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
               tv.setText("22222222");
                break;
            case R.id.btn4:
//                 handler.sendEmptyMessage(0);
                Log.d("123", "---onClick---");
                message = Message.obtain(handler);
                message.sendToTarget();
                break;
            case R.id.btn2:
                WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1]);
                System.out.println(weakReference.get());
                System.gc();
                System.out.println(weakReference.get());
                break;
            case R.id.btn3:
                ReferenceQueue queue = new ReferenceQueue();
                PhantomReference<byte[]> reference = new PhantomReference<byte[]>(new byte[1], queue);

                break;
            case R.id.btn5:

                // 设置3秒后和4秒后执行的消息为异步消息
                message3.setAsynchronous(true);
                message4.setAsynchronous(true);

                handler.sendMessage(message0);
//                handler.sendMessage(message2);
                handler.sendMessageDelayed(message1, 1000); // 发送1秒后执行的同步消息
                handler.sendMessageDelayed(message2, 2000); // 发送2秒后执行的同步消息
                handler.sendMessageDelayed(message3, 3000); // 发送3秒后执行的异步消息
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        postSyncBarrier(); // 投递同步屏障到消息队列中
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                handler.sendMessageDelayed(message4, 4000); // 发送4秒后执行的异步消息

                break;
        }
    }
}
