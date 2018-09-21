package com.example.user.gjsd.modules;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MarketExplorer {
    private Map<String, Point> Markets;

    public MarketExplorer(){
        Markets = Collections.synchronizedMap(new HashMap<String,Point>());

        //데이터 삽입
    }

    public String searchMarket(String key){
        //검색 쿼리 구현
        return null;
    }

    public Point getMarketPoint(String marketName){
        return Markets.get(marketName);
    }

    public double getLatitude(String marketName){
        return Markets.get(marketName).getLatitude();
    }

    public double getLongitude(String marketName){
        return Markets.get(marketName).getLongitude();
    }

    class Point{
        double lat;
        double lng;

        public Point(double lat, double lng){
            this.lat = lat;
            this.lng = lng;
        }

        public double getLatitude(){
            return lat;
        }
        public double getLongitude(){
            return lng;
        }
    }
}
