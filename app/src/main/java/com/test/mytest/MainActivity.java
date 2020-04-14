package com.test.mytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.Toast;

import com.test.mytest.binder.ServerActivity;
import com.test.mytest.broadcast.BroadcastActivity;
import com.test.mytest.broadcast.NetworkConnectChangedReceiver;
import com.test.mytest.glide.GlideTestActivity;
import com.test.mytest.handler.HandlerTestActivity;
import com.test.mytest.launch_mode.D;
import com.test.mytest.leak.LeakTestActivity;
import com.test.mytest.mvp.QueryView;
import com.test.mytest.okhttp.OkHttpTestActivity;
import com.test.mytest.quote.QuoteTestActivity;
import com.test.mytest.rxjava.RxJavaTestActivity;
import com.test.mytest.service.AActivity;
import com.test.mytest.service.ServiceTestActivity;
import com.test.mytest.test.ViewStubTestActivity;
import com.test.mytest.webview.WebViewTestActivity;

public class MainActivity extends AppCompatActivity implements QueryView, View.OnClickListener {

    private CardView cardView;
    private SeekBar seekBar1, seekBar2, seekBar3;

    private void init() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        NetworkConnectChangedReceiver netWorkChangeReceiver = new NetworkConnectChangedReceiver();
        //注册广播
        registerReceiver(netWorkChangeReceiver, intentFilter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("123", "最大可用内存： " + maxMemory + "kb");
        Toast.makeText(this, "最大可用内存： " + maxMemory + "Kb", Toast.LENGTH_LONG).show();
        init();

        cardView = findViewById(R.id.card_view);
        seekBar1 = findViewById(R.id.sb_1);
        seekBar2 = findViewById(R.id.sb_2);
        seekBar3 = findViewById(R.id.sb_3);


        assignViews();


        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btn10).setOnClickListener(this);
        findViewById(R.id.btn11).setOnClickListener(this);
        findViewById(R.id.btn12).setOnClickListener(this);
        findViewById(R.id.btn13).setOnClickListener(this);
        findViewById(R.id.btn14).setOnClickListener(this);
        findViewById(R.id.btn15).setOnClickListener(this);


    }


    private void assignViews() {
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cardView.setRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cardView.setCardElevation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cardView.setContentPadding(progress, progress, progress, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void showNotification() {
        Notification.Builder builder = new Notification.Builder(MainActivity.this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, mIntent, 0);

        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle("普通通知");

        Notification notification = builder.build();
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
        notification.bigContentView = remoteViews;
        int notificationId = (int) System.currentTimeMillis();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(notificationId, notification);
    }

    @Override
    public void onQueryResult(String result) {
    }

    @Override
    public void onClick(View v) {

        Log.d("123", "=====onClick====");
        switch (v.getId()) {
            case R.id.btn:
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, HorizontalTestActivity.class));

                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, RxJavaTestActivity.class));
                break;
            case R.id.btn4:
                Log.d("123", "=====btn3====");
                startActivity(new Intent(MainActivity.this, D.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(MainActivity.this, ViewStubTestActivity.class));

            case R.id.btn6:
                startActivity(new Intent(MainActivity.this, LeakTestActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(MainActivity.this, QuoteTestActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(MainActivity.this, HandlerTestActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(MainActivity.this, GlideTestActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(MainActivity.this, WebViewTestActivity.class));
                break;
            case R.id.btn11:
                startActivity(new Intent(MainActivity.this, OkHttpTestActivity.class));
                break;
            case R.id.btn12:
                startActivity(new Intent(MainActivity.this, BroadcastActivity.class));
                break;
            case R.id.btn13:
                startActivity(new Intent(MainActivity.this, ServiceTestActivity.class));
                break;
            case R.id.btn14:
                startActivity(new Intent(MainActivity.this, AActivity.class));
                break;
            case R.id.btn15:
                startActivity(new Intent(MainActivity.this, ServerActivity.class));
                break;
        }
    }
}
