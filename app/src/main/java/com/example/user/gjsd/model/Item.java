package com.example.user.gjsd.model;

import java.io.Serializable;
import java.util.Set;

public class Item {
    String itemname;

//    public String getPrice() {
//        return price;
//    }

    public String getDifference() {
        return difference;
    }

    String price;
    String difference;
    public Item(String itemname,String price, String difference){
        this.difference = difference;
        this.itemname = itemname;
        this.price = price;
    }

    public String getItemname() {
        return itemname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
