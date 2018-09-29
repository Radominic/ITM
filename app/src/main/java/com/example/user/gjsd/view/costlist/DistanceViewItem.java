package com.example.user.gjsd.view.costlist;

import com.example.user.gjsd.model.Market;

public class DistanceViewItem {

//    private String numberStr ;
//    private String titleStr ;
//    private String descStr ;
//    private String disStr ;
//
//    public void setNumber(String number) {
//        numberStr = number;
//    }
//    public void setTitle(String title) {
//        titleStr = title ;
//    }
//    public void setDesc(String desc) {
//        descStr = desc ;
//    }
//
//    public void setDisStr(String disStr) {
//        this.disStr = disStr;
//    }
//
//    public String getNumber() {
//        return this.numberStr ;
//    }
//    public String getTitle() {
//        return this.titleStr ;
//    }
//    public String getDesc() {
//        return this.descStr ;
//    }
//
//    public String getDisStr() {
//        return disStr;
//    }
//
//
//
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Market getMarket() {
        return market;
    }

    private Market market;

    public DistanceViewItem(String name, Market market){
        this.name = name;
        this.market = market;
    }
}
