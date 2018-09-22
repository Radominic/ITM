package com.example.user.gjsd;

import android.Manifest;
import android.app.slice.Slice;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import com.example.user.gjsd.modules.GPSManager;

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
        //temp
        if (!isPermission) {
            callPermission();
        }

        GPSManager gpsManager = new GPSManager(MainActivity.this);
        MapFragment mapFragment = new MapFragment();
        mapFragment.setGpsManager(gpsManager);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.contents, mapFragment);
        fragmentTransaction.commit();
        //

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



    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            isAccessFineLocation = true;

        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            isAccessCoarseLocation = true;
        }

        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    // 전화번호 권한 요청
    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        } else {
            isPermission = true;
        }
    }
}
