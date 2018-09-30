package com.example.user.gjsd.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.example.user.gjsd.R;
import com.example.user.gjsd.view.costlist.costFragment;
import com.example.user.gjsd.view.costlist.distanceFragment;
import com.example.user.gjsd.view.itemlist.ListViewAdapter;
import com.example.user.gjsd.modules.GPSManager;
import com.example.user.gjsd.modules.MarketExplorer;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SlidingDrawer drawer1,drawer2;
    Animation animro, animri, animlo, animli;
    String selectedItem = "사과";
    String[] item = {"동태","조기","달걀","닭고기","돼지고기","쇠고기","애호박","오이","상추","양파","무","배추","배","사과","오징어","고등어","명태","냉동참조기"};
    ViewPager pager;
    Bundle bundle = new Bundle(1);
    costFragment cf;
    distanceFragment df;
    FloatingActionButton fab ;
    MapFragment mf;
    List<String> marketnamelist ;
    MarketExplorer marketExplorer;
    Button bt1,bt2;
    pagerAdapter  pageadapter ;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:

                    if(!drawer1.isOpened()&&!drawer2.isOpened()) {
                        drawer1.animateOpen();fab.hide();
                    }else if(drawer1.isOpened()){
                        drawer1.animateClose(); fab.show();
                    }
                    else if(drawer2.isOpened()){
                        drawer1.open();
                        drawer2.close();
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(!drawer1.isOpened()&&!drawer2.isOpened()) {
                        df.updateMarkets_sort_by_distance();
                        cf.applyMarkets_sort_by_price();
                        drawer2.animateOpen(); fab.hide();
                    }else if(drawer2.isOpened()){
                        drawer2.animateClose(); fab.show();
                    }else if(drawer1.isOpened()){
                        df.updateMarkets_sort_by_distance();
                        cf.applyMarkets_sort_by_price();
                        drawer2.open();
                        drawer1.close();
                    }
                    return true;
            }
            return false;
        }
    };

    public String getSelectedItemName(){
        Log.d("itemname",selectedItem);
        return selectedItem;
    }

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

        ListView listview;
        ListViewAdapter adapter;

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        mf = new MapFragment();
        marketExplorer = new MarketExplorer(mf);
        mf.setMarketExplorer(marketExplorer);
        GPSManager gm = new GPSManager(this);
        mf.setGpsManager(gm);
        mf.setMainActivity(this);
        mf.setFramelayout((FrameLayout) findViewById(R.id.formap));
        ft.add(R.id.formap,mf);
        ft.commit();

        adapter = new ListViewAdapter() ;

        listview = (ListView) findViewById(R.id.itemlist1);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item1),item[0]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item2),item[1]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item3),item[2]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item4),item[3]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item5),item[4]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item6),item[5]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item7),item[6]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item8),item[7]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item9),item[8]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item10),item[9]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item11),item[10]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item12),item[11]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item13),item[12]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item14),item[13]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item15),item[14]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item16),item[15]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item17),item[16]) ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.item18),item[17]) ;


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                selectedItem = item[position];

                bundle.putString("itemName",selectedItem);
//                cf = new costFragment();
//                cf.setArguments(bundle);

//                pager = (ViewPager)findViewById(R.id.pager);
//                pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
//                pager.setCurrentItem(0);
                pageadapter.notifyDataSetChanged();
//                mf.setArguments(bundle);
                marketExplorer.updateMarkets_sort_by_price(selectedItem);
                fab.show();

                mf.refresh();
                drawer1.close();
            }
        }) ;


        //mf가 여기

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        pager = (ViewPager)findViewById(R.id.pager);
        bt1 = (Button)findViewById(R.id.tab1);
        bt2 = (Button)findViewById(R.id.tab2);

        pageadapter = new pagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pageadapter);
        pager.setCurrentItem(0);

        bt1.setOnClickListener(movePageListener);
        bt1.setTag(0);
        bt2.setOnClickListener(movePageListener);
        bt2.setTag(1);

        bundle.putString("itemName","상품미선택");
        cf = new costFragment();
        df = new distanceFragment();
        cf.setMarketExplorer(marketExplorer);
        df.setMarketExplorer(marketExplorer);
        cf.setArguments(bundle);
        df.setArguments(bundle);
        cf.setMainActivity(this);
        df.setMainActivity(this);
        mf.setArguments(bundle);

        //init sort_by_price
        marketExplorer.updateMarkets_sort_by_price(selectedItem);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mf.setMapMyLocation();
            }
        });


        this.marketnamelist = (List<String>)marketExplorer.getMarkets_sort_by_distance();
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,  marketnamelist ));
        final ClearEditText clearedittext = (ClearEditText)findViewById(R.id.cleartext);
        Button bt = (Button)findViewById(R.id.searchbutton);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<marketnamelist.size();i++){
                    if(marketnamelist.get(i).equals(clearedittext.getText().toString())){
                        mf.setMapMarketLocation(clearedittext.getText().toString());
                    }


                }

            }
        });
    }


    View.OnClickListener movePageListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            int tag = (int)view.getTag();
            pager.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return cf;
                case 1:
                    return df;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            return 2;
        }
    }
    //뒤로가기 리스너
    // 뒤로가기 버튼 입력시간이 담길 long 객체
    private long pressedTime = 0;

    // 리스너 생성
    public interface OnBackPressedListener {
        public void onBack();
    }

    // 리스너 객체 생성
    private OnBackPressedListener mBackListener;

    // 리스너 설정 메소드
    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    // 뒤로가기 버튼을 눌렀을 때의 오버라이드 메소드
    @Override
    public void onBackPressed() {

        // 다른 Fragment 에서 리스너를 설정했을 때 처리됩니다.
        if(mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is not null");
            // 리스너가 설정되지 않은 상태(예를들어 메인Fragment)라면
            // 뒤로가기 버튼을 연속적으로 두번 눌렀을 때 앱이 종료됩니다.
        } else {
            Log.e("!!!", "Listener is null");
            if(drawer1.isOpened()){
                drawer1.animateClose();
                fab.show();
            }else if(drawer2.isOpened()){
                drawer2.animateClose();
            }else{
                if ( pressedTime == 0 ) {
                    Snackbar.make(getWindow().getDecorView().getRootView(),
                            " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                    pressedTime = System.currentTimeMillis();
                }
                else {
                    int seconds = (int) (System.currentTimeMillis() - pressedTime);

                    if ( seconds > 2000 ) {
                        Snackbar.make(getWindow().getDecorView().getRootView(),
                                " 한 번 더 누르면 종료됩니다." , Snackbar.LENGTH_LONG).show();
                        pressedTime = 0 ;
                    }
                    else {
                        super.onBackPressed();
                        Log.e("!!!", "onBackPressed : finish, killProcess");
                        finish();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }
            }

        }
    }


}
