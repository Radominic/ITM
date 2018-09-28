package com.example.user.gjsd.modules;

public class MyPOIObject {
    //poiItem에 붙는 모델

    private String marketName;
    private String price;


    public MyPOIObject(String marketName){
        this.marketName = marketName;
    }

    public String getMarketName(){
        return this.marketName;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getPrice(){
        return price;
    }
}

