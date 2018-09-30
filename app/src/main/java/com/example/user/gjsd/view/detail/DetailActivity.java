package com.example.user.gjsd.view.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.gjsd.R;
import com.example.user.gjsd.modules.MarketExplorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Intent intent ;
    private String marketName ;
    private Map<String, DetailItem> detailItemMap = Collections.synchronizedMap(new HashMap<String, DetailItem>());
    //전달할 아이템들
    private ArrayList<DetailItem> detailItemList = new ArrayList<DetailItem>();

    MarketExplorer marketExplorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        marketName = intent.getStringExtra("marketName");
//        마켓이 가지고 있는 품목의 item만 가지고 있는 list, map에서 검색하여 새로운 디테일아이템리스트를 뽑아 전달함
        initItemMap();
        marketExplorer = MarketExplorer.getInstance();


        TextView textView = (TextView)findViewById(R.id.markettitle);
        if(marketName.equals("용산구 농협 하나로마트 용산점"))
            textView.setText("용산구 농협 하나로마트");
        else{
            textView.setText(marketName);
        }


        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableview);
//        final ArrayList<DetailItem> setItemViews = getItemViews();
        //create and bind to adatper

        for(String itemname : marketExplorer.getMarket(marketName).allItems()) {
            DetailItem detailItem = detailItemMap.get(itemname);
            Log.d("@@@@",itemname);
            detailItem.setDiffrence(marketExplorer.getMarket(marketName).getItem(itemname).getDifference());
            detailItemList.add(detailItem);

        }
        DetailAdapter adapter = new DetailAdapter(this, marketName);
        adapter.addViewItems(detailItemList);


        expandableListView.setAdapter(adapter);

        //set onclick listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), detailItemList.get(groupPosition).diffrence, Toast.LENGTH_LONG).show();
                return false;
            }
        });


        Button goSearch = findViewById(R.id.goSearchButton);
        goSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String destLat = ""+marketExplorer.getMarket(marketName).getMapPoint().getMapPointGeoCoord().latitude;
                String destLng = ""+marketExplorer.getMarket(marketName).getMapPoint().getMapPointGeoCoord().longitude;
                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+destLat+","+destLng+"?q="+marketName));
                startActivity(intent);
            }
        });
    }

    //add and get data for list
    private void initItemMap() {

        DetailItem p1 = new DetailItem("동태", ContextCompat.getDrawable(this, R.drawable.item1));
        DetailItem p2 = new DetailItem("조기", ContextCompat.getDrawable(this, R.drawable.item2));
        DetailItem p3 = new DetailItem("달걀", ContextCompat.getDrawable(this, R.drawable.item3));
        DetailItem p4 = new DetailItem("닭고기", ContextCompat.getDrawable(this, R.drawable.item4));
        DetailItem p5 = new DetailItem("돼지고기", ContextCompat.getDrawable(this, R.drawable.item5));
        DetailItem p6 = new DetailItem("쇠고기", ContextCompat.getDrawable(this, R.drawable.item6));
        DetailItem p7 = new DetailItem("애호박", ContextCompat.getDrawable(this, R.drawable.item7));
        DetailItem p8 = new DetailItem("오이", ContextCompat.getDrawable(this, R.drawable.item8));
        DetailItem p9 = new DetailItem("상추", ContextCompat.getDrawable(this, R.drawable.item9));
        DetailItem p10 = new DetailItem("양파", ContextCompat.getDrawable(this, R.drawable.item10));
        DetailItem p11 = new DetailItem("무", ContextCompat.getDrawable(this, R.drawable.item11));
        DetailItem p12 = new DetailItem("배추", ContextCompat.getDrawable(this, R.drawable.item12));
        DetailItem p13 = new DetailItem("배", ContextCompat.getDrawable(this, R.drawable.item13));
        DetailItem p14 = new DetailItem("사과", ContextCompat.getDrawable(this, R.drawable.item14));
        DetailItem p15 = new DetailItem("오징어", ContextCompat.getDrawable(this, R.drawable.item15));
        DetailItem p16 = new DetailItem("고등어", ContextCompat.getDrawable(this, R.drawable.item16));
        DetailItem p17 = new DetailItem("명태", ContextCompat.getDrawable(this, R.drawable.item17));
//        p1.childinfo.add("1");
//        p2.childinfo.add("2");
//        p3.childinfo.add("3");
//        p4.childinfo.add("4");
//        p5.childinfo.add("5");
//        p6.childinfo.add("6");
//        p7.childinfo.add("7");
//        p8.childinfo.add("8");
//        p9.childinfo.add("9");
//        p10.childinfo.add("10");
//        p11.childinfo.add("11");
//        p12.childinfo.add("12");
//        p13.childinfo.add("13");
//        p14.childinfo.add("14");
//        p15.childinfo.add("15");
//        p16.childinfo.add("16");
//        p17.childinfo.add("17");
//        p18.childinfo.add("18");


        detailItemMap.put(p1.getItemname(),p1);
        detailItemMap.put(p2.getItemname(),p2);
        detailItemMap.put(p3.getItemname(),p3);
        detailItemMap.put(p4.getItemname(),p4);
        detailItemMap.put(p5.getItemname(),p5);
        detailItemMap.put(p6.getItemname(),p6);
        detailItemMap.put(p7.getItemname(),p7);
        detailItemMap.put(p8.getItemname(),p8);
        detailItemMap.put(p9.getItemname(),p9);
        detailItemMap.put(p10.getItemname(),p10);
        detailItemMap.put(p11.getItemname(),p11);
        detailItemMap.put(p12.getItemname(),p12);
        detailItemMap.put(p13.getItemname(),p13);
        detailItemMap.put(p14.getItemname(),p14);
        detailItemMap.put(p15.getItemname(),p15);
        detailItemMap.put(p16.getItemname(),p16);
        detailItemMap.put(p17.getItemname(),p17);



//        return detailItemList;
    }

    }
