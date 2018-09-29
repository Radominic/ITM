package com.example.user.gjsd.model;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

public class Market {
//    public MapPOIItem poiItem;
    private String name;//fix at constructor
    private double lat;//fix at constructor
    private double lng;//fix at constructor
    private MapPoint mapPoint;//fix at constructor
    private boolean isMart;//fix at constructor

    //variable
    private String price = "품목없음";
    private Double distance = 0.0;

//    public MapPOIItem getPoiItem() {
//        return poiItem;
//    }

    public Market(MapPoint mapPoint ,boolean isMart){
        this.mapPoint = mapPoint;
        this.isMart = isMart;


    }
//    public void setPoiItem(MapPOIItem poiItem){
//        this.poiItem = poiItem;
//    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getPrice(){
        return price;
    }

    public String getName() {
        return name;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public MapPoint getMapPoint() {
        return mapPoint;
    }

    public boolean isMart() {
        return isMart;
    }

    public double getDistance() {
        return distance;
    }


    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
