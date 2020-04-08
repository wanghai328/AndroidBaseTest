package com.test.mytest;

public class MyPresenter {
    QueryView view;
    MyModel myModel;

    MyPresenter(QueryView view){
        this.view = view;
        myModel = new MyModel();
    }

    public void queryNum(String num){
        
    }
}
