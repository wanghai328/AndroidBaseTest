package com.test.mytest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.test.mytest.MainActivity;


public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent activityIntent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(activityIntent);
        Log.e("123","收到了广播哦： "+intent.getStringExtra("config"));
    }
}
