package com.test.mytest.utils.network;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public abstract class ResultCallBack {
    public abstract void onError(Request request,Exception e);
    public abstract void onSuccess(Response response) throws IOException;
}
