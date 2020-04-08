package com.test.mytest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.test.mytest.launch_mode.A;
import com.test.mytest.launch_mode.B;
import com.test.mytest.launch_mode.D;

public class BaseApplication extends Application {

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        refWatcher = setupLeakCanary();


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//                Log.d("123", "=====onActivityCreated======");
            }

            @Override
            public void onActivityStarted(Activity activity) {
//                Log.d("123", "=====onActivityStarted======");
            }

            @Override
            public void onActivityResumed(Activity activity) {
//                Log.d("123", "=====onActivityResumed======");
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public void startActivity() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setClass(this, D.class);
        startActivity(intent);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("123", "===========onTrimMemory============ " + level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("123", "===========onLowMemory============ ");
    }
}
