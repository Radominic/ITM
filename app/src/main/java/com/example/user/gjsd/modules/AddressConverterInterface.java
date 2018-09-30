package com.example.user.gjsd.modules;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddressConverterInterface {
    static final String URL = "https://dapi.kakao.com/";

//    @Headers("Authorization : KakaoAK{2f5c1c62fbe6275d6f1d732dc0210f6d}")
    @GET("v2/local/search/keyword.json")
    Call<ResponseBody> getDetailAddress(
            @Header("Authorization") String key,
            @Query("query") String query
//            @Field("latitude") String goods,
//            @Field("longitude") String market_name
    );

}
