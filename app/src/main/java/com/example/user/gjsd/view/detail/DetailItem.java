package com.example.user.gjsd.view.detail;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class DetailItem {

    //Properties of Position
    private Drawable itemIcon;
    public String position;
    public String image;
    public ArrayList<String> childinfo = new ArrayList<String>();

    public DetailItem(String position,Drawable itemIcon){
        this.position = position;
        this.itemIcon = itemIcon;
    }

    public String toString () {
        return position;
    }
    public Drawable getIcon(){
        return this.itemIcon;
    }


}
