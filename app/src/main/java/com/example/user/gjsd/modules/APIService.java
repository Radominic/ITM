package com.example.user.gjsd.modules;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
//    @GET("users/{user}/repos")
//    Call<ArrayList<JsonObject>> getListRepos(@Path("user") String id);
    static final String URL = "http://jine2669.cafe24.com/itm/alpha/";


    @FormUrlEncoded
    @POST("name_goods.php")
    Call<ResponseBody> get_name_goods(
            @Field("goods") String goods,
            @Field("market_name") String market_name
    );

    @FormUrlEncoded
    @POST("ascending_sort.php")
    Call<ResponseBody> get_ascending_sort(
            @Field("goods") String goods
    );

    @FormUrlEncoded
    @POST("all_goods.php")
    Call<ResponseBody> get_all_goods(
            @Field("market_name") String market_name
    );

}
