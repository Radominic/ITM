package com.example.user.gjsd.modules;

import android.util.Log;

public class MyClient {
    private final String serverURL = "http://cafe24.com/";
    private String urlString;
    public void MyClient(){

    }
    public String getPriceOfMarket(String market,String itemname){
        Log.d("send",market+","+itemname);
        return "999999";
    }
}
