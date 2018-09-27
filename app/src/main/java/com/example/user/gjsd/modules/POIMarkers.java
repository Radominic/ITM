package com.example.user.gjsd.modules;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.user.gjsd.R;

public class POIMarkers {
    String name ;
    String price ;
    Context context;
    Drawable defaultdrawble ;

    public void setContext(Context context) {
        this.context = context;
    }

    public POIMarkers(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
    public Bitmap writeOnDrawable(){
        //마켓의 종류에 따라 다른 드로어블 부여함.
        int drawableId = R.drawable.default_mart;
        String text = name;
        int TextSize = 30;

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        BitmapFactory.decodeResource(context.getResources(), R.drawable.custom_marker_red);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(TextSize);
        paint.setTextAlign(Paint.Align.CENTER);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text,bm.getWidth()/2 , bm.getHeight()/2, paint);

        return bm;
    }
}
