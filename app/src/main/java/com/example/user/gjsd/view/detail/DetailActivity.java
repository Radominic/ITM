package com.example.user.gjsd.view.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.gjsd.R;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private Intent intent ;
    private String marketName ;
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

    //add and get data for list
    private ArrayList<DetailItem> getData() {

        DetailItem p1 = new DetailItem("pitcher");
        p1.childinfo.add("고원준");

        DetailItem p2 = new DetailItem("catcher");
        p2.childinfo.add("강민호");

        DetailItem p3 = new DetailItem("infield");
        p3.childinfo.add("문규현");

        DetailItem p4 = new DetailItem("outfield");
        p4.childinfo.add("Jim Adduci");

        ArrayList<DetailItem> allposition = new ArrayList<>();
        allposition.add(p1);
        allposition.add(p2);
        allposition.add(p3);
        allposition.add(p4);

        return allposition;
    }

    }
