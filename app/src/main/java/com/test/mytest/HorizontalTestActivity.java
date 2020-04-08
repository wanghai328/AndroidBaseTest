package com.test.mytest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class HorizontalTestActivity extends Activity {
    private ListView lv1,lv2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        lv1 = findViewById(R.id.lv_one);
        lv2 = findViewById(R.id.lv_two);

        String[] str1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>
                (this, android.R.layout.simple_expandable_list_item_1, str1);

        String[] str2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>
                (this, android.R.layout.simple_expandable_list_item_1, str2);


        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
    }
}
