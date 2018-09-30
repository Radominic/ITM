package com.example.user.gjsd.view.costlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.user.gjsd.R;
import com.example.user.gjsd.view.MapFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CostViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<String> costMarketNameList = new ArrayList<String>() ;
    private Map<String, CostViewItem> costViewItems = Collections.synchronizedMap(new HashMap<String, CostViewItem>());
    costFragment c;
    // ListViewAdapter의 생성자
    public CostViewAdapter(costFragment c) {
        this.c = c;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return costViewItems.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.costfragment, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView numberTextView = (TextView) convertView.findViewById(R.id.textView0);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        CostViewItem costViewItem = costViewItems.get(costMarketNameList.get(position));
        if(costViewItem==null) Log.d("@@@","s");
        // 아이템 내 각 위젯에 데이터 반영
        numberTextView.setText(""+(position+1));
        if(costViewItem.getName().equals("용산구 농협 하나로마트 용산점"))
            titleTextView.setText("용산구 농협 하나로마트");
        else{
            titleTextView.setText(costViewItem.getName());
        }
        if(costViewItem.getMarket().getItem(c.getSelectedItem()) != null&&!costViewItem.getMarket().getItem(c.getSelectedItem()).getPrice().equals("0")) {
            descTextView.setText(c.getSelectedItem()+" : "+Currency.getInstance(Locale.KOREA).getSymbol()+costViewItem.getMarket().getItem(c.getSelectedItem()).getPrice()+" "+ MapFragment.getCriteria(c.getSelectedItem()));
        }else{
            descTextView.setText("품목없음");
        }
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return costViewItems.get(costMarketNameList.get(position)) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
//    public void addItem(CostViewItem item) {
//        costViewItems.put(item.getName(),item);
//
////        item.setNumber(ber);
////        item.setTitle(title);
////        item.setDesc(desc);
////
////        costViewItemList.add(item);
//    }

    public void addItem(CostViewItem item) {
        costViewItems.put(item.getName(),item);
    }

    public void setOrder(ArrayList<String> namelist){
        this.costMarketNameList = namelist;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }

}
