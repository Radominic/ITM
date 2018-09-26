package com.example.user.gjsd.modules;

import com.google.gson.JsonObject;

import java.util.ArrayList;

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
    @POST("cheapest_mart.php")
    Call<String> get_cheapest_mart(
            @Field("goods") String goods
    );



}
