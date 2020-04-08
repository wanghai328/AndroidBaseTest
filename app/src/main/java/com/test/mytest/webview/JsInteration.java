package com.test.mytest.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JsInteration {
     @JavascriptInterface
    public String back() {
         Log.d("123","========h5吊起了back()========");
        return "hello world";
    }
}
