package com.example.user.gjsd.modules;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.user.gjsd.MapFragment;

import net.daum.mf.map.api.MapPOIItem;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
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



    public void getPriceOfMarket(final MapPOIItem poiItem, String goods, String marketName) {
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.URL).build();
//                .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
//                .build();

        service = retrofit.create(APIService.class);

        Call<ResponseBody> call = service.get_name_goods(goods,marketName.replaceAll(" ",""));
//        s= s.replace(" ", "");
        Log.d("@@@",goods+","+marketName.replaceAll(" ",""));
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //추후에 파싱하여 가격정보만 셋팅
                try {

                    String price = parsePrice(response.body().string());
                    mapFragment.setPriceOnPOIItem(poiItem,price);
                }catch(Exception e){
                    Log.v("test","error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mapFragment.setPriceOnPOIItem(poiItem,"가격 정보 찾을 수 없음");
            }
        });
    }

    public String parsePrice(String s){
        Log.v("test",s);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            String price = jsonArray.getJSONArray(0).getJSONObject(0).getString("cost");
            return price;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("test","error");
        }

        return null;
    }

    public ArrayList<String> getMarkets_sort_by_price(){
        //구현할것
        return null;
    }
}

