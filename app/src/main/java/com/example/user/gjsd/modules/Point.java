package com.example.user.gjsd.modules;

import net.daum.mf.map.api.MapPoint;

public class Point {
    double x;
    double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public MapPoint getMapPoint(){
        return MapPoint.mapPointWithGeoCoord(x, y);
    }
}
