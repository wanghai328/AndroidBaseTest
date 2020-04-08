package com.test.mytest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.d("123","-----第二个app-onReceive-------"+intent.getAction());
//        Log.e("123","shijian:"+ System.currentTimeMillis());
    }
}
