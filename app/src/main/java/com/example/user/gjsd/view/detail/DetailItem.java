package com.example.user.gjsd.detail;

import java.util.ArrayList;

public class DetailItem {

    //Properties of Position
    public String position;
    public String image;
    public ArrayList<String> childinfo = new ArrayList<String>();

    public DetailItem(String position){
        this.position = position;
    }

    public String toString () {
        return position;
    }


}
