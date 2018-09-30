package com.example.user.gjsd.model;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Market {
    //    public MapPOIItem poiItem;
    private String name;//fix at constructor
    private double lat;//fix at constructor
    private double lng;//fix at constructor
    private MapPoint mapPoint;//fix at constructor
    private String detailAddress;
    private boolean isMart;//fix at constructor

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    private String updateDate;
    private Map<String, Item> items = Collections.synchronizedMap(new HashMap<String, Item>());
    ;


    //variable
//    private String price = "품목없음";
    private Double distance = 0.0;
    private String difference = "0";

//    public MapPOIItem getPoiItem() {
//        return poiItem;
//    }

    public Market(MapPoint mapPoint, boolean isMart) {
        this.mapPoint = mapPoint;
        this.isMart = isMart;

    }
//    public void setPoiItem(MapPOIItem poiItem){
//        this.poiItem = poiItem;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setPrice(String price){
//        this.price = price;
//    }

//    public String getPrice(){
//        return price;
//    }

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

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public void putItem(String itemname, String price, String difference) {
        items.put(itemname, new Item(itemname, price, difference));
    }
    public Item getItem(String itemname){
        //못찾았을때 널인지 확인
        Item result = items.get(itemname);
        return result;
//        if(result != null){
//            return result;
//        }else{
//            return
//        }
    }

    public Set<String> allItems() {
        return items.keySet();
    }
}
