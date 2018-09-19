package com.example.user.gjsd;

import android.app.slice.Slice;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SlidingDrawer drawer1,drawer2;
    Animation animro, animri, animlo, animli;
    String itemname = "default";
    String[] item = {"동태","조기","달걀","닭고기","돼지고기","쇠고기","애호박","오이","상추","양파","무","배추","배","사과","오징어","고등어","명태","호박","냉동참조기"};


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:

                    if(!drawer1.isOpened()&&!drawer2.isOpened()) {
                        drawer1.animateOpen();
                    }else if(drawer1.isOpened()){
                        drawer1.animateClose();
                    }
                    else if(drawer2.isOpened()){
                        drawer1.open();
                        drawer2.close();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(!drawer1.isOpened()&&!drawer2.isOpened()) {
                        drawer2.animateOpen();
                    }else if(drawer2.isOpened()){
                        drawer2.animateClose();
                    }else if(drawer1.isOpened()){
                        drawer2.open();
                        drawer1.close();
                    }
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        drawer1 = (SlidingDrawer) findViewById(R.id.slide1);
        drawer2 = (SlidingDrawer)findViewById(R.id.slide2);
        TextView tv1 = (TextView)findViewById(R.id.title1);
        tv1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf"));
        TextView tv2 = (TextView)findViewById(R.id.title2);
        tv2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf"));
        TextView tv11 = (TextView)findViewById(R.id.title11);
        TextView tv22 = (TextView)findViewById(R.id.title22);
        tv11.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf"));
        tv22.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Black.ttf"));

        ListView listview ;
        ListViewAdapter adapter;

        adapter = new ListViewAdapter() ;

        listview = (ListView) findViewById(R.id.itemlist1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.

       for(int i=0;i<item.length;i++){
           adapter.addItem(ContextCompat.getDrawable(this, R.drawable.banana),
                   item[i]) ;
       }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                itemname = item[position];
                Log.i("itemname : ", itemname);
                drawer1.animateClose();
            }
        }) ;


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
