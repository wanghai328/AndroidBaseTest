package com.test.mytest.utils.network;

import android.content.Context;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpEngine {
    private static OkHttpEngine mInstance;
    private OkHttpClient mOkhttpClient;
    private Handler mHandler;

    public static OkHttpEngine getInstance(Context context){
        if(mInstance == null){
            synchronized (OkHttpEngine.class){
                if(mInstance == null){
                    mInstance = new OkHttpEngine(context);
                }
            }
        }
        return mInstance;
    }
    private OkHttpEngine(Context context){
        File sdCache = context.getExternalCacheDir();
        int cacheSize = 10*1024*1024;

        OkHttpClient.Builder builder= new OkHttpClient()
                .newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .cache(new Cache(sdCache.getAbsoluteFile(),cacheSize));

        mOkhttpClient = builder.build();
        mHandler = new Handler();
    }

    public void getAsynHttp(String url,ResultCallBack callBack){
        final Request request = new Request.Builder().url(url).build();
        Call call = mOkhttpClient.newCall(request);
        dealResult(call,callBack);
    }
    private void dealResult(Call call, final ResultCallBack callBack){
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedCallBack(call.request(),e,callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sendSuccessCallBack(response,callBack);
            }
        });
    }

    private void sendFailedCallBack(final Request request, final Exception e, final ResultCallBack callBack){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    callBack.onError(request,e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final Response response, final ResultCallBack callBack){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(callBack != null){
                    try {
                        callBack.onSuccess(response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
