package com.example.user.gjsd.modules;

import android.telecom.Call;
import android.util.Log;

import com.example.user.gjsd.MapFragment;

import net.daum.mf.map.api.MapPOIItem;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.user.gjsd.modules.APIService.URL;

public class MyClient {
    private MapFragment mapFragment;

    Retrofit retrofit;
    APIService service;


    public MyClient(MapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }


    public APIService getService() {
        return service;
    }



    public void getPriceOfMarket(final MapPOIItem poiItem) {
        mapFragment.setPriceOnPOIItem(poiItem,"5000");

//        retrofit = new Retrofit.Builder()
//                .baseUrl(URL)
//                .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
//                .build();
//
//        service = retrofit.create(APIService.class);
//
//        retrofit2.Call<String> call = service.get_cheapest_mart("배");
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
//                //추후에 파싱하여 가격정보만 셋팅
//                mapFragment.setPriceOnPOIItem(poiItem,response.body().toString());
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<String> call, Throwable t) {
//                mapFragment.setPriceOnPOIItem(poiItem,"가격 정보 찾을 수 없음");
//            }
//        });
    }
}

