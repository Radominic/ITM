package com.example.user.gjsd.view.detail;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.gjsd.R;
import com.example.user.gjsd.modules.MarketExplorer;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Intent intent ;
    private String marketName ;
    private MarketExplorer marketExplorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        intent = getIntent();
        marketName = intent.getStringExtra("marketName");

        TextView textView = (TextView)findViewById(R.id.markettitle);
        textView.setText(marketName);

        ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableview);
        final ArrayList<DetailItem> position = getData();
        //create and bind to adatper
        DetailAdapter adapter = new DetailAdapter(this, position);
        expandableListView.setAdapter(adapter);

        //set onclick listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(), position.get(groupPosition).childinfo.get(childPosition), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
    private void setMarketExplorer(MarketExplorer marketExplorer){
        this.marketExplorer = marketExplorer;

    }

    //add and get data for list
    private ArrayList<DetailItem> getData() {

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
        DetailItem p18 = new DetailItem("호박", ContextCompat.getDrawable(this, R.drawable.item18));
        p1.childinfo.add("1");
        p2.childinfo.add("2");
        p3.childinfo.add("3");
        p4.childinfo.add("4");
        p5.childinfo.add("5");
        p6.childinfo.add("6");
        p7.childinfo.add("7");
        p8.childinfo.add("8");
        p9.childinfo.add("9");
        p10.childinfo.add("10");
        p11.childinfo.add("11");
        p12.childinfo.add("12");
        p13.childinfo.add("13");
        p14.childinfo.add("14");
        p15.childinfo.add("15");
        p16.childinfo.add("16");
        p17.childinfo.add("17");
        p18.childinfo.add("18");

        ArrayList<DetailItem> allposition = new ArrayList<>();
        allposition.add(p1);
        allposition.add(p2);
        allposition.add(p3);
        allposition.add(p4);
        allposition.add(p5);
        allposition.add(p6);
        allposition.add(p7);
        allposition.add(p8);
        allposition.add(p9);
        allposition.add(p10);
        allposition.add(p11);
        allposition.add(p12);
        allposition.add(p13);
        allposition.add(p14);
        allposition.add(p15);
        allposition.add(p16);
        allposition.add(p17);
        allposition.add(p18);



        return allposition;
    }

    }
