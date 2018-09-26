package com.example.user.gjsd.modules;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;

public class MapView_ extends MapView{


    public MapView_(Activity activity) {
        super(activity);
    }
    public MapView_(Context context){
        super(context);
    }

//    @Override
    public void deselectPOIItem(MapPOIItem poiItem){
        Log.d("over","executed");
    }
//    @Override
    public void selectPOIItem(MapPOIItem poiItem, boolean animated){
        Log.d("over","hello");
    }
}
