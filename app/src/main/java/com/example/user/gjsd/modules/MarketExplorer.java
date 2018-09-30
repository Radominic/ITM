package com.example.user.gjsd.modules;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.user.gjsd.model.Market;
import com.example.user.gjsd.view.MapFragment;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.daum.mf.map.api.MapPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@SuppressWarnings("serial")
public class MarketExplorer {
    private Map<String, Market> markets;
    private ArrayList<String> markets_sort_by_distance;
    private ArrayList<String> markets_sort_by_price;
    AddressConverter addressConverter;
    MapFragment mapFragment;

    private static MarketExplorer marketExplorer;
    public MarketExplorer(MapFragment mapFragment) {
        markets = Collections.synchronizedMap(new HashMap<String, Market>());
        markets_sort_by_distance = new ArrayList<String>();
        markets_sort_by_price = new ArrayList<String>();
        this.mapFragment = mapFragment;
        //데이터 삽입
        markets.put("종로구 통인시장", new Market(MapPoint.mapPointWithGeoCoord(37.5807801, 126.9699523), false));
        markets.put("용산구 용문시장", new Market(MapPoint.mapPointWithGeoCoord(37.5366204, 126.9597976), false));
        markets.put("중구 남대문시장", new Market(MapPoint.mapPointWithGeoCoord(37.5592111, 126.977639), false));
        markets.put("성북구 돈암제일시장", new Market(MapPoint.mapPointWithGeoCoord(37.5916795, 127.0157178), false));
        markets.put("성북구 장위골목시장", new Market(MapPoint.mapPointWithGeoCoord(37.6120979, 127.0508262), false));
        markets.put("영등포구 대림중앙시장", new Market(MapPoint.mapPointWithGeoCoord(37.4916125, 126.899949), false));
        markets.put("영등포구 영등포전통시장", new Market(MapPoint.mapPointWithGeoCoord(37.5197463, 126.9074287), false));
        markets.put("도봉구 방학동도깨비시장", new Market(MapPoint.mapPointWithGeoCoord(37.6657435, 127.0329465), false));
        markets.put("도봉구 신창시장", new Market(MapPoint.mapPointWithGeoCoord(37.63458869999999, 127.01483140000005), false));
        markets.put("서대문구 인왕시장", new Market(MapPoint.mapPointWithGeoCoord(37.5908692, 126.943696), false));
        markets.put("서대문구 영천시장", new Market(MapPoint.mapPointWithGeoCoord(37.5703324, 126.9618708), false));
        markets.put("강서구 송화시장", new Market(MapPoint.mapPointWithGeoCoord(37.5492288, 126.8345762), false));
        markets.put("은평구 대림시장", new Market(MapPoint.mapPointWithGeoCoord(37.5868145, 126.91771399999993), false));
        markets.put("종로구 광장시장", new Market(MapPoint.mapPointWithGeoCoord(37.5701227, 126.9997095), false));
        markets.put("용산구 후암시장", new Market(MapPoint.mapPointWithGeoCoord(37.5501259, 126.9764509), false));
        markets.put("강북구 수유재래시장", new Market(MapPoint.mapPointWithGeoCoord(37.6315365, 127.0223447), false));
        markets.put("성동구 금남시장", new Market(MapPoint.mapPointWithGeoCoord(37.5484673, 127.0227235), false));
        markets.put("성동구 뚝도시장", new Market(MapPoint.mapPointWithGeoCoord(37.5378062, 127.0548442), false));
        markets.put("광진구 자양골목시장", new Market(MapPoint.mapPointWithGeoCoord(37.5338035, 127.0816089), false));
        markets.put("동대문구 청량리종합시장", new Market(MapPoint.mapPointWithGeoCoord(37.5808292, 127.0433944), false));
        markets.put("동대문구 경동시장", new Market(MapPoint.mapPointWithGeoCoord(37.5787997, 127.0396819), false));
        markets.put("중랑구 우림시장", new Market(MapPoint.mapPointWithGeoCoord(37.5967745, 127.0982882), false));
        markets.put("중랑구 동원시장", new Market(MapPoint.mapPointWithGeoCoord(37.5898431, 127.0899073), false));
        markets.put("노원구 공릉동 도깨비시장", new Market(MapPoint.mapPointWithGeoCoord(37.6226999, 127.0762606), false));
        markets.put("양천구 목3동시장", new Market(MapPoint.mapPointWithGeoCoord(37.5482793, 126.8668424), false));
        markets.put("양천구 신영시장", new Market(MapPoint.mapPointWithGeoCoord(37.5330235, 126.8360192), false));
        markets.put("구로구 남구로시장", new Market(MapPoint.mapPointWithGeoCoord(37.4897303, 126.8864836), false));
        markets.put("구로구 고척근린시장", new Market(MapPoint.mapPointWithGeoCoord(37.5024083, 126.8505529), false));
        markets.put("금천구 현대시장", new Market(MapPoint.mapPointWithGeoCoord(37.4597989, 126.90433069999995), false));
        markets.put("금천구 남문시장", new Market(MapPoint.mapPointWithGeoCoord(37.4737845, 126.9004226), false));
        markets.put("마포구 망원시장", new Market(MapPoint.mapPointWithGeoCoord(37.5562398, 126.9056321), false));
        markets.put("마포구 마포농수산물시장", new Market(MapPoint.mapPointWithGeoCoord(37.5651843, 126.8984946), false));
        markets.put("동작구 남성시장", new Market(MapPoint.mapPointWithGeoCoord(37.4894036, 126.9807712), false));
        markets.put("관악구 원당종합시장", new Market(MapPoint.mapPointWithGeoCoord(37.4748148, 126.96562949999998), false));
        markets.put("관악구 신원시장", new Market(MapPoint.mapPointWithGeoCoord(37.4835132, 126.9258997), false));
        markets.put("송파구 마천중앙시장", new Market(MapPoint.mapPointWithGeoCoord(37.4980997, 127.1507085), false));
        markets.put("송파구 방이시장", new Market(MapPoint.mapPointWithGeoCoord(37.5103559, 127.1175756), false));
        markets.put("강동구 암사종합시장", new Market(MapPoint.mapPointWithGeoCoord(37.5508693, 127.1288365), false));
        markets.put("중구 서울중앙시장", new Market(MapPoint.mapPointWithGeoCoord(37.5667939, 127.0198036), false));
        markets.put("강동구 둔촌역전통시장", new Market(MapPoint.mapPointWithGeoCoord(37.5275029, 127.1351706), false));
        markets.put("관악구 관악신사시장", new Market(MapPoint.mapPointWithGeoCoord(37.4868119, 126.9170742), false));
        markets.put("노원구 상계중앙시장", new Market(MapPoint.mapPointWithGeoCoord(37.659884, 127.0698784), false));
        markets.put("광진구 노룬산골목시장", new Market(MapPoint.mapPointWithGeoCoord(37.5364069, 127.0646822), false));
        markets.put("강서구 화곡본동시장", new Market(MapPoint.mapPointWithGeoCoord(37.5428618, 126.8441381), false));
        markets.put("강남구 청담삼익시장", new Market(MapPoint.mapPointWithGeoCoord(37.5224855, 127.0576847), false));
        markets.put("강남구 도곡시장", new Market(MapPoint.mapPointWithGeoCoord(37.4954841, 127.0333574), false));
        markets.put("서초구 방림시장", new Market(MapPoint.mapPointWithGeoCoord(37.486042, 126.99051080000004), false));
        markets.put("은평구 대조시장", new Market(MapPoint.mapPointWithGeoCoord(37.6098316, 126.9276087), false));
        markets.put("강북구 숭인시장", new Market(MapPoint.mapPointWithGeoCoord(37.6131571, 127.0294686), false));
        markets.put("중구 신세계백화점", new Market(MapPoint.mapPointWithGeoCoord(37.5609164, 126.9809698), true));
        markets.put("용산구 이마트 용산점", new Market(MapPoint.mapPointWithGeoCoord(37.5298702, 126.9648759), true));
        markets.put("중구 롯데마트 서울역점", new Market(MapPoint.mapPointWithGeoCoord(37.5574467, 126.9695134), true));
        markets.put("성북구 이마트 미아점", new Market(MapPoint.mapPointWithGeoCoord(37.6108546, 127.0299225), true));
        markets.put("성북구 현대백화점 미아점", new Market(MapPoint.mapPointWithGeoCoord(37.6084082, 127.0287466), true));
        markets.put("영등포구 홈플러스 영등포점", new Market(MapPoint.mapPointWithGeoCoord(37.5182193, 126.8958098), true));
        markets.put("영등포구 이마트 여의도점", new Market(MapPoint.mapPointWithGeoCoord(37.518272, 126.9260954), true));
        markets.put("도봉구 이마트 창동점", new Market(MapPoint.mapPointWithGeoCoord(37.6516448, 127.0467326), true));
        markets.put("도봉구 홈플러스 방학점", new Market(MapPoint.mapPointWithGeoCoord(37.6648915, 127.043577), true));
        markets.put("서대문구 현대백화점 신촌점", new Market(MapPoint.mapPointWithGeoCoord(37.5560866, 126.9358496), true));
        markets.put("강서구 홈플러스 등촌점", new Market(MapPoint.mapPointWithGeoCoord(37.5601997, 126.84633), true));
        markets.put("강서구 이마트 가양점", new Market(MapPoint.mapPointWithGeoCoord(37.5582132, 126.861794), true));
        markets.put("강남구 이마트 역삼점", new Market(MapPoint.mapPointWithGeoCoord(37.4992649, 127.0484171), true));
        markets.put("강남구 롯데백화점 강남점", new Market(MapPoint.mapPointWithGeoCoord(37.497041, 127.0532153), true));
        markets.put("은평구 2001아울렛 불광점", new Market(MapPoint.mapPointWithGeoCoord(37.60974, 126.9289681), true));
        markets.put("은평구 이마트 은평점", new Market(MapPoint.mapPointWithGeoCoord(37.6008414, 126.9205342), true));
        markets.put("중구 롯데백화점", new Market(MapPoint.mapPointWithGeoCoord(37.5649053, 126.98121879999997), true));
        markets.put("중구 이마트 청계점", new Market(MapPoint.mapPointWithGeoCoord(37.5709405, 127.0210984), true));
        markets.put("용산구 농협 하나로마트 용산점", new Market(MapPoint.mapPointWithGeoCoord(37.5331778, 126.9647535), true));
        markets.put("강북구 롯데백화점 미아점", new Market(MapPoint.mapPointWithGeoCoord(37.6145947, 127.0305364), true));
        markets.put("성동구 이마트 왕십리점", new Market(MapPoint.mapPointWithGeoCoord(37.5615131, 127.0384279), true));
        markets.put("성동구 이마트 성수점", new Market(MapPoint.mapPointWithGeoCoord(37.5399353, 127.0535218), true));
        markets.put("광진구 이마트 자양점", new Market(MapPoint.mapPointWithGeoCoord(37.5385628, 127.0730552), true));
        markets.put("광진구 롯데마트 강변점", new Market(MapPoint.mapPointWithGeoCoord(37.5349755, 127.0957226), true));
        markets.put("동대문구 홈플러스 동대문점", new Market(MapPoint.mapPointWithGeoCoord(37.5745613, 127.038873), true));
        markets.put("동대문구 롯데백화점 청량리점", new Market(MapPoint.mapPointWithGeoCoord(37.581387, 127.048979), true));
        markets.put("중랑구 이마트 상봉점", new Market(MapPoint.mapPointWithGeoCoord(37.5964905, 127.093618), true));
        markets.put("중랑구 홈플러스 면목점", new Market(MapPoint.mapPointWithGeoCoord(37.5792089, 127.0812071), true));
        markets.put("노원구 롯데백화점 노원점", new Market(MapPoint.mapPointWithGeoCoord(37.6550434, 127.0610971), true));
        markets.put("노원구 홈플러스 중계점", new Market(MapPoint.mapPointWithGeoCoord(37.6399096, 127.0686298), true));
        markets.put("구로구 이마트 신도림점", new Market(MapPoint.mapPointWithGeoCoord(37.5070488, 126.890246), true));
        markets.put("구로구 애경백화점", new Market(MapPoint.mapPointWithGeoCoord(37.2656796, 127.0002404), true));
        markets.put("금천구 홈플러스 시흥점", new Market(MapPoint.mapPointWithGeoCoord(37.4518936, 126.9007885), true));
        markets.put("마포구 그랜드마트 신촌점", new Market(MapPoint.mapPointWithGeoCoord(37.5550041, 126.9359849), true));
        markets.put("마포구 홈플러스 월드컵점", new Market(MapPoint.mapPointWithGeoCoord(37.5699509, 126.899029), true));
        markets.put("동작구 태평백화점", new Market(MapPoint.mapPointWithGeoCoord(37.4869128, 126.9816608), true));
        markets.put("영등포구 롯데백화점 영등포점", new Market(MapPoint.mapPointWithGeoCoord(37.5156838, 126.9076607), true));
        markets.put("관악구 롯데백화점 관악점", new Market(MapPoint.mapPointWithGeoCoord(37.4904913, 126.9249815), true));
        markets.put("관악구 세이브 마트", new Market(MapPoint.mapPointWithGeoCoord(37.474277, 126.91764330000001), true));
        markets.put("서초구 하나로클럽 양재점", new Market(MapPoint.mapPointWithGeoCoord(37.4634962, 127.0435314), true));
        markets.put("송파구 롯데백화점 잠실점", new Market(MapPoint.mapPointWithGeoCoord(37.5110794, 127.0981638), true));
        markets.put("송파구 홈플러스 잠실점", new Market(MapPoint.mapPointWithGeoCoord(37.5162594, 127.1030345), true));
        markets.put("강동구 이마트 명일점", new Market(MapPoint.mapPointWithGeoCoord(37.5547054, 127.1556281), true));
        markets.put("강동구 홈플러스 강동점", new Market(MapPoint.mapPointWithGeoCoord(37.5456952, 127.142258), true));
        markets.put("서초구 뉴코아아울렛 강남점", new Market(MapPoint.mapPointWithGeoCoord(37.5092377, 127.0074886), true));
        markets.put("강북구 하나로클럽 미아점", new Market(MapPoint.mapPointWithGeoCoord(37.6215636, 127.0265155), true));
        markets.put("양천구 이마트 목동점", new Market(MapPoint.mapPointWithGeoCoord(37.52609, 126.871084), true));
        markets.put("서초구 신세계백화점 강남점", new Market(MapPoint.mapPointWithGeoCoord(37.5049178, 127.0032131), true));
        markets.put("서대문구 롯데슈퍼", new Market(MapPoint.mapPointWithGeoCoord(37.585199, 126.94744400000002), true));
        markets.put("금천구 홈플러스 독산점", new Market(MapPoint.mapPointWithGeoCoord(37.4686645, 126.8968827), true));
        markets.put("양천구 홈플러스 목동점", new Market(MapPoint.mapPointWithGeoCoord(37.5302268, 126.8733084), true));

        addressConverter = new AddressConverter(this);

        //setNameToEachMarket
        Set<String> keys = markets.keySet();
        for (String key : keys) {
            markets.get(key).setName(key);
            getItemInfo(key);
            addressConverter.getAddress(markets.get(key));
        }

        //default sort
        markets_sort_by_distance = new ArrayList<String>(markets.keySet());
        markets_sort_by_price = new ArrayList<String>(markets.keySet());

        marketExplorer = this;
    }


    public void getItemInfo(final String marketname){
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.URL).build();
        service = retrofit.create(APIService.class);
        Call<ResponseBody> call = service.get_all_goods(marketname.replaceAll(" ", ""));
        Log.d("debug_getItemInfoOfMarket", marketname.replaceAll(" ", ""));
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray post = new JSONArray(response.body().string());
                    markets.get(marketname).setUpdateDate(post.getJSONObject(0).getString("time").toString());
                    for(int i = 0;i<post.length();i++){
                        String tmp = post.getJSONObject(i).getString("goods").toString();
                        Log.d("getItemInfo",tmp);
                        String itemname = tmp.split("\\(")[0];
                        Log.d("getItemInfo_parced",itemname+">"+marketname);
                        if(itemname.equals("냉동참조기"))continue;
                        if(itemname.equals("호박"))continue;
                        String price=post.getJSONObject(i).getString("cost").toString();
                        String difference = post.getJSONObject(i).getString("difference").toString();
                        markets.get(marketname).putItem(itemname,price,difference);
                        Log.d("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@",itemname+">"+marketname);
                    }

                    Log.v("getItemInfo_success", "_______________________");
                } catch (Exception e) {
                    Log.v("getItemInfo_error1", marketname+":"+e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("getItemInfo_error2","true");
            }
        });
    }
















    //getter and setter
    public MapPoint getMarketMapPoint(String name) {
        return markets.get(name).getMapPoint();
    }

    public Market getMarket(String name) {
        return markets.get(name);

    }

    public ArrayList<String> getMarkets_sort_by_distance() {
        return markets_sort_by_distance;
    }

    public ArrayList<String> getMarkets_sort_by_price(){return markets_sort_by_price;}




//    public


    @RequiresApi(api = Build.VERSION_CODES.N)
    public MapPoint[] getNearNMarketsMapPoint(int numOfMarket) {
//        updateMarketDistance(p);
        MapPoint[] result = new MapPoint[numOfMarket];
        int index = 0;
        for (String name : getNearNMarketName(numOfMarket)) {
            result[index] = markets.get(name).getMapPoint();
            index++;
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String[] getNearNMarketName(int numOfMarket) {

        String[] result = new String[numOfMarket];
        for (int i = 0; i < numOfMarket; i++) {
            result[i] = markets_sort_by_distance.get(i);
            Log.d("debug_near", markets_sort_by_distance.get(i));
        }
        return result;
    }

    //calculate distance and sort
    MarketDistanceComparator mc = new MarketDistanceComparator();


    @TargetApi(Build.VERSION_CODES.N)
    public void updateMarketDistance(MapPoint p) {
        for (String name : markets_sort_by_distance) {
            double latitude = markets.get(name).getMapPoint().getMapPointGeoCoord().latitude;
            double longitude = markets.get(name).getMapPoint().getMapPointGeoCoord().longitude;
            double stdlatitude = p.getMapPointGeoCoord().latitude;
            double stdlongitude = p.getMapPointGeoCoord().longitude;
            double d = calDistance(latitude, longitude, stdlatitude, stdlongitude);
            markets.get(name).setDistance(d);
        }
        markets_sort_by_distance.sort(mc);
    }

    private double calDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta, dist;
        theta = lon1 - lon2;
        dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;    // 단위 mile 에서 km 변환.
        dist = dist * 1000.0;      // 단위  km 에서 m 로 변환
        return dist;
    }

    private double deg2rad(double deg) {
        return (double) (deg * Math.PI / (double) 180d);
    }

    private double rad2deg(double rad) {
        return (double) (rad * (double) 180d / Math.PI);
    }

    private class MarketDistanceComparator implements Comparator<String> {
        @Override
        public int compare(String s, String t1) {
            Double d1 = markets.get(s).getDistance();
            Double d2 = markets.get(t1).getDistance();
            return d1.compareTo(d2);
        }
    }


    Retrofit retrofit;
    APIService service;

    public APIService getService() {
        return service;
    }

    private String parsePrice(String s) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            String price = jsonArray.getJSONObject(0).getString("cost");
            return price;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "품목없음";
    }

    public void updateMarkets_sort_by_price(String selectedItem) {
        Log.d("update_sort_by_price","------------");
        retrofit = new Retrofit.Builder()
                .baseUrl(APIService.URL).build();
        service = retrofit.create(APIService.class);
        Call<ResponseBody> call = service.get_ascending_sort(selectedItem);
        Log.d("debug_getPriceOfMarket", selectedItem);
        call.enqueue(new Callback<ResponseBody>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONArray post = new JSONArray(response.body().string());

                    breakOut:
                    for(int i = 0; i<post.getJSONArray(0).length();i++){
                        String name = post.getJSONArray(0).getJSONObject(i).getString("mart_name").toString();
                        boolean tmp;
                        for(int j = i;j<post.getJSONArray(0).length();j++){
                            tmp = false;
                            if(markets_sort_by_price.get(j).replaceAll(" ","").equals(name)){
                                Collections.swap(markets_sort_by_price,j,i);
                                tmp = true;
                                break;
                            }
                        }
                        if(tmp = false) Log.v("no_match", name);


//                        = markets_sort_by_price.indexOf(name.));

                        Log.v("sorted_market_name", name+"/"+markets_sort_by_price.get(i));
                    }
                    mapFragment.updateCostFragment();
                } catch (Exception e) {
                    Log.v("debug_error", "getMarketsSortByPrice_error"+e.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                markets.get(name).setPrice(null);
                Log.v("debug_error", "getMarketsSortByPrice_error:server_no_response");
            }
        });

    }

    //응답 string을 arraylist<String>으로 변환
    private ArrayList<String> parsePriceList(String s) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            ArrayList<String> tmp = new ArrayList<String>();
            for(int i = 0; i<jsonArray.length();i++){
                tmp.add(jsonArray.getJSONObject(i).getString("mart_name"));
            }
            return tmp;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static MarketExplorer getInstance(){
        return marketExplorer;
    }

}
