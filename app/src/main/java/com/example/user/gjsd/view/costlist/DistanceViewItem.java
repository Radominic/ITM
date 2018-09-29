package com.example.user.gjsd.view.costlist;

public class DistanceViewItem {

    private String numberStr ;
    private String titleStr ;
    private String descStr ;

    public void setNumber(String number) {
        numberStr = number;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public String getNumber() {
        return this.numberStr ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}
