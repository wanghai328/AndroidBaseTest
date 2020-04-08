package com.test.mytest.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.test.mytest.R;

public class WebViewTestActivity extends Activity implements View.OnClickListener {
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.web_view);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);

        WebSettings webSettings = webView.getSettings();
        //让webView支持JS
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsInteration(),"android");


//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d("123", "========shouldOverrideUrlLoading======" + url);
//                view.loadUrl(url);
//                return true;
//            }
//        });

        webView.evaluateJavascript("sum(1,2)", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                Log.d("123", "========onReceiveValue======" + value);
            }
        });


        webView.loadUrl("http://www.wh_test.com/test.html");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Log.i("123","======btn1=======");
                webView.loadUrl("javascript:do()");
                break;
            case R.id.btn2:
                break;
        }
    }
}
