package com.example.user.gjsd.modules;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.user.gjsd.model.Market;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddressConverter {
    MarketExplorer marketExplorer;
    Retrofit retrofit;
    AddressConverterInterface service;
    public AddressConverter(MarketExplorer marketExplorer) {
        this.marketExplorer = marketExplorer;
    }

    public void getAddress(final Market market){
        retrofit = new Retrofit.Builder()
                .baseUrl(AddressConverterInterface.URL).build();
        service = retrofit.create(AddressConverterInterface.class);

        Call<ResponseBody> call = service.getDetailAddress("KakaoAK2f5c1c62fbe6275d6f1d732dc0210f6d",market.getName().replaceAll(" ",""));
//        Log.d("debug_getPriceOfMarket", selectedItem);
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("detail_address_resonse",response.body().string());
                    JSONObject post = new JSONObject(response.body().string());

                    String detailAddress = post.getJSONArray("documents").getJSONObject(0).getString("address_name").toString();
                    market.setDetailAddress(detailAddress);
                    Log.v("detail_address_set_success", market.getName()+":"+detailAddress);
//                    breakOut:
//                    for(int i = 0; i<post.getJSONArray(0).length();i++){
//                        String name = post.getJSONArray(0).getJSONObject(i).getString("mart_name").toString();
//                        boolean tmp;
//                        for(int j = i;j<post.getJSONArray(0).length();j++){
//                            tmp = false;
//                            if(markets_sort_by_price.get(j).replaceAll(" ","").equals(name)){
//                                Collections.swap(markets_sort_by_price,j,i);
//                                tmp = true;
//                                break;
//                            }
//                        }
//                        if(tmp = false) Log.v("no_match", name);
//
//
////                        = markets_sort_by_price.indexOf(name.));
//
//                        Log.v("sorted_market_name", name);
//                    }
//                    mapFragment.updateCostFragment();
                } catch (Exception e) {
                    Log.v("detail_address_set_error", "error at "+market.getName()+e.toString());

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                markets.get(name).setPrice(null);
                Log.v("debug_error", "getMarketsSortByPrice_error:server_no_response");
            }
        });
    }

}
