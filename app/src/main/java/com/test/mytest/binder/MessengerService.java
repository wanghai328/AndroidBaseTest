package com.test.mytest.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Log.e("123","--------server msg----------");
            Log.d("123","server msg: "+msg.what);
            Messenger messenger = msg.replyTo;
            try {
                messenger.send(Message.obtain(null,0));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
