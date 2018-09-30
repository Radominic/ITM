package com.example.user.gjsd.view.detail;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class DetailItem {

    //Properties of Position
    private Drawable itemIcon;
//    public String position;

    public String itemname;
    public String image;
    public String diffrence;
//    public ArrayList<String> childinfo = new ArrayList<String>();

    public DetailItem(String itemname, Drawable itemIcon) {
        this.itemname = itemname;
        this.itemIcon = itemIcon;
    }

    public String getItemname() {
        return itemname;
    }


    public String toString() {
        return itemname;
    }

    public Drawable getIcon() {
        return this.itemIcon;
    }

    public void setDiffrence(String s) {
        this.diffrence = s;
    }
}
