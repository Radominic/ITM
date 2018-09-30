package com.example.user.gjsd.model;

public class Item {
    String itemname;
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
}
