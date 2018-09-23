package com.example.user.gjsd.modules;

import net.daum.mf.map.api.MapPoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//enum GU {노원구, 도봉구};

public class GuManager {
    private Map<String, Point> Gus;


    public GuManager(){
        Gus = Collections.synchronizedMap(new HashMap<String,Point>());

        //데이터 삽입
        Gus.put("도봉구", new Point(37.6687738,127.04707059999998));
        Gus.put("강북구", new Point(37.6396099,127.02565749999997));
        Gus.put("노원구", new Point(37.6541917,127.05679299999997));
        Gus.put("은평구", new Point(37.6026957,126.92911189999995));
        Gus.put("성북구", new Point(37.589116,127.01821459999996));
        Gus.put("종로구", new Point(37.5729503,126.97935789999997));
        Gus.put("동대문구", new Point(37.57436819999999,127.04001889999995));
        Gus.put("중랑구", new Point(37.6065602,127.09265189999996));
        Gus.put("서대문구", new Point(37.5791158,126.93677890000004));
        Gus.put("중구", new Point(37.5640907,126.99794029999998));
        Gus.put("성동구", new Point(37.5633415,127.03710249999995));
        Gus.put("광진구", new Point(37.5384843,127.0822938));
        Gus.put("용산구", new Point(37.5384272,126.96544419999998));
        Gus.put("마포구", new Point(37.5637561,126.90842109999994));
        Gus.put("강서구", new Point(37.5509786,126.84953819999998));
        Gus.put("양천구", new Point(37.5168721,126.86639850000006));
        Gus.put("구로구", new Point(37.4954031,126.88736900000004));
        Gus.put("영등포구", new Point(37.5263715,126.89622830000008));
        Gus.put("금천구", new Point(37.4518527,126.90203580000002));
        Gus.put("관악구", new Point(37.4784063,126.95161329999996));
        Gus.put("동작구", new Point(37.512402,126.93925250000007));
        Gus.put("서초구", new Point(37.4837121,127.03241120000007));
        Gus.put("강남구", new Point(37.5172363,127.04732480000007));
        Gus.put("송파구", new Point(37.5145437,127.10659710000004));
        Gus.put("강동구", new Point(37.5301251,127.12376199999994));
    }

    public String searchMarket(String key){
        //검색 쿼리 구현
        return null;
    }

    public Point getGuPoint(String guName){
        return Gus.get(guName);
    }

    public MapPoint getGuMapPoint(String guName){ return (Gus.get(guName).getMapPoint());}
//    public double getLatitude(String guName){
//        return Gus.get(guName).getLatitude();
//    }
//
//    public double getLongitude(String guName){
//        return Gus.get(guName).getLongitude();
//    }
//
//    class Point{
//        double lat;
//        double lng;
//
//        public Point(double lat, double lng){
//            this.lat = lat;
//            this.lng = lng;
//        }
//
//        public double getLatitude(){
//            return lat;
//        }
//        public double getLongitude(){
//            return lng;
//        }
//    }
}
